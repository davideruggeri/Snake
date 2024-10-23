package it.unibs.pajc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Pannello extends JPanel {
    private Serpente serpente;
    private Timer timer;
    private boolean emosso;

    public Pannello() {
        setPreferredSize(new Dimension(500, 500));
        serpente = new Serpente(5, 5, 500, 500);

        setFocusable(true);
        inizialize();
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

    private void finePartita() {
        timer = new Timer(50, e -> {
            serpente.muovi();
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

        g.setColor(Color.GREEN);
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        serpente.disegna(g);
    }
}
