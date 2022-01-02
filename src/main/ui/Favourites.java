package main.ui;

import main.model.Stock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;



public class Favourites extends JPanel {


    Favourites(Stock[] favouriteStocks) {
        //insets
        int rows = (int) Math.ceil((favouriteStocks.length) / 2);
        this.setBorder(new EmptyBorder(15, 20, 15, 20));
        this.setLayout(new GridLayout(rows, 2, 20, 20));

        for (int i = 0; i < favouriteStocks.length; i++) {
            FavouriteComponent favouriteComponent = new FavouriteComponent(favouriteStocks[i]);
            this.add(favouriteComponent);
        }

        JScrollPane favsScrollArea = new JScrollPane(this);
        favsScrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        JFrame frame = new JFrame();
        frame.add(favsScrollArea);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

}
