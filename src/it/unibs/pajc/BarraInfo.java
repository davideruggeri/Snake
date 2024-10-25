package it.unibs.pajc;

import javax.swing.*;
import java.awt.*;

public class BarraInfo extends JPanel {
    private JLabel labelMeleMangiate;
    private JLabel title;
    private JLabel recordLabel;
    private JLabel difficoltaLabel;
    private int record;
    private ImageIcon mela;
    private ImageIcon coppa;

    public BarraInfo() {
        setLayout(new BorderLayout());
        ImageIcon originalMela = new ImageIcon(getClass().getResource("/images/school.png"));
        ImageIcon originalCoppa = new ImageIcon(getClass().getResource("/images/trophy.png"));

        Image ridemensioneMela = originalMela.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
        mela = new ImageIcon(ridemensioneMela);
        Image ridimensioneCoppa = originalCoppa.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
        coppa = new ImageIcon(ridimensioneCoppa);

        title = new JLabel("SNAKE");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        title.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(title, BorderLayout.NORTH);

        labelMeleMangiate = new JLabel("0");
        labelMeleMangiate.setIcon(mela);
        labelMeleMangiate.setFont(new Font("Arial", Font.BOLD, 18));
        add(labelMeleMangiate, BorderLayout.WEST);

        recordLabel = new JLabel(" 0");
        recordLabel.setIcon(coppa);
        recordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(recordLabel, BorderLayout.EAST);

        difficoltaLabel = new JLabel();
        difficoltaLabel.setFont(new Font("Arial", Font.BOLD, 18));
        difficoltaLabel.setText("");
        difficoltaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(difficoltaLabel, BorderLayout.CENTER);

        record = 0;
    }

    public void aggiornaInfo(int meleMangiate, String difficolta) {
        labelMeleMangiate.setIcon(mela);
        labelMeleMangiate.setText(" " + meleMangiate);
        difficoltaLabel.setText(difficolta);
        if (meleMangiate > record) {
            record = meleMangiate;
            recordLabel.setIcon(coppa);
            recordLabel.setText(" " + record);
        }
    }
}
