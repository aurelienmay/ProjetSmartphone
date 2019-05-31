package main.ComponentEcran;

import main.ComponentGallery.Gallery;
import main.ComponentIcon.IconPanel;
import main.ComponentSettings.Settings;
import main.ComponentCalculatrice.Calculatrice;
import main.ComponentIcon.IconButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class de l'écran du smartphone
 *
 * @author aurelienmay
 * @version 12.0
 */
public class PanelEcranCenter extends JPanel implements ActionListener{

    private final int applicationSize = 50 ;

    private final IconButton imageCalculatrice = new IconButton("Images\\Icons\\Calculator.png", applicationSize, applicationSize);
    private final IconButton imageGallery = new IconButton("Images\\Icons\\gallery.png", applicationSize, applicationSize);
    private final IconButton imageSettings = new IconButton("Images\\Icons\\settings.png", applicationSize, applicationSize);

    private static final IconPanel wallpaper = new IconPanel("Images\\Wallpapers\\wallpaper4.jpg", 298, 529);

    public final CardLayout gestionnaireCards = new CardLayout();

    /**
     * Constructeur de l'écran du smartphone
     */
    public PanelEcranCenter(){
        setLayout(gestionnaireCards);
        setBackground(Color.white);

        imageCalculatrice.addActionListener(this);
        imageSettings.addActionListener(this);
        imageGallery.addActionListener(this);

        FlowLayout flowLayout = new FlowLayout(30, 37, 30);
        wallpaper.setLayout(flowLayout);
        wallpaper.add(imageSettings);
        wallpaper.add(imageCalculatrice);
        wallpaper.add(imageGallery);
        IconButton imageContact = new IconButton("Images\\Icons\\contact1.png", applicationSize, applicationSize);
        wallpaper.add(imageContact);

        this.add(wallpaper, "menu");
        JPanel cardCalculatrice = new Calculatrice();
        this.add(cardCalculatrice, "calculatrice");
        JPanel cardSettings = new Settings();
        this.add(cardSettings, "settings");
        JPanel cardGallery = new Gallery();
        this.add(cardGallery, "gallery");
    }

    /**
     * Listener des icônes des applications selon le choix de l'utilisateur
     *
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e){
        Object o = e.getSource();
        if(o == imageCalculatrice){
            gestionnaireCards.show(this, "calculatrice");
        }
        if(o == imageSettings){
            //wallpaper.setIconPanel("Images\\Wallpapers\\wallpaper2.jpg", 1080, 2320);
            gestionnaireCards.show(this, "settings");
        }
        if(o == imageGallery){
            gestionnaireCards.show(this, "gallery");
        }
    }
}
