package main.ui;

import main.model.*;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        StockInfoGetter stockInfoGetter = new YahooStockInfoGetter();
        SentimentGetter sentimentGetter = new SymblSentimentGetter();
        NewsGetter googleNewsGetter = new GoogleNewsGetter();
//        Handler handler = new Handler(stockInfoGetter, googleNewsGetter, sentimentGetter);
//        new Homepage(handler.initializeFavouriteStocks());
        new Homepage(dummyStocks());
    }

    public static Stock[] dummyStocks() {
        Stock stock = new Stock("Apple", "AAPL", 120.12, 0.05);
        stock.setSentiments(new Sentiment[]{new Sentiment("aaa","bsbsdf")});
        return new Stock[]{stock, stock};
    }

    private class StubStockInfoGetter implements StockInfoGetter {

        private Stock stubStock;

        public StubStockInfoGetter(Stock stubStock) {
            this.stubStock = stubStock;
        }

        @Override
        public Stock getStock(String ticker) {
            return stubStock;
        }
    }
}
