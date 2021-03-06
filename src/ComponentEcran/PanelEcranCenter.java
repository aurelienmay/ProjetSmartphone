package ComponentEcran;

import ComponentCalculatrice.Calculator;
import ComponentContact.Contact;
import ComponentGallery.Gallery;
import ComponentIcon.IconPanel;
import ComponentSettings.Settings;
import ComponentIcon.IconButton;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Class de l'écran du smartphone
 *
 * @author aurelienmay
 * @version 12.0
 */
public class PanelEcranCenter extends JPanel implements ActionListener {

    private final int applicationSize = 50;

    private final IconButton imageCalculatrice = new IconButton("Images\\Icons\\Calculator.png", applicationSize, applicationSize);
    private final IconButton imageGallery = new IconButton("Images\\Icons\\gallery.png", applicationSize, applicationSize);
    private final IconButton imageSettings = new IconButton("Images\\Icons\\settings.png", applicationSize, applicationSize);
    private final IconButton imageContact = new IconButton("Images\\Icons\\contact1.png", applicationSize, applicationSize);

    public final CardLayout gestionnaireCards = new CardLayout();

    public static IconPanel wallpaper = new IconPanel("Gallery\\i4.jpg");

    private final Gallery cardGallery = new Gallery();

    private final Contact cardContact = new Contact();

    /**
     * Constructeur de l'écran du smartphone
     */
    public PanelEcranCenter() {
        setLayout(gestionnaireCards);
        setBackground(Color.white);

        imageCalculatrice.addActionListener(this);
        imageSettings.addActionListener(this);
        imageGallery.addActionListener(this);
        imageContact.addActionListener(this);

        //wallpaperCreator();
        wallpaperReader();

        updatePanel();

        JPanel cardCalculatrice = new Calculator();
        this.add(cardCalculatrice, "calculatrice");
        this.add(cardGallery, "gallery");

        this.add(cardContact, "contact");
    }

    /**
     * Listener des icônes des applications selon le choix de l'utilisateur
     *
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == imageCalculatrice) {
            gestionnaireCards.show(this, "calculatrice");
        }
        if (o == imageSettings) {
            JPanel cardSettings = new Settings();
            this.add(cardSettings, "settings");
            gestionnaireCards.show(this, "settings");
        }
        if (o == imageGallery) {
            cardGallery.setOpenInGallery(true);
            gestionnaireCards.show(this, "gallery");
        }
        if (o == imageContact) {
            gestionnaireCards.show(this, "contact");
        }
    }

    /**
     * Méthode qui met à jour le panel wallpaper, elle est utilisée pour initialisé le panel
     * mais aussi lors de changement du fond d'écran
     */
    public void updatePanel(){
        FlowLayout flowLayout = new FlowLayout(30, 37, 30);
        wallpaper.setLayout(flowLayout);
        wallpaper.add(imageSettings);
        wallpaper.add(imageCalculatrice);
        wallpaper.add(imageGallery);
        wallpaper.add(imageContact);
        this.add(wallpaper, "menu");
    }

    /**
     * Méthode qui lit le fichier wallpaper.ser pour mettre le fond d'écran
     * choisi lors du dernier usage du smartphone
     */
    public void wallpaperReader(){
        ObjectInputStream ois;

        try {
            final FileInputStream fichierIn = new FileInputStream("wallpaper.ser");
            ois = new ObjectInputStream(fichierIn);
            wallpaper = (IconPanel) ois.readObject();
        }catch (IIOException iio){
            iio.printStackTrace();
            Gallery.serializeWallpaper(new IconPanel("Images\\wallpaperBase.png"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }
}