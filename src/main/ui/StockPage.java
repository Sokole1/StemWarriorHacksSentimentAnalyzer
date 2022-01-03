package main.ui;

import main.model.Stock;

import javax.swing.*;
import java.awt.*;

// This is the main stock page JFrame
public class StockPage {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    Header header = new Header();

    public StockPage(Stock stock) {
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
        panel.add(new StockInfoPanel(stock), gbc);

        SourcesPanel sourcesPanel = new SourcesPanel(stock.getSentiments());
        JScrollPane scrollPane = new JScrollPane(sourcesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setFocusable(false);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.gridy++;
        gbc.insets = new Insets(0,50,50,50);
        panel.add(scrollPane, gbc);

        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
