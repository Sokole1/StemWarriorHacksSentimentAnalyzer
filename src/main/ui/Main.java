package main.ui;

import main.model.*;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        StockInfoGetter stockInfoGetter = new YahooStockInfoGetter();
        SentimentGetter sentimentGetter = new SymblSentimentGetter();
        NewsGetter googleNewsGetter = new GoogleNewsGetter();
        Handler handler = new Handler(stockInfoGetter, googleNewsGetter, sentimentGetter);
//        new Homepage(handler.initializeFavouriteStocks());
        new Homepage(dummyStocks());
    }

    public static Stock[] dummyStocks() {
        return new Stock[]{new Stock("Apple", "AAPL", 120.12, 0.05)};
    }
}
