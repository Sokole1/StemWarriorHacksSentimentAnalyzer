package main.ui;

import main.model.Stock;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class FavouriteComponent {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();

    JLabel ticker;
    JLabel priceAndPercent;
    JPanel info;
    JButton genSent;
    JButton remove;
    JPanel genSentPanel;

//    button.setOpaque(false);
//button.setContentAreaFilled(false);
//button.setBorderPainted(false);

    FavouriteComponent(Stock stock) {
        JPanel panel = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        initialize(stock);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.7;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(info, gbc);

        gbc.weightx = 0.3;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(remove, gbc);


        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        panel.add(genSentPanel, gbc);

        //panel.setBorder(new EmptyBorder(20,20,20,20));

        JFrame frame = new JFrame();


        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(WIDTH/2, WIDTH/2));


    }

    private void initialize(Stock stock) {

        String tickerName = stock.getTicker();
        String price = stock.getPrice().toString();
        String percent = stock.getPercent().toString();

        ticker = new JLabel();
        ticker.setText(tickerName);
        ticker.setFont(new Font("Open Sans", Font.BOLD, 36));

        priceAndPercent = new JLabel();
        priceAndPercent.setText(price + "(" + percent + "%)");
        priceAndPercent.setFont(new Font("Open Sans", Font.PLAIN, 36));
        priceAndPercent.setForeground(new Color(51, 148, 35));

        info = new JPanel();
        info.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        info.add(ticker);
        info.add(priceAndPercent);


        genSent = new JButton();
        genSent.setText("Generate Sentiment");
        genSent.setFont(new Font("Open Sans", Font.PLAIN, 28));
        Font font = genSent.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        genSent.setFont(font.deriveFont(attributes));
        genSent.setForeground(Color.blue);
        genSent.setOpaque(false);
        genSent.setContentAreaFilled(false);
        genSent.setBorderPainted(false);
        genSent.setFocusable(false);

        genSentPanel = new JPanel();
        genSentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        genSentPanel.add(genSent);


        remove = new JButton(new ImageIcon("assets/remove.png"));
        remove.setBorder(BorderFactory.createEmptyBorder());
        remove.setContentAreaFilled(false);
        remove.setFocusable(false);

    }

}
