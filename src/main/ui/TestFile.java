package main.ui;

import javax.swing.*;

public class TestFile extends JFrame {
    public TestFile() {
        add(new Header());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new TestFile();
    }
}