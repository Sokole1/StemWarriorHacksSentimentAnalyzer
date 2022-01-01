package main.model;

public class Stock {
    private String name;
    private String ticker;
    private Double price;
    private Double percent;
    private Double averageSentiment = null;
    private Sentiment[] sentiments;
}
