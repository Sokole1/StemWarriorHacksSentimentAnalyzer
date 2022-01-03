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
        Stock stock = new Stock("AA", "SDF", 1.2, 1.23);
        Stock[] stocks = new Stock[]{stock, stock};
        new Homepage(stocks);
    }
}
