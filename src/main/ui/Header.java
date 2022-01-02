package main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Header extends JPanel {

    JButton buttonSearch;
    JButton buttonHome;
    JTextField textField;
    JLabel title;
    JPanel searchPanel;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();

    Header() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1.0;
        initializeContent();

        gbc.gridx = 0;
        gbc.weightx = 0.1;
        this.add(title, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        setUpSearchPanel();
        this.add(searchPanel, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.1;
        this.add(buttonHome, gbc);

        this.setBackground(new Color(0, 0, 0)); // change colour of background
        this.setVisible(true); // make frame visible
    }

    private void initializeContent() {
        title = new JLabel();
        title.setText("Stock Sentiment Analyzer");
        title.setFont(new Font("Raleway", Font.BOLD, 24));
        title.setForeground(new Color(51,148,35));
        title.setBorder(new EmptyBorder(10, 50, 10, 10));

        textField = new JTextField();
        textField.setForeground(Color.black);
//        textField.setPreferredSize(new Dimension(400, 14));
        textField.setText("stock ticker");
        textField.setFont(new Font("Raleway", Font.PLAIN, 24));

        ImageIcon searchIcon = new ImageIcon("assets/buttonSearch.png");
        buttonSearch = new JButton(searchIcon);
//        buttonSearch.setRolloverEnabled(true);
//        buttonSearch.setRolloverIcon(new ImageIcon("assets/home.png"));
        buttonSearch.setPreferredSize(new Dimension(50, 50));
        buttonSearch.addActionListener(e -> {
            System.out.println("Welcome " + textField.getText());
        });
        buttonSearch.setBorder(BorderFactory.createEmptyBorder());
        buttonSearch.setContentAreaFilled(false);
        buttonSearch.setFocusable(false);

        buttonHome = new JButton(new ImageIcon("assets/home.png"));
        buttonHome.addActionListener(e -> {
            System.out.println("HELLO");
        });
        buttonHome.setBorder(BorderFactory.createEmptyBorder());
        buttonHome.setContentAreaFilled(false);
        buttonHome.setFocusable(false);
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
