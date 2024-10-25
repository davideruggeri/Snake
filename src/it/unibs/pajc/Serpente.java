package it.unibs.pajc;

import javax.swing.*;
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
    private ImageIcon testa;

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
    private void disegnaTesta(){
        ImageIcon testaOriginal = new ImageIcon(getClass().getResource("/images/snake.png"));
        Image ridemensioneTesta = testaOriginal.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
        testa = new ImageIcon(ridemensioneTesta);
    }
    public void disegna(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        disegnaTesta();

        double angolo = Math.toRadians(90);
        switch (direzione) {
            case SU -> angolo = Math.toRadians(180);
            case GIU -> angolo = Math.toRadians(0);
            case SINISTRA -> angolo = Math.toRadians(90);
            case DESTRA -> angolo = Math.toRadians(-90);
        }


        g2d.translate(corpo.getFirst().x * DIMENSIONE_CELLA + DIMENSIONE_CELLA / 2,
                corpo.getFirst().y * DIMENSIONE_CELLA + DIMENSIONE_CELLA / 2);
        g2d.rotate(angolo);
        g2d.drawImage(testa.getImage(), -DIMENSIONE_CELLA / 2, -DIMENSIONE_CELLA / 2, DIMENSIONE_CELLA, DIMENSIONE_CELLA, null);

        g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform());

        g2d.setColor(Color.BLUE);
        for (int i = 1; i < corpo.size(); i++) {
            Point p = corpo.get(i);
            g2d.fillRect(p.x * DIMENSIONE_CELLA, p.y * DIMENSIONE_CELLA, DIMENSIONE_CELLA, DIMENSIONE_CELLA);
        }

        mela.disegna(g);
    }
    /*
    public void disegna(Graphics g) {
        //g.setColor(Color.BLUE);
        //g.fillOval(corpo.getFirst().x * DIMENSIONE_CELLA, corpo.getFirst().y * DIMENSIONE_CELLA, DIMENSIONE_CELLA, DIMENSIONE_CELLA);

        disegnaTesta();
        g.drawImage(testa.getImage(), corpo.getFirst().x * DIMENSIONE_CELLA, corpo.getFirst().y * DIMENSIONE_CELLA, null    );

        g.setColor(Color.BLUE);
        for (int i = 1; i < corpo.size(); i++) {
            Point p = corpo.get(i);
            g.fillRect(p.x * DIMENSIONE_CELLA, p.y * DIMENSIONE_CELLA, DIMENSIONE_CELLA, DIMENSIONE_CELLA);
        }

        mela.disegna(g);
    }*/

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
            mela.posizionaRandom(500, 500, this);
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
