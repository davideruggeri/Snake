package it.unibs.pajc;

import javax.swing.*;
import java.awt.*;

public class BarraInfo extends JPanel {
    private JLabel labelMeleMangiate;
    private JLabel title;
    private JLabel recordLabel;
    private int record;

    public BarraInfo() {
        setLayout(new BorderLayout());

        title = new JLabel("SNAKE");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        title.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(title, BorderLayout.NORTH);

        labelMeleMangiate = new JLabel("Mele Mangiate: 0");
        labelMeleMangiate.setFont(new Font("Arial", Font.BOLD, 18));
        labelMeleMangiate.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelMeleMangiate, BorderLayout.WEST);

        recordLabel = new JLabel("Record: 0");
        recordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        recordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(recordLabel, BorderLayout.EAST);

        record = 0;
    }

    public void aggiornaMeleMangiate(int meleMangiate) {
        labelMeleMangiate.setText("Mele Mangiate: " + meleMangiate);
        if (meleMangiate > record) {
            record = meleMangiate;
            recordLabel.setText("Record: " + record);
        }

    }
}
