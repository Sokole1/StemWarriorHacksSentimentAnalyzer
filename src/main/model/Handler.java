package main.model;

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
    // EFFECTS: initializes favouriteStocks with user's saved stocks
    public void setUpFavouriteStocks() {

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
