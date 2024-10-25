package it.unibs.pajc;

import java.awt.*;
import java.util.Random;

public class Mela {
    private Point posizione;
    private static final int DIMENSIONE_CELLA = 20;

    public Mela(int larghezza, int altezza) {
        posizionaRandom(larghezza, altezza);
    }

    public void posizionaRandom(int larghezza, int altezza) {
        Random rand = new Random();
        int x = rand.nextInt(larghezza / DIMENSIONE_CELLA);
        int y = rand.nextInt(altezza / DIMENSIONE_CELLA);
        posizione = new Point(x, y);
    }

    public void disegna(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(posizione.x * DIMENSIONE_CELLA, posizione.y * DIMENSIONE_CELLA, DIMENSIONE_CELLA, DIMENSIONE_CELLA);
    }

    public Point getPosizione() {
        return posizione;
    }

    public boolean mangiata(Serpente serpente) {
        return serpente.getTesta().equals(posizione);
    }
}
