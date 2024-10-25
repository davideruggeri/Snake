package it.unibs.pajc;

import javax.swing.*;
import java.awt.*;

public class SnakeApp {
    public static void main(String[] args) {
        JFrame finestra = new JFrame();
        Pannello pannello = new Pannello();
        BarraInfo barraInfo = new BarraInfo();

        finestra.setLayout(new BorderLayout());

        finestra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        finestra.setTitle("Snake Game");

        finestra.add(pannello, BorderLayout.CENTER);
        finestra.add(barraInfo, BorderLayout.NORTH);

        pannello.setBarraInfo(barraInfo);

        finestra.pack();
        finestra.setResizable(false);
        finestra.setVisible(true);
    }
}
