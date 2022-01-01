package main.model;

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
        stock.setSentiments(newsGetter.getNewsSentiment(ticker));

        for (Sentiment sentiment : stock.getSentiments()) {
            sentiment.setSentimentScore(sentimentGetter.getSentiment(sentiment.getSource()));
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
