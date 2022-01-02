package main.ui;

import main.model.Sentiment;

import javax.swing.*;
import java.awt.*;

// TODO: Make look good, dynamic values, add source display
public class StockPage {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    Header header = new Header();

    public StockPage() {
        panel.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.gridx = 0;

        gbc.gridy = 0;
        panel.add(header, gbc);

        gbc.gridy++;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(0,50,0,50);
        panel.add(new StockInfoPanel(), gbc);

        Sentiment sentiment = new Sentiment("sdfs", "dsfds");
        Sentiment[] sentiments = new Sentiment[]{sentiment, sentiment, sentiment, sentiment,
                sentiment, sentiment, sentiment, sentiment};

        SourcesPanel sourcesPanel = new SourcesPanel(sentiments);
        JScrollPane scrollPane = new JScrollPane(sourcesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setFocusable(false);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.gridy++;
        gbc.insets = new Insets(0,50,0,50);
        panel.add(scrollPane, gbc);

        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
