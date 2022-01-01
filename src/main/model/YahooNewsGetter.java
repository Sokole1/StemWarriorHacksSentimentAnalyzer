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

public class YahooNewsGetter implements NewsGetter {

    @Override
    public Sentiment[] getNewsSentiment(String ticker) {
        return getNewsTurnIntoSentiments(getRawNews(ticker));
    }

    // TODO: remove this function when testing not needed
    public static void main(String[] args) {

        YahooNewsGetter yahooNewsGetter = new YahooNewsGetter();
        yahooNewsGetter.getYahooApiKey();
////        String r = getRawNews("AAPL");
//        Sentiment[] arr = yahooNewsGetter.getNewsSentiment("AAPL");
//
//        //change index from 0-99 for testing
//        System.out.println(arr[50].displayHeadingAndSource());
    }

    private String getRawNews(String ticker) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://yfapi.net/ws/insights/v1/finance/insights?symbol=" + ticker))
                .header("x-api-key", getYahooApiKey())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response.body();
    }

    private Sentiment[] getNewsTurnIntoSentiments(String rawNews) {
        // extracting the reports array
        JSONArray reports = new JSONObject(rawNews)
                .getJSONObject("finance")
                .getJSONObject("result")
                .getJSONArray("reports");

        //report length is 100
        Sentiment[] sentiments = new Sentiment[reports.length()];

        for (int i = 0; i < reports.length(); ++i) {
            JSONObject report = reports.getJSONObject(i);
            String heading = report.getString("title");
            String source = report.getString("summary");
            sentiments[i] = new Sentiment(heading, source);
        }

        return sentiments;
    }

    private String getYahooApiKey() {
        File file = new File("config.json");
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject json = new JSONObject(content);
            return json.getString("yahooApiKey");
        } catch (IOException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }
}




