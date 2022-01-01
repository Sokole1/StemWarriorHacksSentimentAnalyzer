package main.model;

public class Sentiment {
    private String heading;
    private Double sentimentScore;
    private String source;

    public Sentiment(String heading, Double sentimentScore, String source) {
        this.heading = heading;
        this.sentimentScore = sentimentScore;
        this.source = source;
    }



}
