package main.model;

import java.util.Arrays;

public class Stock {
    private String name;
    private String ticker;
    private Double price;
    private Double percent;
    private Double averageSentiment;
    private Sentiment[] sentiments;

    public Stock(String name, String ticker, Double price, Double percent) {
        this.name = name;
        this.ticker = ticker;
        this.price = price;
        this.percent = percent;
        this.averageSentiment = null;
    }

    // GETTERS
    public Sentiment[] getSentiments() {
        return sentiments;
    }
    public String getName() {
        return name;
    }
    public String getTicker() {
        return ticker;
    }
    public Double getPrice() {
        return price;
    }
    public Double getPercent() {
        return percent;
    }
    public Double getAverageSentiment() {
        return averageSentiment;
    }

    // SETTERS
    public void setAverageSentiment(Double averageSentiment) {
        this.averageSentiment = averageSentiment;
    }

    public void setSentiments(Sentiment[] sentiments) {
        this.sentiments = sentiments;
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
