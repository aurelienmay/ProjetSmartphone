package ComponentIcon;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class IconButton extends JButton {
    private String location;
    public int large;
    public int length;
    private String fileLocation = "";

    public int pictureLarge ;
    public int pictureLength ;
    public double percentage ;

    //Constructeur
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

    public IconButton(String location) throws IOException {
        this.location=location;
        Image img = ImageIO.read(new File(location));
        pictureLarge = ((BufferedImage) img).getWidth();
        pictureLength = ((BufferedImage) img).getHeight();

        large = 100 ;
        percentage = large*100/pictureLarge;
        length = (int) (pictureLength*(percentage/100));

        setPreferredSize(new Dimension(large, length));

        setBorderPainted(false);
        setContentAreaFilled(true);
        setFocusPainted(true);
        setOpaque(false);
    }

    public void setNewLocation(String location){
        this.location = location ;
    }

    public void setIconButtonSize(int large, int length){
        this.length=length;
        this.large=large;

        setPreferredSize(new Dimension(large, length));

        setBorderPainted(false);
        setContentAreaFilled(true);
        setFocusPainted(true);
        setOpaque(false);
    }

    public int getLarge(){
        return large;
    }

    public int getLength(){
        return length;
    }

    public void paintComponent(Graphics g){

        try {
            Image i = ImageIO.read(new File(location));
            g.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            Image img;
            try {
                img = ImageIO.read(new File(fileLocation));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }



}


