package it.unibs.pajc;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Mela {
    private Point posizione;
    private static final int DIMENSIONE_CELLA = 20;
    private ImageIcon mela;

    public Mela(int larghezza, int altezza) {
        posizionaRandom(larghezza, altezza, null);
    }

    public void posizionaRandom(int larghezza, int altezza, Serpente serpente) {
        Random rand = new Random();
        int x;
        int y;
        do {
            x = rand.nextInt(larghezza / DIMENSIONE_CELLA);
            y = rand.nextInt(altezza / DIMENSIONE_CELLA);
            posizione = new Point(x, y);

        } while (serpente != null && serpente.getTesta().equals(posizione));
    }

    public void disegnaMela() {
        ImageIcon originalMela = new ImageIcon(getClass().getResource("/images/school.png"));
        Image ridemensioneMela = originalMela.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
        mela = new ImageIcon(ridemensioneMela);
    }

    public void disegna(Graphics g) {
        disegnaMela();
        g.drawImage(mela.getImage(), posizione.x * DIMENSIONE_CELLA, posizione.y * DIMENSIONE_CELLA, null);

    }

    public Point getPosizione() {
        return posizione;
    }

    public boolean mangiata(Serpente serpente) {
        return serpente.getTesta().equals(posizione);
    }
}
