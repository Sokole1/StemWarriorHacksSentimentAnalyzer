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
    // EFFECTS: turn list of article bodies to list of convIDS, wait, turn into polarity, if message empty, return 0
    @Override
    public Double[] getSentiments(String[] articleBodies) {
        String[] convIds = new String[articleBodies.length];

        for (int i = 0; i < articleBodies.length; ++i) {
            convIds[i] = getConvID(articleBodies[i])[0];
        }

        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Double[] polarities = new Double[convIds.length];

        for (int i = 0; i < convIds.length; ++i) {
            polarities[i] = getPolarity(convIds[i]);
        }

        return polarities;
    }

    // EFFECTS: gives api articles to process, returns the ids to access processed articles later
    private String[] getConvID(String articleBody) {
        articleBody = articleBody.replace("\"", "");
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

        String convID = new JSONObject(response.body()).getString("conversationId");
        String jobID = new JSONObject(response.body()).getString("jobId");
        String[] ids = new String[]{convID, jobID};

        return ids;
    }

    // EFFECTS: requests api for polarity score of message
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
