package main.model;

import main.persistence.FavouritesWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Handler {

    private StockInfoGetter stockInfoGetter;
    private NewsGetter newsGetter;
    private SentimentGetter sentimentGetter;
    private Stock[] favouriteStocks;

    public Handler(StockInfoGetter stockInfoGetter, NewsGetter newsGetter, SentimentGetter sentimentGetter) {
        this.stockInfoGetter = new StubStockInfoGetter(
                new Stock("Apple", "AAPL", 120.12, 0.05));
        JSONArray stubReport = new JSONArray("[{\"summary\":\"<SUMMARY>\",\"provider\":\"<PROVIDER>\",\"publishedOn\":\"<DATE>\",\"id\":\"<ID>\",\"title\":\"<TITLE>\"}," +
                "{\"summary\":\"<SUMMARY>\",\"provider\":\"<PROVIDER>\",\"publishedOn\":\"<DATE>\",\"id\":\"<ID>\",\"title\":\"<TITLE>\"}," +
                "{\"summary\":\"<SUMMARY>\",\"provider\":\"<PROVIDER>\",\"publishedOn\":\"<DATE>\",\"id\":\"<ID>\",\"title\":\"<TITLE>\"}]");
        this.newsGetter = new StubNewsGetter(stubReport);
        this.sentimentGetter = new StubSentimentGetter();
//        this.stockInfoGetter = stockInfoGetter;
//        this.newsGetter = newsGetter;
//        this.sentimentGetter = sentimentGetter;
    }

    // MODIFIES: this
    // EFFECTS: initializes basic stock information for user's saved tickers
    public Stock[] initializeFavouriteStocks() throws FileNotFoundException {
        FavouritesWriter favouritesWriter = new FavouritesWriter("data/favourites.json");
        String[] tickers = favouritesWriter.getFavourites();
        favouriteStocks = Arrays.stream(tickers).map(this::getBasicStockInfo).toArray(Stock[]::new);
        return favouriteStocks;
    }

    // EFFECTS: return stock with name, ticker, price, percent. averageSentiment and sentiments are null
    public Stock getBasicStockInfo(String ticker) {
        return stockInfoGetter.getStock(ticker);
    }

    public Stock setUpRestOfStock(Stock stock) {
        stock.setSentiments(newsGetter.getNewsSentiment(stock.getTicker()));

        Sentiment[] sentiments = stock.getSentiments();

        String[] texts = Arrays.stream(sentiments).map(s -> s.getSource()).toArray(String[]::new);
        Double[] scores = sentimentGetter.getSentiments(texts);
        if (sentiments.length > 0) {
            for (int i = 0; i < sentiments.length; i++) {
                sentiments[i].setSentimentScore(scores[i]);
            }
        }
        stock.setAverageSentiment(calculateAverageSentiment(stock));
        return stock;
    }

    // EFFECTS: produce Stock from given ticker
    public Stock setUpStock(String ticker) {
        ticker = ticker.replace(" ", "");
        Stock stock = stockInfoGetter.getStock(ticker);
        if (stock == null) {
            return null;
        }
        stock.setSentiments(newsGetter.getNewsSentiment(ticker));

        Sentiment[] sentiments = stock.getSentiments();

        String[] texts = Arrays.stream(sentiments).map(s -> s.getSource()).toArray(String[]::new);
        Double[] scores = sentimentGetter.getSentiments(texts);
        if (sentiments.length > 0) {
            for (int i = 0; i < sentiments.length; i++) {
                sentiments[i].setSentimentScore(scores[i]);
            }
        }
        stock.setAverageSentiment(calculateAverageSentiment(stock));
        return stock;
    }

    // MODIFIES: Stock
    // EFFECTS: calculate the average sentiment of a given stock and sets stock averageSentiment
    public Double calculateAverageSentiment(Stock stock) {
        if (stock.getSentiments().length == 0) {
            stock.setAverageSentiment(0.0);
            return 0.0;
        }
        Double ans = 0.0;
        for (Sentiment sentiment : stock.getSentiments()) {
            ans += sentiment.getSentimentScore();
        }
        ans = Math.round(ans / stock.getSentiments().length * 100.0) / 100.0;
        stock.setAverageSentiment(ans);
        return ans;
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
            this.stubReturns = new Double[]{0.0, 0.0, 0.0, 0.0};
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
