package main.ui;

import javax.swing.*;
import java.awt.*;

public class Loading extends JPanel {

    JLabel loading;
    ImageIcon spinner;
    ImageIcon symblai;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();

    Loading() {
//        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BorderLayout());

        spinner = new ImageIcon("assets/spinner2.gif");

        loading = new JLabel("loading...", spinner, JLabel.CENTER);
        loading.setFont(new Font("Raleway", Font.BOLD, 48));

        symblai = new ImageIcon("assets/symbl-ai.jpg");

        this.add(loading, BorderLayout.CENTER);
        this.add(new JLabel(symblai), BorderLayout.SOUTH);

        this.setBackground(new Color(185, 185, 185)); // change colour of background
        this.setVisible(true); // make frame visible
    }
}
