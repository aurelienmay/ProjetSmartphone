package ComposantMenu;

import Bouton.Shutdown;
import ComposantCalculatrice.Calculatrice;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import ComposantIcon.Icon;

    public class Frame extends JFrame {

        JPanel panelSouth = new JPanel();
        JPanel panelNorth = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelEast = new JPanel();
        Shutdown shutdown = new Shutdown();
        Calculatrice calculatrice = new Calculatrice();
        JPanel panelCenter = new JPanel();

        int applicationSize = 50 ;
        Icon imageCalculatrice = new Icon("Images\\Calculatrice.png", applicationSize, applicationSize);
        Icon imageShutDown = new Icon("Images\\shutdown.png", applicationSize, applicationSize);
        Icon imageContact = new Icon("Images\\contact.png", applicationSize, applicationSize);
        Icon imageGallery = new Icon("Images\\gallery.png", applicationSize, applicationSize);


        public Frame(){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setUndecorated(true);
            setBackground(new Color(0,0,0,0));

            panelCenter.setLayout(new FlowLayout());

            //ajoute le background, contour du smartphone
            backgroundImage();

            boolean onoff = false ;

            panelCenter.setOpaque(onoff);
            panelCenter.setBackground(new Color(0,0,0,0));

            panelNorth.setOpaque(onoff);
            panelNorth.setPreferredSize(new Dimension(323, 65));

            panelWest.setOpaque(onoff);
            panelWest.setPreferredSize(new Dimension(13,100));

            panelEast.setOpaque(onoff);
            panelEast.setPreferredSize(new Dimension(13, 100));

            panelSouth.setOpaque(onoff);
            panelSouth.setPreferredSize(new Dimension(323, 57));

            panelCenter.add(imageShutDown);
            panelCenter.add(imageCalculatrice);
            panelCenter.add(imageGallery);
            panelCenter.add(imageContact);

            //panelCenter.add(calculatrice); //tu peux supprimer ça si jms (c'est juste pour afficher la calculette

            add(panelNorth, BorderLayout.NORTH);
            add(panelWest, BorderLayout.WEST);
            add(panelEast, BorderLayout.EAST);
            add(panelSouth, BorderLayout.SOUTH);
            add(panelCenter, BorderLayout.CENTER);

            pack();
        }

        public void backgroundImage(){
            try {
                Image backgroundImage = javax.imageio.ImageIO.read(new File("Images\\Smartphone.png"));
                setContentPane(new JPanel(new BorderLayout()) {
                    @Override public void paintComponent(Graphics g) {
                        g.drawImage(backgroundImage, 0, 0, null);
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }