package ComposantIcon;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class IconButton extends JButton {
    private String location;
    private int large;
    private int length;
    private String fileLocation = "";

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


