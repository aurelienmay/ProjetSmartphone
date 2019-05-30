package ComponentIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import ComponentIcon.*;

/**
 * Class IconPanel
 *
 * @author aurelienmay
 * @version 12.0
 */
public class IconPanel extends JPanel {

    private String location;
    private int large;
    private int length;

    /**
     * Constructeur
     *
     * @param location location du fichier (images)
     * @param large largeur de l'icône
     * @param length longueur de l'icône
     */
    public IconPanel(String location, int large, int length) {
        this.location=location;
        this.length=length;
        this.large=large;
        setPreferredSize(new Dimension(large, length));
        setOpaque(false);
    }

    /**
     * Met à jour la location, la largeur et la longeur de l'IconPanel
     *
     * @param location nouvelle location
     * @param large nouvelle largeur
     * @param length nouvelle longeueur
     */
    public void setIconPanel(String location, int large, int length){
        this.location=location;
        this.length=length;
        this.large=large;

        setPreferredSize(new Dimension(large, length));
        setOpaque(false);
    }

    /**
     * Méthode qui dessine l'IconPanel selon la taille insérée
     *
     * @param g
     */
    public void paintComponent(Graphics g){
        try {
            Image i = ImageIO.read(new File(location));
            g.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), this);
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
