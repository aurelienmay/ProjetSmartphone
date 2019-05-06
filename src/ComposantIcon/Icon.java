package ComposantIcon;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Icon extends JButton {
    private String location;
    private int large;
    private int length;
    private String fileLocation = "images/icons/Contacts-48.png";

    //Constructeur
    public Icon(String location, int large, int length) {
        this.location=location;
        this.length=length;
        this.large=large;

        setPreferredSize(new Dimension(large, length));

        setBorderPainted(true);
        setContentAreaFilled(true);
        setFocusPainted(true);
        setOpaque(false);
    }



}


