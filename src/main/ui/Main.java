package main.ui;

import main.model.*;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        NewsGetter newsGetter = new YahooNewsGetter();
        StockInfoGetter stockInfoGetter = new YahooStockInfoGetter();
        SentimentGetter sentimentGetter = new SymblSentimentGetter();
//        Handler handler = new Handler(stockInfoGetter, newsGetter, sentimentGetter);
//        System.out.println(Arrays.toString(handler.initializeFavouriteStocks()));
//        Stock myStock = handler.setUpStock("123214");
//        System.out.println(myStock);
//        StockDisplay stockDisplay = new StockDisplay();
        NewsGetter googleNewsGetter = new GoogleNewsGetter();
//        System.out.println(Arrays.toString(googleNewsGetter.getNewsSentiment("TSLA")));
        Handler handler = new Handler(stockInfoGetter, googleNewsGetter, sentimentGetter);
        Stock stock = handler.setUpStock("AMZN");

        System.out.println(stock);
//        Homepage homepage = new Homepage(handler.initializeFavouriteStocks());
        SearchPageError searchPageError = new SearchPageError("AsdfAPL");
        StockPage stockDisplay = new StockPage(stock);
    }
}
