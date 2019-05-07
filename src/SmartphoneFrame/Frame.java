package SmartphoneFrame;

import ComposantCalculatrice.Calculatrice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import ComposantEcran.*;
import ComposantIcon.Icon;

    public class Frame extends JFrame {

        //panels de la frame
        JPanel panelSouth = new JPanel();
        JPanel panelNorth = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelEcran = new JPanel();

        //Applications
        Calculatrice calculatrice = new Calculatrice();

        PanelEcranNorth panelEcranNorth = new PanelEcranNorth();
        PanelEcranCenter panelEcranCenter = new PanelEcranCenter();

        public Frame(){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setUndecorated(true);
            setBackground(new Color(0,0,0,0));

            //set les différents Layout
            panelEcran.setLayout(new BorderLayout());

            //Ajout du background, contour du smartphone
            backgroundImage();

            //Ajout des listener des applications
            panelEcranCenter.imageShutDown.addActionListener(new Frame.ShutDownListener());
            panelEcranCenter.imageCalculatrice.addActionListener(new Frame.CalculatriceListener());

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

        class ShutDownListener implements ActionListener {
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        }

        class CalculatriceListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                //panelNorth.add(calculatrice);
                //add(panelNorth, BorderLayout.NORTH);
            }
        }

    }