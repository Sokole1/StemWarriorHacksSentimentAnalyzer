package main.ui;

import main.model.*;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        NewsGetter newsGetter = new YahooNewsGetter();
        StockInfoGetter stockInfoGetter = new YahooStockInfoGetter();
        SentimentGetter sentimentGetter = new SymblSentimentGetter();
        Handler handler = new Handler(stockInfoGetter, newsGetter, sentimentGetter);
        System.out.println(Arrays.toString(handler.initializeFavouriteStocks()));
        Header header = new Header();
       // Stock myStock = handler.setUpStock("AAPL");
       // System.out.println(myStock);
    }
}
