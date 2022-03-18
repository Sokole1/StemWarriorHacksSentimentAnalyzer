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

public class YahooStockInfoGetter implements StockInfoGetter {
    @Override
    public Stock getStock(String ticker) {
        return rawToStock(getRawStock(ticker));
    }

    private String getRawStock(String ticker) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://yfapi.net/v6/finance/quote?symbols=" + ticker))
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

    private Stock rawToStock(String rawStock) {
        JSONArray result = new JSONObject(rawStock).getJSONObject("quoteResponse").getJSONArray("result");
        if (result.isEmpty()) {
            return null;
        }
        JSONObject stock = result.getJSONObject(0);
        System.out.println(stock);
        Stock stockOb = new Stock(stock.getString("displayName"),
                stock.getString("symbol"),
                stock.getDouble("regularMarketPrice"),
                stock.getDouble("regularMarketChangePercent"));

        return stockOb;
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


