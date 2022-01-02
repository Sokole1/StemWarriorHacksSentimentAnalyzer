package main.ui;

import main.model.*;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//        NewsGetter newsGetter = new YahooNewsGetter();
        StockInfoGetter stockInfoGetter = new YahooStockInfoGetter();
        SentimentGetter sentimentGetter = new SymblSentimentGetter();
//        Handler handler = new Handler(stockInfoGetter, newsGetter, sentimentGetter);
//        System.out.println(Arrays.toString(handler.initializeFavouriteStocks()));
//        Stock myStock = handler.setUpStock("AAPL");
//        System.out.println(myStock);
//        StockDisplay stockDisplay = new StockDisplay();
        NewsGetter googleNewsGetter = new GoogleNewsGetter();
//        System.out.println(Arrays.toString(googleNewsGetter.getNewsSentiment("TSLA")));
//        Handler handler = new Handler(stockInfoGetter, googleNewsGetter, sentimentGetter);
//        System.out.println(handler.setUpStock("GME"));

        StockPage stockDisplay = new StockPage();
    }
}
