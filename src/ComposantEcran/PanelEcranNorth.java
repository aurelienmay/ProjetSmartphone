package ComposantEcran;

import javax.swing.*;
import java.awt.*;

public class PanelEcranNorth extends JPanel {

    private Font police = new Font("Arial", Font.BOLD, 10);

    private Dimension dimReseau = new Dimension(100, 20);
    private Dimension dimHeure = new Dimension(100, 20);
    private Dimension dimBatterie = new Dimension(100, 20);

    private JLabel reseau = new JLabel(" ");
    private JLabel heure = new JLabel("14:00", SwingConstants.CENTER);
    private JLabel batterie = new JLabel(" ");

    public PanelEcranNorth(){

        setLayout(new BorderLayout());
        setBackground(Color.gray);
        setOpaque(true);

        //reseau.setSize(dimReseau);
        //heure.setSize(dimHeure);
        //batterie.setSize(dimBatterie);

        reseau.setFont(police);
        //reseau.setBackground(Color.green);
        reseau.setOpaque(true);

        heure.setFont(police);
        //heure.setBackground(Color.blue);
        heure.setOpaque(true);

        batterie.setFont(police);
        //batterie.setBackground(Color.red);
        batterie.setOpaque(true);

        this.add(heure, BorderLayout.CENTER);
        this.add(reseau, BorderLayout.WEST);
        this.add(batterie, BorderLayout.EAST);
    }
}
