package it.unibs.pajc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Pannello extends JPanel {
    private Serpente serpente;
    private Timer timer;
    private boolean emosso;
    private BarraInfo barraInfo;
    private int velocita;

    public Pannello() {
        setPreferredSize(new Dimension(500, 500));
        serpente = new Serpente(5, 5, 500, 500);
        velocita = 0;

        setFocusable(true);
        inizialize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    public void setBarraInfo(BarraInfo barraInfo) {
        this.barraInfo = barraInfo;
    }

    private void inizialize() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!emosso) {
                    timer.start();
                    emosso = true;
                }
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        serpente.cambiaDirezione(Serpente.Direction.SU);
                        break;
                    case KeyEvent.VK_DOWN:
                        serpente.cambiaDirezione(Serpente.Direction.GIU);
                        break;
                    case KeyEvent.VK_LEFT:
                        serpente.cambiaDirezione(Serpente.Direction.SINISTRA);
                        break;
                    case KeyEvent.VK_RIGHT:
                        serpente.cambiaDirezione(Serpente.Direction.DESTRA);
                        break;
                    case KeyEvent.VK_W:
                        serpente.cambiaDirezione(Serpente.Direction.SU);
                        break;
                    case KeyEvent.VK_S:
                        serpente.cambiaDirezione(Serpente.Direction.GIU);
                        break;
                    case KeyEvent.VK_A:
                        serpente.cambiaDirezione(Serpente.Direction.SINISTRA);
                        break;
                    case KeyEvent.VK_D:
                        serpente.cambiaDirezione(Serpente.Direction.DESTRA);
                        break;
                    default:
                        break;
                }
            }
        });

        emosso = false;
        finePartita();
    }

    private void scegliVelocita(){
        String messaggio = "Con quale livello di velocità vuoi giocare?";
        String[] opzioni = {"Tartaruga", "Serpente", "Coniglio"};
        int scelta = JOptionPane.showOptionDialog(this, messaggio, "Difficoltà", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, opzioni, opzioni[0]);

        if (scelta == -1) {
            System.exit(0);
        }

        switch (scelta) {
            case 0:
                velocita = 80;
                break;
            case 1:
                velocita = 55;
                break;
            case 2:
                velocita = 40;
                break;
            default:
                return;
        };
    }

    private void finePartita() {
        scegliVelocita();

        timer = new Timer(velocita, e -> {
            serpente.muovi();

            barraInfo.aggiornaMeleMangiate(serpente.getMeleMangiate());

            if (serpente.controllaCollisione(getWidth(), getHeight())) {
                timer.stop();
                int scelta = JOptionPane.showConfirmDialog(null,
                        "Hai perso! Vuoi ricominciare la partita?", "Fine partita", JOptionPane.YES_NO_OPTION);

                if (scelta == JOptionPane.YES_OPTION) {
                    serpente = new Serpente(5, 5, getWidth(), getHeight());
                    emosso = false;
                    repaint();
                } else {
                    System.exit(0);
                }
            }
            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = 20;
        Color chiaro = new Color(170, 215, 81);
        Color scuro = new Color(162, 209, 73);

        for (int y = 0; y < 500; y += cellSize) {
            for (int x = 0; x < 500; x += cellSize) {
                if ((x / cellSize + y / cellSize) % 2 == 0) {
                    g.setColor(chiaro);
                } else {
                    g.setColor(scuro);
                }
                g.fillRect(x, y, cellSize, cellSize);
            }
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        serpente.disegna(g);
    }
}
