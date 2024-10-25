package it.unibs.pajc;

import javax.swing.*;
import java.awt.*;

public class SnakeApp {
    public static void main(String[] args) {
        boolean modalitaPacifica = false;
        String[] opzioni = {"Normale", "Pacifica"};
        int scelta = JOptionPane.showOptionDialog(null, "Seleziona la modalità di gioco", "Modalità",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, opzioni, opzioni[0]);
        if (scelta == 1) {
            modalitaPacifica = true;
        }
        JFrame finestra = new JFrame();
        BarraInfo barraInfo = new BarraInfo();
        Pannello pannello = new Pannello(modalitaPacifica);


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
