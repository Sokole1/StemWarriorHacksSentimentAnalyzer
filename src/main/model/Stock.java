package main.model;

import java.util.Arrays;

public class Stock {
    private String name;
    private String ticker;
    private Double price;
    private Double percent;
    private Double averageSentiment = null;
    private Sentiment[] sentiments;

    public Stock(String name, String ticker, Double price, Double percent) {
        this.name = name;
        this.ticker = ticker;
        this.price = price;
        this.percent = percent;
        this.averageSentiment = null;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", ticker='" + ticker + '\'' +
                ", price=" + price +
                ", percent=" + percent +
                ", averageSentiment=" + averageSentiment +
                ", sentiments=" + Arrays.toString(sentiments) +
                '}';
    }
}
