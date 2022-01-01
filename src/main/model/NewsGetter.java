package main.model;

public interface NewsGetter {
    // EFFECTS: get news of Sentiment from ticker
    Sentiment[] getNewsSentiment(String ticker);
}
