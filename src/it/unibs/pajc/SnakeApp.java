package it.unibs.pajc;

import javax.swing.*;
import java.awt.*;

public class SnakeApp {
    public static void main(String[] args) {
        JFrame finestra = new JFrame();
        Pannello pannello = new Pannello();

        finestra.setSize(510, 533);
        finestra.setLayout(new BorderLayout());
        finestra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        finestra.setLocationRelativeTo(null);
        finestra.setTitle("Snake Game");
        finestra.add(pannello);
        finestra.setResizable(false);
        finestra.setVisible(true);
    }
}
