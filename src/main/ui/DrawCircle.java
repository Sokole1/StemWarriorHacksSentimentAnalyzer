package main.ui;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JPanel {

    public void paintComponent(Graphics g) {
        setSize(75, 75);
        g.drawOval(100, 100, 50, 50);

        g.setColor(Color.GREEN);
        g.fillOval(100, 100, 50, 50);
//        g2d.setColor(Color.RED);
//        Shape circle = new Ellipse2D.Double(50, 50, 100, 100);
//        g2d.draw(circle);
    }
}
