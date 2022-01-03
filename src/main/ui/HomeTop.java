package main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomeTop extends JPanel {

    JButton buttonSearch;
    JTextField textField;
    JLabel title;
    JPanel searchPanel;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();

    HomeTop() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT / 4));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        initializeContent(); // title, ticker, search

        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0,100,0,100);

        this.add(title, gbc);

        gbc.gridy = 1;
        setUpSearchPanel();
        this.add(searchPanel, gbc);

        this.setBackground(new Color(0, 0, 0)); // change colour of background
        this.setVisible(true); // make frame visible
    }

    private void initializeContent() {
        title = new JLabel();
        title.setText("Stock Sentiment Analyzer");
        title.setFont(new Font("Raleway", Font.BOLD, 24));
        title.setForeground(new Color(51,148,35));
        title.setBorder(new EmptyBorder(10, 50, 10, 10));
        title.setHorizontalAlignment(JLabel.CENTER);

        textField = new JTextField();
        textField.setForeground(Color.black);
        textField.setText("stock ticker");
        textField.setFont(new Font("Raleway", Font.PLAIN, 24));

        ImageIcon searchIcon = new ImageIcon("assets/search.png");
        buttonSearch = new JButton(searchIcon);

        buttonSearch.addActionListener(e -> {
            System.out.println("Welcome " + textField.getText());
        });
        buttonSearch.setBorder(BorderFactory.createEmptyBorder());
        buttonSearch.setContentAreaFilled(false);
        buttonSearch.setFocusable(false);
    }

    private void setUpSearchPanel() {
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.gridx = 0;

        gbc2.weighty = 1.0;
        gbc2.weightx = 1.0;
        searchPanel.add(textField, gbc2);
        gbc2.gridx = 1;
        gbc2.weightx = 0.1;
        searchPanel.add(buttonSearch, gbc2);
        searchPanel.setBackground(Color.BLACK);
    }
}
