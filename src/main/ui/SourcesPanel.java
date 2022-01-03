package main.ui;

import main.model.Sentiment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// This is the panel that contains the dynamic number of sentiments
public class SourcesPanel extends JPanel {

    SourcesPanel(Sentiment[] sentiments) {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.setBorder(new EmptyBorder(5,50,0,50));
        for (Sentiment sentiment : sentiments) {
            this.add(new SourceComponent(sentiment));
            this.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        this.setBackground(new Color(227, 227, 227));
        this.setVisible(true);
    }
}
