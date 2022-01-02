package main.ui;

import javax.swing.*;
import java.awt.*;

public class SourceComponent extends JPanel {

    JLabel headline, sentiment, score, readMore;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();

    SourceComponent() {
//        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.insets = new Insets(20, 1, 20, 1);

        initializeLabels();

        gbc.weighty = 1.0;
        gbc.gridy = 0;

        gbc.gridx = 0;
        this.add(headline, gbc);

        gbc.gridx++;
        this.add(sentiment, gbc);

        gbc.gridx++;
        this.add(score, gbc);

        gbc.gridx++;
        this.add(readMore, gbc);
        this.setBackground(new Color(251, 247, 247));
        this.setVisible(true);
    }

    private void initializeLabels() {
        headline = new JLabel("Some news headline...");
        sentiment = new JLabel("Sentiment:");
        score = new JLabel("0.1");
        readMore = new JLabel("Read More", new ImageIcon("assets/open.png"), JLabel.LEFT);
    }
}
