package ComposantEcran;

import ComponentSettings.Settings;
import ComposantCalculatrice.Calculatrice;
import ComposantIcon.IconButton;

import javax.swing.*;
import ComposantIcon.*;
import ComponentSettings.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEcranCenter extends JPanel implements ActionListener{

    private int applicationSize = 50 ;

    public IconButton imageCalculatrice = new IconButton("Images\\Icons\\Calculator.png", applicationSize, applicationSize);
    public IconButton imageShutDown = new IconButton("Images\\Icons\\shut down.png", applicationSize, applicationSize);
    public IconButton imageContact = new IconButton("Images\\Icons\\contact1.png", applicationSize, applicationSize);
    public IconButton imageGallery = new IconButton("Images\\Icons\\gallery.png", applicationSize, applicationSize);
    public IconButton imageSettings = new IconButton("Images\\Icons\\settings.png", applicationSize, applicationSize);

    public IconPanel wallpaper = new IconPanel("Images\\Wallpapers\\wallpaper1.jpg", 298, 529);

    JPanel cardCalculatrice = new Calculatrice();
    JPanel cardSettings = new Settings();
    public CardLayout gestionnaireCards = new CardLayout();
    FlowLayout flowLayout = new FlowLayout( 30, 37, 30);

    public PanelEcranCenter(){
        setLayout(gestionnaireCards);
        setBackground(Color.white);

        imageCalculatrice.addActionListener(this);
        imageShutDown.addActionListener(this);
        imageSettings.addActionListener(this);

        wallpaper.setLayout(flowLayout);
        wallpaper.add(imageShutDown);
        wallpaper.add(imageSettings);
        wallpaper.add(imageCalculatrice);
        wallpaper.add(imageGallery);
        wallpaper.add(imageContact);

        this.add(wallpaper, "menu");
        this.add(cardCalculatrice, "calculatrice");
        this.add(cardSettings, "settings");
    }

    public void actionPerformed(ActionEvent e){
        Object o = e.getSource();
        if(o == imageCalculatrice){
            gestionnaireCards.show(this, "calculatrice");
        }
        if(o == imageShutDown){
            System.exit(0);
        }
        if(o == imageSettings){
            //wallpaper.setIconPanel("Images\\Wallpapers\\wallpaper2.jpg", 1080, 2320);
            gestionnaireCards.show(this, "settings");
        }

    }
}
