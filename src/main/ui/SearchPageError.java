package main.ui;

import javax.swing.*;
import java.awt.*;

public class SearchPageError {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();

    JButton buttonHome;
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    Header header = new Header();
    JLabel stockTicker, errorMessage;

    SearchPageError(String ticker) {
        panel.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        initializeLabels(ticker);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(header, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(stockTicker, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(errorMessage, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(buttonHome, gbc);

        panel.setBackground(Color.GRAY);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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