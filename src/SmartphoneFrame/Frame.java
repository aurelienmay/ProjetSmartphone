package SmartphoneFrame;

import ComposantCalculatrice.Calculatrice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import ComposantEcran.PanelEcranNorth;
import ComposantIcon.Icon;

    public class Frame extends JFrame {

        //panels de la frame
        JPanel panelSouth = new JPanel();
        JPanel panelNorth = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelEcran = new JPanel();

        //panels de l'écran du smartphone
        JPanel panelEcranCenter = new JPanel();

        //Applications
        Calculatrice calculatrice = new Calculatrice();

        int applicationSize = 60 ;
        //Icon imageCalculatrice = new Icon("Images\\Calculatrice.png", applicationSize, applicationSize);
        Icon imageCalculatrice = new Icon("Images\\Calculator.png", applicationSize, applicationSize);
        //Icon imageShutDown = new Icon("Images\\shutdown.png", applicationSize, applicationSize);
        Icon imageShutDown = new Icon("Images\\shut down.png", applicationSize, applicationSize);
        //Icon imageContact = new Icon("Images\\contact.png", applicationSize, applicationSize);
        Icon imageContact = new Icon("Images\\contact1.png", applicationSize, applicationSize);
        Icon imageGallery = new Icon("Images\\gallery.png", applicationSize, applicationSize);
        Icon imageSettings = new Icon("Images\\settings.png", applicationSize, applicationSize);

        PanelEcranNorth panelEcranNorth = new PanelEcranNorth();

        public Frame(){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setUndecorated(true);
            setBackground(new Color(0,0,0,0));

            //set les différents Layout
            panelEcran.setLayout(new BorderLayout());
            panelEcranCenter.setLayout(new FlowLayout());

            //Paramètres du panel écran nord (barre de contrôle)

            //Paramètre du panel écran centre (
            panelEcranCenter.setBackground(Color.white);

            //ajoute le background, contour du smartphone
            backgroundImage();

            //Tracement des panels nord, est, ouest et sud ; pour
            boolean onoff = false ;
            panelEcran.setOpaque(onoff);
            panelEcran.setBackground(new Color(0,0,0,0));
            panelNorth.setOpaque(onoff);
            panelNorth.setPreferredSize(new Dimension(323, 65));
            panelWest.setOpaque(onoff);
            panelWest.setPreferredSize(new Dimension(13,100));
            panelEast.setOpaque(onoff);
            panelEast.setPreferredSize(new Dimension(12, 100));
            panelSouth.setOpaque(onoff);
            panelSouth.setPreferredSize(new Dimension(323, 57));

            //Listener des applications
            imageShutDown.addActionListener(new ShutDownListener());
            imageCalculatrice.addActionListener(new CalculatriceListener());

            //Ajout des icon des applications sur l'écran
            panelEcranCenter.add(imageShutDown);
            panelEcranCenter.add(imageCalculatrice);
            panelEcranCenter.add(imageGallery);
            panelEcranCenter.add(imageContact);
            panelEcranCenter.add(imageSettings);

            //Ajout du panel écran centre (application) et le panel écran nord (barre de contrôle)
            panelEcran.add(panelEcranNorth, BorderLayout.NORTH);
            panelEcran.add(panelEcranCenter, BorderLayout.CENTER);

            //panelCenter.add(calculatrice); //tu peux supprimer ça si jms (c'est juste pour afficher la calculette

            //Ajout des panels au smartphone (frame)
            add(panelNorth, BorderLayout.NORTH);
            add(panelWest, BorderLayout.WEST);
            add(panelEast, BorderLayout.EAST);
            add(panelSouth, BorderLayout.SOUTH);
            add(panelEcran, BorderLayout.CENTER);

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

        class ShutDownListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        }

        class CalculatriceListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                panelNorth.add(calculatrice);
                add(panelNorth, BorderLayout.NORTH);
            }
        }
    }