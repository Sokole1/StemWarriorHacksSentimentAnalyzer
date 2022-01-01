package main.model;

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
    @Override
    public Double getSentiment(String articleBody) {

        String status;
        String[] Ids = getConvID(articleBody);
        String jobId = Ids[1];
        String convId = Ids[0];

        while (true) {
            status = getJobStatus(jobId);
            if (status.equals("completed")) {
                break;
            } else {
                try {
                    TimeUnit.SECONDS.sleep(6);
                    System.out.println("slept");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        return getPolarity(convId);
    }

    // TODO: remove this function when testing not needed
    public static void main(String[] args) {

        SymblSentimentGetter symblSentimentGetter = new SymblSentimentGetter();


        Double sentiment = symblSentimentGetter.getSentiment("Okay, so you're talking about that file, which I am sending you.");


        System.out.println(sentiment);
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

        JSONObject r = new JSONObject(response.body()).getJSONArray("messages").getJSONObject(0).getJSONObject("sentiment");
        Double polarity = r.getJSONObject("polarity").getDouble("score");
        // String suggested = r.getString("suggested");
        // return response.body();
        // System.out.println(response.body());
        // System.out.println(polarity);
        return polarity;
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
