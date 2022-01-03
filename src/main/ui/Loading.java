package main.ui;

import javax.swing.*;
import java.awt.*;

public class Loading  {

    JLabel loading;
    ImageIcon spinner;
    ImageIcon symblai;
    JPanel panel;
    public JFrame frame;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();

    Loading() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setTitle("Loading, please wait");
        panel.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT / 3));
        panel.setLayout(new BorderLayout());

        spinner = new ImageIcon("assets/spinner2.gif");

        loading = new JLabel("loading...", spinner, JLabel.CENTER);
        loading.setFont(new Font("Open Sans", Font.BOLD, 48));

        symblai = new ImageIcon("assets/symbl-ai.jpg");

        panel.add(loading, BorderLayout.CENTER);
        panel.add(new JLabel(symblai), BorderLayout.SOUTH);
        panel.setBackground(new Color(185, 185, 185)); // change colour of background
        panel.setVisible(true);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        System.out.println("SLEEPING FRAME");
//        TimeUnit.SECONDS.sleep(1);
//        System.out.println("DONE SLEEPING");
    }
}
