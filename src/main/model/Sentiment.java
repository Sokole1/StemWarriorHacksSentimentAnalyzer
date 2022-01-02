package main.model;

public class Sentiment {

    private String heading;
    private Double sentimentScore;
    private String source;
    private String url;

    public Sentiment(String heading, String source) {
        this.heading = heading;
        this.source = source;
        this.url = null;
    }

    // GETTERS
    public String getHeading() {
        return this.heading;
    }

    public String getSource() {
        return this.source;
    }

    public Double getSentimentScore() {
        return sentimentScore;
    }

    // SETTERS
    public void setSentimentScore(Double sentimentScore) {
        this.sentimentScore = sentimentScore;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Sentiment{" +
                "heading='" + heading + '\'' +
                ", sentimentScore=" + sentimentScore +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
