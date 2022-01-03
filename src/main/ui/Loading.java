package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Loading {

    JLabel loading;
    ImageIcon spinner;
    ImageIcon symblai;
    JPanel panel;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();

    public Loading() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT / 3));
        panel.setLayout(new BorderLayout());

        spinner = new ImageIcon("assets/spinner2.gif");

        loading = new JLabel("loading...", spinner, JLabel.CENTER);
        loading.setFont(new Font("Open Sans", Font.BOLD, 48));

        symblai = new ImageIcon("assets/symbl-ai.jpg");

        panel.add(loading, BorderLayout.CENTER);

        panel.add(new JLabel(symblai), BorderLayout.SOUTH);
        panel.setBackground(new Color(185, 185, 185)); // change colour of background

        JDialog dialog = new JDialog();
        dialog.setTitle("Loading please wait...");
        dialog.setModal(true);
        dialog.setContentPane(panel);
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();

        //create timer to dispose of dialog after 7 seconds
        Timer timer = new Timer(7000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);//the timer should only go off once

        timer.start();
        dialog.setVisible(true);

    }
}
