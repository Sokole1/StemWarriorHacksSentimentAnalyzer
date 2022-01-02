package main.model;

public interface SentimentGetter {
    // EFFECTS: get sentiment score of given text
    Double[] getSentiments(String[] articleBodies);
}
