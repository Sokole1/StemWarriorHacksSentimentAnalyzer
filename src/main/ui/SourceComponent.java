package main.ui;

import main.model.Sentiment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

// This is what is made for each sentiment to display in StockPage
public class SourceComponent extends JPanel {

    JLabel headline, sentiment, score;
    JButton readMore;
    JPanel textPanel;

    SourceComponent(Sentiment sent) {
        initializeLabels(sent);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.add(textPanel);
        this.add(readMore);

        this.setBackground(new Color(251, 247, 247));
        this.setVisible(true);
    }

    private void initializeLabels(Sentiment sent) {
        headline = new JLabel(sent.getHeading());
        sentiment = new JLabel("Sentiment:");

        Double sentScore = sent.getSentimentScore();
        score = new JLabel(String.valueOf(sentScore));
        if (sentScore > 0) {
            score.setForeground(Color.GREEN);
        } else if (sentScore < 0) {
            score.setForeground(Color.RED);
        }

        headline.setFont(new Font("Open Sans", Font.PLAIN, 20));
        sentiment.setFont(new Font("Open Sans", Font.BOLD, 20));
        score.setFont(new Font("Open Sans", Font.PLAIN, 20));

        textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 50));
        textPanel.add(headline);
        textPanel.add(sentiment);
        textPanel.add(score);
        textPanel.setBackground(new Color(251, 247, 247));

        readMore = new JButton("Read More", new ImageIcon("assets/open.png"));
        readMore.setHorizontalAlignment(SwingConstants.RIGHT);
        readMore.setBorder(new EmptyBorder(0,0,0,30));
        readMore.setContentAreaFilled(false);
        readMore.setFocusable(false);
        readMore.setRolloverEnabled(true);
        readMore.setRolloverIcon(new ImageIcon("assets/light_open.png"));

        readMore.addActionListener(a -> {
            String myUrl = sent.getUrl();
            int ind = myUrl.indexOf("cid=");
            if (ind > 0) {
                myUrl = myUrl.substring(0, ind);
            }
            try {
                Desktop.getDesktop().browse(new URL(myUrl).toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
