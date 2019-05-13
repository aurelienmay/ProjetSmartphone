package ComponentSettings;

import javax.swing.*;
import java.awt.*;
import ComponentSettings.*;
import ComposantIcon.IconButton;

public class Informations extends JPanel {

    Font policeCaracter = new Font("Arial", Font.BOLD, 18);
    Font fontTitle = new Font("Arial", Font.BOLD, 20);

    JLabel text = new JLabel("Crée par :", JLabel.CENTER);
    JLabel creator1 = new JLabel("Aurélien May", JLabel.CENTER);
    JLabel creator2 = new JLabel("Léonard Favre", JLabel.CENTER);

    JPanel panelCenter = new JPanel();

    IconButton backBtn = new IconButton("Images\\Icons\\backbtn.png", 20, 20);

    public Informations(){
        setLayout(new BorderLayout());

        //Smartphone informations
        text.setFont(fontTitle);
        creator1.setFont(policeCaracter);
        creator2.setFont(policeCaracter);

        panelCenter.setLayout(new GridLayout(3, 1));
        panelCenter.add(text);
        panelCenter.add(creator1);
        panelCenter.add(creator2);

        add(panelCenter, BorderLayout.CENTER);
    }
}
