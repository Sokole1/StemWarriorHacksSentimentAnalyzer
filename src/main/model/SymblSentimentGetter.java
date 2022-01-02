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
import java.util.concurrent.TimeUnit;

public class SymblSentimentGetter implements SentimentGetter {

    // REQUIRES: articleBodies.length <= 2
    @Override
    public Double[] getSentiments(String[] articleBodies) {
        // turn list of article bodies to list of convIDS, wait 6 seconds, turn into polarity, if message empty, return 0
        String[] convIds = new String[articleBodies.length];

        for (int i = 0; i < articleBodies.length; ++i) {
            convIds[i] = getConvID(articleBodies[i])[0];
        }

        try {
            TimeUnit.SECONDS.sleep(7);
            System.out.println("slept");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Double[] polarities = new Double[convIds.length];

        for (int i = 0; i < convIds.length; ++i) {
            polarities[i] = getPolarity(convIds[i]);
        }

        return polarities;
    }
//    public Double getSentiment(String articleBody) {
//
//        String status;
//        String[] Ids = getConvID(articleBody);
//        String jobId = Ids[1];
//        String convId = Ids[0];
//
////        while (true) {
////            status = getJobStatus(jobId);
////            if (status.equals("completed")) {
////                break;
////            } else {
////                try {
////                    TimeUnit.SECONDS.sleep(5);
////                    System.out.println("slept");
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////
////            }
////        }
//
//        return getPolarity(convId);
//    }

    // TODO: remove this function when testing not needed
    public static void main(String[] args) {

        SymblSentimentGetter symblSentimentGetter = new SymblSentimentGetter();
        String[] articles = new String[]{"Investors had numerous economic reports to digest this morning before the market opened. The US Department of Commerce announced that its \\\"third\\\" estimate for 1Q20 GDP growth was a rate of -5.0%.  This was in line with the previous estimates and with the consensus outlook. The GDP report also includes an inflation gauge, the PCE price index. This index, excluding food and energy, increased at a 1.7% pace -- below the Federal Reserve's inflation target of 2.0%. Remember, these readings were for the first quarter; they will get worse in the second quarter. Still, the second quarter should be the bottom. This morning's Durable Goods report indicated an increase in spending on Durable Goods by 15.8% in May versus a decline of 17.7% in April. The Commerce Department also reported that the trade deficit in goods widened in May (bad for 2Q GDP) and that wholesale inventories declined (good for 3Q GDP, as inventories are replenished).  The U.S. Department of Labor reported that another 1.48 million people filed initial unemployment claims.  While still high, the readings have been declining from week to week.  Even so, the continuing claims number remained above 20 million. This typically has been a close approximation to the unemployment rate, which is likely to be at least 13% when reported next week.", "Investors had numerous economic reports to digest this morning before the market opened. The US Department of Commerce announced that its \\\"third\\\" estimate for 1Q20 GDP growth was a rate of -5.0%.  This was in line with the previous estimates and with the consensus outlook. The GDP report also includes an inflation gauge, the PCE price index. This index, excluding food and energy, increased at a 1.7% pace -- below the Federal Reserve's inflation target of 2.0%. Remember, these readings were for the first quarter; they will get worse in the second quarter. Still, the second quarter should be the bottom. This morning's Durable Goods report indicated an increase in spending on Durable Goods by 15.8% in May versus a decline of 17.7% in April. The Commerce Department also reported that the trade deficit in goods widened in May (bad for 2Q GDP) and that wholesale inventories declined (good for 3Q GDP, as inventories are replenished).  The U.S. Department of Labor reported that another 1.48 million people filed initial unemployment claims.  While still high, the readings have been declining from week to week.  Even so, the continuing claims number remained above 20 million. This typically has been a close approximation to the unemployment rate, which is likely to be at least 13% when reported next week."};

        Double[] sentiments = symblSentimentGetter.getSentiments(articles);

        //Double sentiment = symblSentimentGetter.getSentiment("Stocks rose on Friday, finishing off a strong week that saw major indexes climb higher. Riskier equities outperformed blue chips, a trend that was commonplace during the early weeks of the recovery. On Friday, the Dow Jones Industrial Average gained 162 points, or 0.6%. The S&P 500 was up 0.7% and the Nasdaq Composite was up 0.6%. For the full week, the Dow was up 2.6%, the S&P 500 was up 3.3% and the Nasdaq gained 3.4%. Year-to-date, the DJIA crossed into positive territory this week, and is up 0.4%, the S&P is up 8.6%, and the Nasdaq is up 30.4%. Over the past 52 weeks, the Dow is up 8.5%, the S&P 500 is up 19.9%, and the Nasdaq is up 46.9%.");

        for (int i = 0; i < sentiments.length; ++i) {
            System.out.println(sentiments[i]);
        }

    }

    private String[] getConvID(String articleBody) {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.symbl.ai/v1/process/text"))
                .headers("Authorization", getSymblApiKey(), "Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{" +
                        "  \"messages\": [{" +
                        "    \"payload\": {" +
                        "      \"content\":" + "\"" + articleBody + "\"" +
                        "    }" +
                        "  }]" +
                        "}"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());
        String convID = new JSONObject(response.body()).getString("conversationId");
        String jobID = new JSONObject(response.body()).getString("jobId");
        String ids[] = new String[]{convID, jobID};

        return ids;


    }

    private String getJobStatus(String Id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.symbl.ai/v1/job/" + Id))
                .headers("Authorization", getSymblApiKey(), "Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());
        String r = new JSONObject(response.body()).getString("status");
        return r;
    }

    private Double getPolarity(String Id) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.symbl.ai/v1/conversations/" + Id + "/messages?sentiment=true"))
                .header("Authorization", getSymblApiKey())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        JSONArray messageArray = new JSONObject(response.body()).getJSONArray("messages");

        if (messageArray.length() == 0 || messageArray == null) {
            return 0.0;
        } else {
            JSONObject r = new JSONObject(response.body()).getJSONArray("messages").getJSONObject(0).getJSONObject("sentiment");
            Double polarity = r.getJSONObject("polarity").getDouble("score");
            // String suggested = r.getString("suggested");
            // return response.body();
            // System.out.println(response.body());
            // System.out.println(polarity);
            return polarity;
        }
    }

    private String getSymblApiKey() {
        File file = new File("config.json");
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject json = new JSONObject(content);
            return json.getString("symblApiKey");
        } catch (IOException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }


}
