package it.unibs.pajc;

import java.awt.*;
import java.util.Random;

public class Mela {
    private Point posizione; // Posizione della mela
    private static final int DIMENSIONE_CELLA = 20; // Dimensione di ogni cella

    public Mela(int larghezza, int altezza) {
        posizionaRandom(larghezza, altezza);
    }

    // Posiziona la mela in una posizione casuale
    public void posizionaRandom(int larghezza, int altezza) {
        Random rand = new Random();
        int x = rand.nextInt(larghezza / DIMENSIONE_CELLA);
        int y = rand.nextInt(altezza / DIMENSIONE_CELLA);
        posizione = new Point(x, y);
    }

    // Disegna la mela
    public void disegna(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(posizione.x * DIMENSIONE_CELLA, posizione.y * DIMENSIONE_CELLA, DIMENSIONE_CELLA, DIMENSIONE_CELLA);
    }

    // Restituisce la posizione della mela
    public Point getPosizione() {
        return posizione;
    }

    // Controlla se il serpente ha mangiato la mela
    public boolean mangiata(Serpente serpente) {
        return serpente.getTesta().equals(posizione);
    }
}
