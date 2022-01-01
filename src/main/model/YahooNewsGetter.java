package main.model;

//public class YahooNewsGetter implements NewsGetter {
//    @Override
//    public Sentiment[] getNewsSentiment(String ticker) {
//        return new Sentiment[0];
//    }
// COMMENTED OUT FOR NOW, don't know how to use NewsGetter

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class YahooNewsGetter {
    public static void main(String[] args) {
        String r = getRawNews("AAPL");
        Sentiment[] arr = getNewsTurnIntoSentiments(r);

        //change index from 0-99 for testing
        System.out.println(arr[50].displayHeadingAndSource());
    }

    public static String getRawNews(String ticker) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://yfapi.net/ws/insights/v1/finance/insights?symbol=" + ticker))
                .header("x-api-key", "zQ0HGB2hvf84gDCfuvw5X3KoVrIVlX1b43lIaEgy")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return response.body();
    }

    public static Sentiment[] getNewsTurnIntoSentiments(String rawNews) {
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
}




