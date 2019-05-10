package ComposantEcran;

import ComposantIcon.Icon;

import javax.swing.*;
import ComposantIcon.*;
import SmartphoneFrame.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEcranCenter extends JPanel {

    private int applicationSize = 60 ;

    public Icon imageCalculatrice = new ComposantIcon.Icon("Images\\Calculator.png", applicationSize, applicationSize);
    public Icon imageShutDown = new ComposantIcon.Icon("Images\\shut down.png", applicationSize, applicationSize);
    public Icon imageContact = new ComposantIcon.Icon("Images\\contact1.png", applicationSize, applicationSize);
    public Icon imageGallery = new ComposantIcon.Icon("Images\\gallery.png", applicationSize, applicationSize);
    public Icon imageSettings = new Icon("Images\\settings.png", applicationSize, applicationSize);

    public PanelEcranCenter(){
        setLayout(new FlowLayout( 30, 30, 30));
        setBackground(Color.white);

        this.add(imageShutDown);
        this.add(imageCalculatrice);
        this.add(imageGallery);
        this.add(imageContact);
        this.add(imageSettings);
    }
}
