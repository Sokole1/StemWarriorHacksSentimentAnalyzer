package main.ui;

import javax.swing.*;
import java.awt.*;

public class SearchPageError extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();

    JButton buttonHome;
    JPanel panel = new JPanel();
    Header header = new Header(this);
    JLabel stockTicker, errorMessage;

    SearchPageError(String ticker) {
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        initializeLabels(ticker);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.weightx = 1.0;
        gbc.weighty = 0.25;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(header, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weighty = 0.8;
        gbc.gridy = 1;
        panel.add(stockTicker, gbc);

        gbc.gridy = 2;
        panel.add(errorMessage, gbc);

        gbc.gridy = 3;
        panel.add(buttonHome, gbc);

        panel.setBackground(Color.GRAY);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void initializeLabels(String ticker) {

        stockTicker = new JLabel("Could not find: " + ticker, SwingConstants.CENTER);
        // this is where ticker from home page is if not found

        stockTicker.setFont(new Font("Raleway", Font.BOLD, 48));
        errorMessage = new JLabel("Check Your Spelling and Try Again", SwingConstants.CENTER);
        errorMessage.setFont(new Font("Raleway", Font.PLAIN, 48));

        buttonHome = new JButton(new ImageIcon("assets/home.png"));
        buttonHome.setBorder(BorderFactory.createEmptyBorder());
        buttonHome.setContentAreaFilled(false);
        buttonHome.setFocusable(false);
    }
}