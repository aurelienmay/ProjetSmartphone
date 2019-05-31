package main.ComponentIcon;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * Class IconButton utilisée pour les applications, les images de la gallery,...
 *
 * @author aurelienmay
 * @version 12.0
 */
public class IconButton extends JButton {
    private String location;
    private int large;
    public int length;
    public boolean toBeDeleted = false ;

    public int pictureLarge ;
    public int pictureLength ;

    /**
     * Constructeur 1, avec toutes les informations (location et taille)
     *
     * @param location location du fichier (image)
     * @param large largeur de l'icône
     * @param length longueur de l'icône
     */
    public IconButton(String location, int large, int length) {
        this.location=location;
        this.length=length;
        this.large=large;

        setPreferredSize(new Dimension(large, length));

        setBorderPainted(false);
        setContentAreaFilled(true);
        setFocusPainted(true);
        setOpaque(false);
    }

    /**
     * Constructeur 2, avec seulement location comme paramètre
     *
     * @param location location du fichier (image)
     * @throws IOException en cas de problème dans la lecture du fichier
     */
    public IconButton(String location) throws IOException {
        this.location=location;
        BufferedImage img = ImageIO.read(new File(location));
        pictureLarge = img.getWidth();
        pictureLength = img.getHeight();

        large = 100 ;
        double percentage = large * 100 / pictureLarge;
        length = (int) (pictureLength*(percentage /100));

        setPreferredSize(new Dimension(large, length));

        setBorderPainted(false);
        setContentAreaFilled(true);
        setFocusPainted(true);
        setOpaque(false);
    }

    /**
     * Met à jour la location du fichier
     *
     * @param location nouvelle location
     */
    public void setNewLocation(String location){
        this.location = location ;
    }

    /**
     * Met à jour la largeur et la longueur de l'IconButton
     *
     * @param large nouvelle largeur
     * @param length nouvelle longueur
     */
    public void setIconButtonSize(int large, int length){
        this.length=length;
        this.large=large;

        setPreferredSize(new Dimension(large, length));

        setBorderPainted(false);
        setContentAreaFilled(true);
        setFocusPainted(true);
        setOpaque(false);
    }

    /**
     * Retourne la location de l'IconButton
     *
     * @return la location du fichier de l'IconButton
     */
    public String getFileLocation(){ return location; }

    /**
     * Retourne la largeur de l'IconButton
     *
     * @return la largeur de l'IconButton
     */
    public int getLarge(){
        return large;
    }

    /**
     * Retourne si la photo doit être supprimée ou pas
     *
     * @return toBeDeleted
     */
    public boolean getToBeDeleted(){
        return toBeDeleted;
    }

    /**
     * @return la longueur de l'IconButton
     */
    public int getLength(){
        return length;
    }

    /**
     * Méthode qui dessine l'IconButton
     *
     * @param g Graphics
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
            } catch (IIOException e2){
                //en cas de suppression d'image
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}