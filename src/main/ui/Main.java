package main.ui;

import main.model.NewsGetter;
import main.model.Sentiment;
import main.model.YahooNewsGetter;

public class Main {

    public static void main(String[] args) {
        NewsGetter newsGetter = new YahooNewsGetter();

        Sentiment[] sentiments = newsGetter.getNewsSentiment("AAPL");
        for (Sentiment s : sentiments) {
            System.out.println(s);
        }
    }
}
