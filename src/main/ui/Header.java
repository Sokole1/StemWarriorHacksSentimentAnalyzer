package main.ui;

import javax.swing.*;
import java.awt.*;

public class Header extends JFrame {

    JButton buttonSearch;
    JButton buttonHome;
    JTextField textField;
    JLabel title;

    Header() {

        title = new JLabel();
        title.setText("Stock Sentiment Analyzer");
        title.setFont(new Font("Raleway", Font.PLAIN, 54));
        title.setForeground(new Color(51,148,35));






        textField = new JTextField();
        textField.setPreferredSize(new Dimension(800, 75));
        textField.setFont(new Font("Consolas", Font.PLAIN, 35));
        textField.setForeground(Color.black);
        textField.setBackground(new Color(185, 185, 185));
        textField.setText("stock ticker");
        textField.setFont(new Font("Raleway", Font.PLAIN, 60));

        buttonSearch = new JButton(new ImageIcon("E:\\StemWarriorHacksSentimentAnalyzer\\assets\\buttonSearch.png"));
        buttonSearch.setPreferredSize(new Dimension(75,75));
        buttonSearch.addActionListener(e -> {
            if (e.getSource() == buttonSearch) {
                System.out.println("Welcome " + textField.getText());
            }
        });

        buttonHome = new JButton(new ImageIcon("E:\\StemWarriorHacksSentimentAnalyzer\\assets\\home.png"));
        buttonHome.setPreferredSize(new Dimension(75,75));

        JPanel searchPanel = new JPanel();
        searchPanel.add(textField);
        searchPanel.add(buttonSearch);
        searchPanel.setBackground(new Color(32, 33, 36));

        JPanel headerPanel = new JPanel();
        BorderLayout layout = new BorderLayout();
        layout.setHgap(15);
        headerPanel.setLayout(layout);
        headerPanel.setBackground(new Color(32, 33, 36));

        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(searchPanel, BorderLayout.CENTER);
        headerPanel.add(buttonHome, BorderLayout.EAST);

        this.add(headerPanel);

        this.setTitle("JFrame title goes here"); // sets title of frame
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE); // exit when hitting x
        //this.setSize(1440, 1024); // sets x and y dimension of frame
        this.setPreferredSize(new Dimension(1640, 160));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        // this.setLayout(null); // change layout maanger
        this.setVisible(true); // make frame visible
        this.pack(); // adjusts to size of things inside (add all before packing)

        //ImageIcon image1 = new ImageIcon("src/main/resources/img.png"); // create image icon top left
        //this.setIconImage(image1.getImage()); // change icon of frame
        this.getContentPane().setBackground(new Color(32, 33, 36)); // change colour of background
    }

}
