package main.ui;

import main.model.Stock;
import main.persistence.FavouritesWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileNotFoundException;

// This is the panel of stock page that displays the stock info
public class StockInfoPanel extends JPanel {

    JLabel stockName, stockTicker, stockPrice, stockPercent, stockSentiment, sources;
    JLabel circleImage;
    JButton favourite;
    JPanel names, prices, sentiment;

    StockInfoPanel(Stock stock) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        initializeLabels(stock);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(names, gbc);

        gbc.gridx++;
        this.add(favourite, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        this.add(prices, gbc);

        gbc.gridy++;
        this.add(sentiment, gbc);

        gbc.gridx++;
        this.add(sources, gbc);

        this.setBackground(new Color(185, 185, 185));
        this.setVisible(true);
    }

    private void initializeLabels(Stock stock) {
        stockName = new JLabel(stock.getName());
        stockName.setFont(new Font("Open Sans", Font.BOLD, 36));

        stockTicker = new JLabel(stock.getTicker());
        stockTicker.setFont(new Font("Open Sans", Font.PLAIN, 14));

        stockPrice = new JLabel("$" + stock.getPrice());
        stockPrice.setFont(new Font("Open Sans", Font.PLAIN, 30));

        stockPercent = new JLabel(stock.getPercent() + "%");
        stockPercent.setFont(new Font("Open Sans", Font.PLAIN, 30));

        stockSentiment = new JLabel();
        String sentimentValue;
        Double averageSentiment = stock.getAverageSentiment();
        if (averageSentiment > 0) {
            sentimentValue = "Positive Sentiment: " + averageSentiment;
            circleImage = new JLabel(new ImageIcon("assets/green_circle.png"));
            stockSentiment.setForeground(Color.GREEN);
        } else if (averageSentiment < 0) {
            sentimentValue = "Negative Sentiment: " + averageSentiment;
            circleImage = new JLabel(new ImageIcon("assets/red_circle.png"));
            stockSentiment.setForeground(Color.RED);
        } else {
            sentimentValue = "Neutral Sentiment: " + averageSentiment;
            circleImage = new JLabel(new ImageIcon("assets/white_circle.png"));
        }

        Double percent = stock.getPercent();
        if (percent > 0) {
            stockPercent.setForeground(Color.GREEN);
        } else if (percent < 0) {
            stockPercent.setForeground(Color.RED);
        }
        stockSentiment.setText(sentimentValue);
        stockSentiment.setFont(new Font("Open Sans", Font.PLAIN, 30));

        favourite = new JButton("Add to Favourites", new ImageIcon("assets/star.png"));
        favourite.setFont(new Font("Open Sans", Font.PLAIN, 20));
        favourite.setHorizontalAlignment(SwingConstants.RIGHT);
        favourite.setBorder(new EmptyBorder(0,0,0,30));
        favourite.setContentAreaFilled(false);
        favourite.setFocusable(false);
        favourite.setRolloverEnabled(true);
        favourite.setRolloverIcon(new ImageIcon("assets/light_star.png"));
        favourite.addActionListener(e -> {
            try {
                FavouritesWriter favouritesWriter = new FavouritesWriter("data/favourites.json");
                favouritesWriter.addToFavourites(stock.getTicker());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        sources = new JLabel(stock.getSentiments().length + " sources");
        sources.setFont(new Font("Open Sans", Font.PLAIN, 30));
        sources.setHorizontalAlignment(SwingConstants.RIGHT);
        sources.setBorder(new EmptyBorder(0,0,0,30));

        setUpPanels();
    }

    private void setUpPanels() {
        names = new JPanel();
        names.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        names.add(stockName);
        names.add(stockTicker);
        names.setBorder(new EmptyBorder(10,30,10,0));

        prices = new JPanel();
        prices.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        prices.add(stockPrice);
        prices.add(stockPercent);
        prices.setBorder(new EmptyBorder(10,30,10,0));

        sentiment = new JPanel();
        sentiment.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        sentiment.add(circleImage);
        sentiment.add(stockSentiment);
        sentiment.setBorder(new EmptyBorder(10,30,10,0));

        prices.setBackground(new Color(185, 185, 185));
        sentiment.setBackground(new Color(185, 185, 185));
        names.setBackground(new Color(185, 185, 185));
    }
}
