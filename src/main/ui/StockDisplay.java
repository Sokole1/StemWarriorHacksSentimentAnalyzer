package main.ui;

import javax.swing.*;
import java.awt.*;

// TODO: Make look good, dynamic values, add source display
public class StockDisplay {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel stockName, stockTicker, stockPrice, stockPercent, stockSentiment, favourite, sources;
    JLabel circleImage;

    public StockDisplay() {
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        initializeLabels();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(stockName, gbc);

        gbc.gridx = 1;
        panel.add(stockTicker, gbc);

        gbc.gridx = 2;
        panel.add(favourite, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(stockPrice, gbc);

        gbc.gridx = 1;
        panel.add(stockPercent, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(circleImage, gbc);

        gbc.gridx = 1;
        panel.add(stockSentiment, gbc);

        gbc.gridx = 2;
        panel.add(sources, gbc);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initializeLabels() {
        stockName = new JLabel("APPLE");
        stockTicker = new JLabel("AAPL");
        stockPrice = new JLabel("$192.27");
        stockPercent = new JLabel("(+30% last month)");
        stockSentiment = new JLabel("Negative Sentiment (0.2)");
        favourite = new JLabel("Favourite", new ImageIcon("assets/star.png"), JLabel.LEFT);
        sources = new JLabel("2 sources");
        circleImage = new JLabel(new ImageIcon("assets/red_circle.png"));
    }
}
