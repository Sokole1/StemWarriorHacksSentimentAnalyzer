# Stock Stalk
*Stock Stalk* is a Java application that allows users perform sentiment analysis on any stock using [Symbl.ai](https://symbl.ai/), [Yahoo Finance](https://www.yahoofinanceapi.com/), and [News API](https://newsapi.org/s/google-news-api).

Simply search for a stock ticker and allow Stock Stalk to provide you with its price, individual sentiment score of recent news articles, and average sentiment score. Sentiment scores range from -1.0 (negative) to 1.0 (positive).

You can also save your favourite stocks for easy access on the homepage!

## Inspiration
Making money. Just kidding. 

We thought using the Symbl API would be fun and using the sentiment analysis feature on stocks was the first good idea that came to mind.

## How it works
The user enters a stock ticker which we use to get stock information using [Yahoo's Finance API](https://www.yahoofinanceapi.com/) and recent news articles using [News API](https://newsapi.org/s/google-news-api). 

We then use Symbl.ai's sentiment analysis API to get the polarity score of each news article. The scores are averaged and shown to the user along with the stock information.

(Due to Symbl.ai only allowing for two concurrent jobs with a free account, only the two most recent news articles are used for the sentiment analysis. Upgrade your plan to get even more!)

Everything was written in Java and the UI was made using Java Swing

## Installation/Quick Start Guide

You will need to the API key's for the following:
1. [Symbl.ai](https://docs.symbl.ai/docs/developer-tools/authentication)
2. [Yahoo Finance](https://www.yahoofinanceapi.com/dashboard)
3. [News API](https://newsapi.org/register)

Then, copy and paste your API keys into the config.json file.

After you finish, you are able to run the program. 

