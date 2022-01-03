package main.ui;

import main.model.Stock;

import javax.swing.*;
import java.awt.*;


public class Homepage {

    HomeTop homeTop = new HomeTop();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = (int) screenSize.getWidth();
    final int HEIGHT = (int) screenSize.getHeight();


    Homepage(Stock[] stocks) {

        JPanel home = new JPanel();
        home.setLayout(new BoxLayout(home, BoxLayout.Y_AXIS));

        Favourites favourites = new Favourites(stocks);

        JScrollPane favScrollArea = new JScrollPane(favourites);
        favScrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        favScrollArea.setFocusable(false);

        home.add(homeTop);
        home.add(favScrollArea);

        JFrame frame = new JFrame();
        frame.add(home);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
