package main.ui;

import javax.swing.*;
import java.awt.*;

public class StockInfoPanel extends JPanel {

    JLabel stockName, stockTicker, stockPrice, stockPercent, stockSentiment, favourite, sources;
    JLabel circleImage;
    JPanel names, prices, sentiment;

    StockInfoPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        initializeLabels();

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

        this.setVisible(true);
    }

    private void initializeLabels() {
        stockName = new JLabel("APPLE");
        stockName.setFont(new Font("Open Sans", Font.BOLD, 36));
        stockTicker = new JLabel("AAPL");
        stockTicker.setFont(new Font("Open Sans", Font.PLAIN, 14));
        stockPrice = new JLabel("$192.27");
        stockPrice.setFont(new Font("Open Sans", Font.PLAIN, 30));
        stockPercent = new JLabel("(+30% last month)");
        stockPercent.setFont(new Font("Open Sans", Font.PLAIN, 30));
        stockSentiment = new JLabel("Negative Sentiment (0.2)");
        stockSentiment.setFont(new Font("Open Sans", Font.PLAIN, 30));
        favourite = new JLabel("Add to Favourites", new ImageIcon("assets/star.png"), JLabel.LEFT);
        favourite.setFont(new Font("Open Sans", Font.PLAIN, 20));
        sources = new JLabel("2 sources");
        sources.setFont(new Font("Open Sans", Font.PLAIN, 30));
        circleImage = new JLabel(new ImageIcon("assets/red_circle.png"));
        
        setUpPanels();
    }

    private void setUpPanels() {
        names = new JPanel();
        names.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        names.add(stockName);
        names.add(stockTicker);

        prices = new JPanel();
        prices.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        prices.add(stockPrice);
        prices.add(stockPercent);

        sentiment = new JPanel();
        sentiment.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        sentiment.add(circleImage);
        sentiment.add(stockSentiment);
    }
}
