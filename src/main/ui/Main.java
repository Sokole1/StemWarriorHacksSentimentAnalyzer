package main.ui;

import main.model.*;

public class Main {

    public static void main(String[] args) {
        NewsGetter newsGetter = new YahooNewsGetter();
        StockInfoGetter stockInfoGetter = new YahooStockInfoGetter();
        SentimentGetter sentimentGetter = new SymblSentimentGetter();

        Handler handler = new Handler(stockInfoGetter, newsGetter, sentimentGetter);
        Stock myStock = handler.setUpStock("AAPL");
        System.out.println(myStock);
    }
}
