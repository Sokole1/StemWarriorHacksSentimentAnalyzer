package main.model;

public class Sentiment {

    private String heading;
    private Double sentimentScore;
    private String source;

    public Sentiment(String heading, String source) {
        this.heading = heading;
        this.source = source;
    }

    public String getHeading() {
        return this.heading;
    }

    public String getSource() {
        return this.source;
    }

    // remove later for testing
    public String displayHeadingAndSource() {
        return getHeading() + " : " + getSource();
    }

    @Override
    public String toString() {
        return "Sentiment{" +
                "heading='" + heading + '\'' +
                ", sentimentScore=" + sentimentScore +
                ", source='" + source + '\'' +
                '}';
    }

}
