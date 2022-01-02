package main.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GoogleNewsGetter implements NewsGetter {

    @Override
    public Sentiment[] getNewsSentiment(String ticker) {
        return getNewsTurnIntoSentiments(getRawNews(ticker));
    }

    private String getRawNews(String ticker) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://newsapi.org/v2/everything?q=" + ticker))
                .header("x-api-key", getGoogleApiKey())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response.body();
    }

    private Sentiment[] getNewsTurnIntoSentiments(String rawNews) {
        final int MAX = 2;
        JSONArray articles = new JSONObject(rawNews).getJSONArray("articles");

        Sentiment[] sentiments = articles.length() <= MAX ? new Sentiment[articles.length()] : new Sentiment[MAX];

        for (int i = 0; i < articles.length() && i < MAX; ++i) {
            JSONObject article = articles.getJSONObject(i);
            String heading = article.getString("title");
            String source = article.getString("description");
            sentiments[i] = new Sentiment(heading, source);
        }

        return sentiments;
    }

    private String getGoogleApiKey() {
        File file = new File("config.json");
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject json = new JSONObject(content);
            return json.getString("googleApiKey");
        }
        catch (IOException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }
}
