package ComposantEcran;

import ComposantCalculatrice.Calculatrice;
import ComposantIcon.Icon;

import javax.swing.*;
import ComposantIcon.*;
import SmartphoneFrame.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEcranCenter extends JPanel implements ActionListener{

    private int applicationSize = 50 ;

    public Icon imageCalculatrice = new ComposantIcon.Icon("Images\\Calculator.png", applicationSize, applicationSize);
    public Icon imageShutDown = new ComposantIcon.Icon("Images\\shut down.png", applicationSize, applicationSize);
    public Icon imageContact = new ComposantIcon.Icon("Images\\contact1.png", applicationSize, applicationSize);
    public Icon imageGallery = new ComposantIcon.Icon("Images\\gallery.png", applicationSize, applicationSize);
    public Icon imageSettings = new Icon("Images\\settings.png", applicationSize, applicationSize);

    JPanel card1Calculatrice = new Calculatrice();
    public JPanel ecran = new JPanel();
    public CardLayout gestionnaireCards = new CardLayout();
    FlowLayout flowLayout = new FlowLayout( 30, 30, 30);

    public PanelEcranCenter(){
        setLayout(gestionnaireCards);
        setBackground(Color.white);

        //imageShutDown.addActionListener(new ShutDownListener());
        //imageCalculatrice.addActionListener(new CalculatriceListener());

        imageCalculatrice.addActionListener(this);
        imageShutDown.addActionListener(this);

        ecran.setLayout(flowLayout);
        ecran.add(imageShutDown);
        ecran.add(imageCalculatrice);
        ecran.add(imageGallery);
        ecran.add(imageContact);
        ecran.add(imageSettings);

        this.add(ecran, "menu");
        this.add(card1Calculatrice, "calculatrice");

    }

    public void actionPerformed(ActionEvent e){
        Object o = e.getSource();
        if(o == imageCalculatrice){
            gestionnaireCards.show(this, "calculatrice");
        }
        if(o == imageShutDown){
            System.exit(0);
        }

        }
}
