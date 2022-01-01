package main.model;

public interface SentimentGetter {
    // EFFECTS: get sentiment score of given text
    Double getSentiment(String articleBody);
}
