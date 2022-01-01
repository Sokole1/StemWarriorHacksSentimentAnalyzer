package main.model;

public class YahooNewsGetter implements NewsGetter {
    @Override
    public Sentiment[] getNewsSentiment(String ticker) {
        return new Sentiment[0];
    }
}
