package it.unibs.pajc;

import java.awt.*;
import java.util.LinkedList;

public class Serpente {
    private LinkedList<Point> corpo;
    private Direction direzione;
    private int lunghezza;
    private static final int DIMENSIONE_CELLA = 20;
    private Mela mela;

    public enum Direction {
        SU, GIU, SINISTRA, DESTRA
    }

    public Serpente(int xIniziale, int yIniziale, int larghezza, int altezza) {
        corpo = new LinkedList<>();
        corpo.add(new Point(xIniziale, yIniziale));
        lunghezza = 4;
        direzione = Direction.DESTRA;
        mela = new Mela(larghezza, altezza);
    }

    public void disegna(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(corpo.getFirst().x * DIMENSIONE_CELLA, corpo.getFirst().y * DIMENSIONE_CELLA, DIMENSIONE_CELLA, DIMENSIONE_CELLA);

        g.setColor(Color.BLUE);
        for (int i = 1; i < corpo.size(); i++) {
            Point p = corpo.get(i);
            g.fillRect(p.x * DIMENSIONE_CELLA, p.y * DIMENSIONE_CELLA, DIMENSIONE_CELLA, DIMENSIONE_CELLA);
        }

        mela.disegna(g);
    }

    public void muovi() {
        Point testa = corpo.getFirst();
        Point nuovaTesta = new Point(testa);

        switch (direzione) {
            case SU -> nuovaTesta.translate(0, -1);
            case GIU -> nuovaTesta.translate(0, 1);
            case SINISTRA -> nuovaTesta.translate(-1, 0);
            case DESTRA -> nuovaTesta.translate(1, 0);
        }

        // Controlla se il serpente ha mangiato la mela
        if (mela.mangiata(this)) {
            aumentaLunghezza(); // Aumenta la lunghezza se ha mangiato la mela
            mela.posizionaRandom(500, 500); // Riposiziona la mela
        }

        corpo.addFirst(nuovaTesta); // Aggiunge la nuova testa
        // Rimuovi l'ultima parte del corpo se non si mangia la mela
        if (corpo.size() > lunghezza) {
            corpo.removeLast();
        }
    }

    public void cambiaDirezione(Direction nuovaDirezione) {
        if ((direzione == Direction.SU && nuovaDirezione != Direction.GIU) ||
                (direzione == Direction.GIU && nuovaDirezione != Direction.SU) ||
                (direzione == Direction.SINISTRA && nuovaDirezione != Direction.DESTRA) ||
                (direzione == Direction.DESTRA && nuovaDirezione != Direction.SINISTRA)) {
            direzione = nuovaDirezione;
        }
    }

    public boolean controllaCollisione(int larghezza, int altezza) {
        Point testa = corpo.getFirst();

        if (testa.x < 0 || testa.x > larghezza / DIMENSIONE_CELLA || testa.y < 0 || testa.y > altezza / DIMENSIONE_CELLA) {
            return true;
        }

        for (int i = 1; i < corpo.size(); i++) {
            if (corpo.get(i).equals(testa)) {
                return true;
            }
        }

        return false;
    }


    public void aumentaLunghezza() {
        lunghezza++;
    }

    public Point getTesta() {
        return corpo.getFirst();
    }
}
