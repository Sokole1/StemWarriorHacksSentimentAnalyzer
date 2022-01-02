package main.model;

import main.persistence.FavouritesWriter;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Handler {

    private StockInfoGetter stockInfoGetter;
    private NewsGetter newsGetter;
    private SentimentGetter sentimentGetter;
    private Stock[] favouriteStocks;

    public Handler(StockInfoGetter stockInfoGetter, NewsGetter newsGetter, SentimentGetter sentimentGetter) {
        this.stockInfoGetter = stockInfoGetter;
        this.newsGetter = newsGetter;
        this.sentimentGetter = sentimentGetter;
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

    // EFFECTS: produce Stock from given ticker
    public Stock setUpStock(String ticker) {
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
}
