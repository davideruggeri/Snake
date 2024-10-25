package it.unibs.pajc;

import java.awt.*;
import java.util.LinkedList;

public class Serpente {
    private LinkedList<Point> corpo;
    private Direction direzione;
    private int lunghezza;
    private static final int DIMENSIONE_CELLA = 20;
    private Mela mela;
    private int meleMangiate;
    private long ultimoCambio = 0;
    private static final long DELAY = 100;
    private boolean modalitaPacifica;

    public enum Direction {
        SU, GIU, SINISTRA, DESTRA
    }

    public Serpente(int xIniziale, int yIniziale, int larghezza, int altezza, boolean modalitaPacifica) {
        corpo = new LinkedList<>();
        corpo.add(new Point(xIniziale, yIniziale));
        lunghezza = 4;
        direzione = Direction.DESTRA;
        mela = new Mela(larghezza, altezza);
        meleMangiate = 0;
        this.modalitaPacifica = modalitaPacifica;
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

        if (mela.mangiata(this)) {
            aumentaLunghezza();
            mela.posizionaRandom(500, 500);
        }

        corpo.addFirst(nuovaTesta);

        if (corpo.size() > lunghezza) {
            corpo.removeLast();
        }
    }

    public void cambiaDirezione(Direction nuovaDirezione) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - ultimoCambio < DELAY) {
            return;
        }

        if ((direzione == Direction.SU && nuovaDirezione != Direction.GIU) ||
                (direzione == Direction.GIU && nuovaDirezione != Direction.SU) ||
                (direzione == Direction.SINISTRA && nuovaDirezione != Direction.DESTRA) ||
                (direzione == Direction.DESTRA && nuovaDirezione != Direction.SINISTRA)) {
            direzione = nuovaDirezione;
        }
    }

    public boolean controllaCollisione(int larghezza, int altezza) {
        Point testa = corpo.getFirst();

        if (modalitaPacifica) {
            if (testa.x < 0) {
                testa.x = (larghezza / DIMENSIONE_CELLA) - 1;
            } else if (testa.x >= larghezza / DIMENSIONE_CELLA) {
                testa.x = 0;
            } else if (testa.y < 0) {
                testa.y = (altezza / DIMENSIONE_CELLA) - 1;
            } else if (testa.y >= altezza / DIMENSIONE_CELLA) {
                testa.y = 0;
            }
            corpo.set(0, testa);
            return false;
        }

        if (testa.x < 0 || testa.x >= larghezza / DIMENSIONE_CELLA || testa.y < 0 || testa.y >= altezza / DIMENSIONE_CELLA) {
            return true;
        }

        for (int i = 1; i < corpo.size(); i++) {
            if (corpo.get(i).equals(testa)) {
                return true;
            }
        }

        return false;
    }
    public int getMeleMangiate () {
        return meleMangiate;
    }

    public void aumentaLunghezza() {
        lunghezza++;
        meleMangiate++;

    }

    public Point getTesta() {
        return corpo.getFirst();
    }
}
