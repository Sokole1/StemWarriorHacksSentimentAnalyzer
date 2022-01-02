package test.model;

import main.model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class HandlerTest {

    private Handler handler;
    private Stock stock;

    @BeforeEach
    public void runBefore() {
        stock = new Stock("Apple", "AAPL", 177.57, -0.35352954);
        handler = new Handler(new StubStockInfoGetter(stock), new StubNewsGetter(), new StubSentimentGetter());
    }

    @Test
    public void testCalculateAverageSentimentNoSentiments() {
        stock.setSentiments(new Sentiment[0]);
        Assertions.assertEquals(0.0, handler.calculateAverageSentiment(stock));
        Assertions.assertEquals(0.0, stock.getAverageSentiment());
    }

    @Test
    public void testCalculateAverageSentimentMultipleSentiments() {
        Sentiment sent1 = new Sentiment("aa", "aaabcd");
        sent1.setSentimentScore(-0.56123);
        Sentiment sent2 = new Sentiment("ab", "ababcd");
        sent2.setSentimentScore(0.96241);
        Sentiment sent3 = new Sentiment("ab", "ababcd");
        sent3.setSentimentScore(0.10323);

        stock.setSentiments(new Sentiment[]{sent1, sent2, sent3});
        Assertions.assertEquals(0.17, handler.calculateAverageSentiment(stock));
        Assertions.assertEquals(0.17, stock.getAverageSentiment());
    }

    // TODO: test what happens when one of the apis return null or empty
    @Test
    public void testSetUpStock() {
        final Double[] STUB_SENTIMENTS = new Double[]{0.25, 0.50};

        Stock dummyStock = new Stock("APPLE", "AAPL", 153.52, 1.03);

        StockInfoGetter stockInfoGetter = new StubStockInfoGetter(dummyStock);
        SentimentGetter sentimentGetter = new StubSentimentGetter(STUB_SENTIMENTS);
        JSONArray stubReport = new JSONArray("[{\"summary\":\"<SUMMARY>\",\"provider\":\"<PROVIDER>\",\"publishedOn\":\"<DATE>\",\"id\":\"<ID>\",\"title\":\"<TITLE>\"}," +
                "{\"summary\":\"<SUMMARY>\",\"provider\":\"<PROVIDER>\",\"publishedOn\":\"<DATE>\",\"id\":\"<ID>\",\"title\":\"<TITLE>\"}," +
                "{\"summary\":\"<SUMMARY>\",\"provider\":\"<PROVIDER>\",\"publishedOn\":\"<DATE>\",\"id\":\"<ID>\",\"title\":\"<TITLE>\"}]");

        NewsGetter newsGetter = new StubNewsGetter(stubReport);
        Handler myHandler = new Handler(stockInfoGetter, newsGetter, sentimentGetter);
        dummyStock.setAverageSentiment(0.16);
        dummyStock.setSentiments(newsGetter.getNewsSentiment("AAPL"));

        for (int i = 0; i < STUB_SENTIMENTS.length; i++) {
            dummyStock.getSentiments()[i].setSentimentScore(STUB_SENTIMENTS[i]);
        }
        Assertions.assertEquals(dummyStock, myHandler.setUpStock("AAPL"));
    }

    // These tests make sure YahooNewsGetter is properly capping the length of Sentiment[]
    @Nested
    class NewsGetterNestedTest {

        @Test
        public void testGetNewsSentimentEmpty() {
            JSONArray stubReport = new JSONArray("[]");
            StubNewsGetter stubNewsGetter = new StubNewsGetter(stubReport);
            Assertions.assertEquals(0, stubNewsGetter.getNewsSentiment("sDf").length);
        }

        @Test
        public void testGetNewsSentimentOneArticle() {
            JSONArray stubReport = new JSONArray("[{\"summary\":\"<SUMMARY>\",\"provider\":\"<PROVIDER>\",\"publishedOn\":\"<DATE>\",\"id\":\"<ID>\",\"title\":\"<TITLE>\"}]");
            StubNewsGetter stubNewsGetter = new StubNewsGetter(stubReport);
            Assertions.assertEquals(1, stubNewsGetter.getNewsSentiment("sDf").length);
        }

        @Test
        public void testGetNewsSentimentLengthTwo() {
            JSONArray stubReport = new JSONArray("[{\"summary\":\"<SUMMARY>\",\"provider\":\"<PROVIDER>\",\"publishedOn\":\"<DATE>\",\"id\":\"<ID>\",\"title\":\"<TITLE>\"}," +
                    "{\"summary\":\"<SUMMARY>\",\"provider\":\"<PROVIDER>\",\"publishedOn\":\"<DATE>\",\"id\":\"<ID>\",\"title\":\"<TITLE>\"}]");
            StubNewsGetter stubNewsGetter = new StubNewsGetter(stubReport);
            Assertions.assertEquals(2, stubNewsGetter.getNewsSentiment("sDf").length);
        }

        @Test
        public void testGetNewsSentimentLengthThree() {
            JSONArray stubReport = new JSONArray("[{\"summary\":\"<SUMMARY>\",\"provider\":\"<PROVIDER>\",\"publishedOn\":\"<DATE>\",\"id\":\"<ID>\",\"title\":\"<TITLE>\"}," +
                    "{\"summary\":\"<SUMMARY>\",\"provider\":\"<PROVIDER>\",\"publishedOn\":\"<DATE>\",\"id\":\"<ID>\",\"title\":\"<TITLE>\"}," +
                    "{\"summary\":\"<SUMMARY>\",\"provider\":\"<PROVIDER>\",\"publishedOn\":\"<DATE>\",\"id\":\"<ID>\",\"title\":\"<TITLE>\"}]");
            StubNewsGetter stubNewsGetter = new StubNewsGetter(stubReport);
            Assertions.assertEquals(2, stubNewsGetter.getNewsSentiment("sDf").length);
        }
    }

    private class StubStockInfoGetter implements StockInfoGetter {

        private Stock stubStock;

        public StubStockInfoGetter(Stock stubStock) {
            this.stubStock = stubStock;
        }

        @Override
        public Stock getStock(String ticker) {
            return stubStock;
        }
    }

    private class StubSentimentGetter implements SentimentGetter {

        private Double[] stubReturns;

        public StubSentimentGetter(Double[] stubReturn) {
            this.stubReturns = stubReturn;
        }

        public StubSentimentGetter() {
            this.stubReturns = new Double[]{0.0};
        }

        @Override
        public Double[] getSentiments(String[] articleBodies) {
            return stubReturns;
        }
    }

    private class StubNewsGetter implements NewsGetter {

        private JSONArray stubReports;

        public StubNewsGetter(JSONArray stubReports) {
            this.stubReports = stubReports;
        }

        public StubNewsGetter() {
        }

        @Override
        public Sentiment[] getNewsSentiment(String ticker) {
            return getNewsTurnIntoSentiments("");
        }

        private Sentiment[] getNewsTurnIntoSentiments(String rawNews) {
            final int MAX = 2;

            Sentiment[] sentiments = stubReports.length() <= MAX ? new Sentiment[stubReports.length()] : new Sentiment[MAX];

            for (int i = 0; i < stubReports.length() && i < MAX; ++i) {
                JSONObject report = stubReports.getJSONObject(i);
                String heading = report.getString("title");
                String source = report.getString("summary");
                sentiments[i] = new Sentiment(heading, source);
            }
            System.out.println(Arrays.toString(sentiments));
            return sentiments;
        }
    }
}
