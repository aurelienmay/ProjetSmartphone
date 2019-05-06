package ComposantMenu;

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

        Calculatrice calculatrice = new Calculatrice();
        JPanel panelCenter = new JPanel();

        Icon image1 = new Icon("Images\\settings.png", 50, 50);

        public Frame(){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setUndecorated(true);
            setBackground(new Color(0,0,0,0));

            //ajoute le background, contour du smartphone
            backgroundImage();

            boolean onoff = false ;

            panelCenter.setOpaque(onoff);

            panelNorth.setOpaque(onoff);
            panelNorth.setPreferredSize(new Dimension(323, 60));

            panelWest.setOpaque(onoff);
            panelWest.setPreferredSize(new Dimension(13,100));

            panelEast.setOpaque(onoff);
            panelEast.setPreferredSize(new Dimension(13, 100));

            panelSouth.setOpaque(onoff);
            panelSouth.setPreferredSize(new Dimension(323, 57));


            //panelCenter.add(calculatrice); //tu peux supprimer ça si jms (c'est juste pour afficher la calculette
            panelCenter.add(image1);
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