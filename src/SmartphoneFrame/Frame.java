package SmartphoneFrame;

import ComposantCalculatrice.Calculatrice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import ComposantEcran.*;
import ComposantIcon.*;

    public class Frame extends JFrame {
        //pannels of the frame
        JPanel panelSouth = new JPanel();
        JPanel panelNorth = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelEcran = new JPanel();

        //button to go back to the application menu
        IconButton btnBack = new IconButton("Images\\btnback.png", 77, 30);
        JPanel panelNorthofPanelSouth = new JPanel();

        //Applications
        Calculatrice calculatrice = new Calculatrice();

        PanelEcranNorth panelEcranNorth = new PanelEcranNorth();
        PanelEcranCenter panelEcranCenter = new PanelEcranCenter();

        public IconPanel wallpaper = new IconPanel("Images\\wallpaper.jpg", 298, 529);

        public Frame(){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setUndecorated(true);
            setBackground(new Color(0,0,0,0));

            //set les différents Layout
            panelEcran.setLayout(new BorderLayout());

            //Ajout du background, contour du smartphone
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

            //Ajout du panel écran centre (application) et le panel écran nord (barre de contrôle)

            panelEcran.add(panelEcranNorth, BorderLayout.NORTH);
            panelEcran.add(panelEcranCenter, BorderLayout.CENTER);

            //panelNorthOfPanelSouth parameters
            panelNorthofPanelSouth.setBackground(new Color(0,0,0,0));
            panelNorthofPanelSouth.setPreferredSize(new Dimension(323, 1));
            panelSouth.add(panelNorthofPanelSouth, BorderLayout.NORTH);
            panelSouth.add(btnBack, BorderLayout.CENTER);

            //btnBack parameters
            btnBack.addActionListener(new BtnBackListener());
            btnBack.setHorizontalAlignment(0);
            btnBack.setVerticalAlignment(0);

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

        class BtnBackListener implements ActionListener {
            public void actionPerformed(ActionEvent e){
                panelEcranCenter.gestionnaireCards.show(panelEcranCenter, "menu");
            }
        }

    }