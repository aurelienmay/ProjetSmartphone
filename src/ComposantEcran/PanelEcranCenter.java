package ComposantEcran;

import ComposantCalculatrice.Calculatrice;
import ComposantIcon.Icon;

import javax.swing.*;
import ComposantIcon.*;
import SmartphoneFrame.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEcranCenter extends JPanel {

    private int applicationSize = 50 ;

    public Icon imageCalculatrice = new ComposantIcon.Icon("Images\\Calculator.png", applicationSize, applicationSize);
    public Icon imageShutDown = new ComposantIcon.Icon("Images\\shut down.png", applicationSize, applicationSize);
    public Icon imageContact = new ComposantIcon.Icon("Images\\contact1.png", applicationSize, applicationSize);
    public Icon imageGallery = new ComposantIcon.Icon("Images\\gallery.png", applicationSize, applicationSize);
    public Icon imageSettings = new Icon("Images\\settings.png", applicationSize, applicationSize);

    JPanel card1Calculatrice = new Calculatrice();
    JPanel cards = new JPanel();
    CardLayout gestionnaireCards = new CardLayout();

    public PanelEcranCenter(){
        //setLayout(new FlowLayout( 30, 30, 30));
        setLayout(gestionnaireCards);
        setBackground(Color.white);

        imageShutDown.addActionListener(new ShutDownListener());
        imageCalculatrice.addActionListener(new CalculatriceListener());

        this.add(imageShutDown);
        this.add(imageCalculatrice);
        this.add(imageGallery);
        this.add(imageContact);
        this.add(imageSettings);

        cards.setLayout(gestionnaireCards);
        cards.add(this, "menu");
        cards.add(card1Calculatrice, "calculatrice");
    }

    class ShutDownListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    class CalculatriceListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            gestionnaireCards.previous(cards);
        }
    }
}
