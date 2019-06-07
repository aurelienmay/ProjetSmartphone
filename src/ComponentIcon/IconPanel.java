package ComponentIcon;

import ComponentEcran.PanelEcranCenter;
import ComponentGallery.Gallery;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Class IconPanel qui permet de mettre une image en fond du panel
 *
 * @author aurelienmay
 * @version 12.0
 */
public class IconPanel extends JPanel {

    private String location;

    /**
     * Constructeur
     *
     * @param location location du fichier (images)
     */
    public IconPanel(String location) {
        this.location=location;
        setOpaque(true);
    }

    public IconPanel(){}

    /**
     * Met à jour la location
     *
     * @param location nouvelle location
     */
    public void setLocation(String location){
        this.location=location;
        setOpaque(true);
    }

    /**
     * Retourne la location du fichier (image)
     *
     * @return location
     */
    public String getFileLocation() {
        return location;
    }

    /**
     * Méthode qui dessine l'IconPanel
     *
     * @param g Graphics
     */
    public void paintComponent(Graphics g){
        try {
            File f = new File(location);
            if (!f.isFile()){
                PanelEcranCenter.wallpaper.setLocation("Images\\wallpaperBase.png");
            }else{
                Image i = ImageIO.read(f);
                g.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        } catch (IIOException io){
            //Si l'image du fond d'écran a été supprimé
            io.printStackTrace();
            Gallery.serializeWallpaper(new IconPanel("Images\\wallpaperBase.png"));
            PanelEcranCenter.wallpaper.setLocation("Images\\wallpaperBase.png");
        } catch (IOException e) {
            Image img;
            try {
                String fileLocation = "";
                img = ImageIO.read(new File(fileLocation));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
