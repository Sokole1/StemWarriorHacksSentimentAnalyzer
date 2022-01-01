package main.model;

public class Handler {

    private StockInfoGetter stockInfoGetter;
    private NewsGetter newsGetter;
    private SentimentGetter sentimentGetter;
    private Stock[] favouriteStocks;

    // MODIFIES: this
    // EFFECTS: initializes favouriteStocks with user's saved stocks
    public void setUpFavouriteStocks() {

    }

    // EFFECTS: produce Stock from given ticker
    public Stock setUpStock(String ticker) {
        return null;
    }

    // EFFECTS: calculate the average sentiment of a given stock
    public Double calculateAverageSentiment(Stock stock) {
        return -1.0;
    }
}
