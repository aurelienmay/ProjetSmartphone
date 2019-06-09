package SmartphoneFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import ComponentEcran.*;
import ComponentIcon.IconButton;
import ComponentIcon.IconPanel;

/**
 * Frame est la classe principale du smartphone
 * @author aurelienmay
 * @version
 */
    @SuppressWarnings("JavaDoc")
    public class Frame extends JFrame {

    private final PanelEcranCenter panelEcranCenter = new PanelEcranCenter();

    /**
     * Création de la frame principale
     */
        public Frame(){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setUndecorated(true);
            setBackground(new Color(0,0,0,0));

            JPanel panelEcran = new JPanel();
            panelEcran.setLayout(new BorderLayout());

            backgroundImage();

            //Tracement des panels nord, est, ouest et sud
            panelEcran.setOpaque(false);
            panelEcran.setBackground(new Color(0,0,0,0));
            JPanel panelNorth = new JPanel();
            panelNorth.setOpaque(false);
            panelNorth.setPreferredSize(new Dimension(323, 65));
            JPanel panelWest = new JPanel();
            panelWest.setOpaque(false);
            panelWest.setPreferredSize(new Dimension(13,100));
            JPanel panelEast = new JPanel();
            panelEast.setOpaque(false);
            panelEast.setPreferredSize(new Dimension(12, 100));
            JPanel panelSouth = new JPanel();
            panelSouth.setOpaque(false);
            panelSouth.setPreferredSize(new Dimension(323, 57));

            //Ajout du panel écran centre (application) et le panel écran nord (barre de contrôle)
            PanelEcranNorth panelEcranNorth = new PanelEcranNorth();
            panelEcran.add(panelEcranNorth, BorderLayout.NORTH);
            panelEcran.add(panelEcranCenter, BorderLayout.CENTER);

            //Shutdown en haut de l'écran
            JPanel panelNorthofPanelNorth = new JPanel();
            panelNorthofPanelNorth.setOpaque(false);
            panelNorthofPanelNorth.setPreferredSize(new Dimension(323, 25));
            IconButton shutdownBtn = new IconButton("Images\\Icons\\shutdown.png", 20, 20);
            shutdownBtn.addActionListener(new btnShutDownListener());
            panelNorth.add(panelNorthofPanelNorth, BorderLayout.NORTH);
            panelNorth.add(shutdownBtn, BorderLayout.SOUTH);

            //panelNorthOfPanelSouth parameters
            JPanel panelNorthofPanelSouth = new JPanel();
            panelNorthofPanelSouth.setBackground(new Color(0,0,0,0));
            panelNorthofPanelSouth.setPreferredSize(new Dimension(323, 1));
            panelSouth.add(panelNorthofPanelSouth, BorderLayout.NORTH);
            IconButton homeButton = new IconButton("Images\\btnback.png", 77, 30);
            panelSouth.add(homeButton, BorderLayout.CENTER);

            //btnBack parameters
            homeButton.addActionListener(new homeButtonListener());
            homeButton.setHorizontalAlignment(0);
            homeButton.setVerticalAlignment(0);

            //Ajout des panels au smartphone (frame)
            add(panelNorth, BorderLayout.NORTH);
            add(panelWest, BorderLayout.WEST);
            add(panelEast, BorderLayout.EAST);
            add(panelSouth, BorderLayout.SOUTH);
            add(panelEcran, BorderLayout.CENTER);

            pack();
        }

    /**
     * Méthode qui ajoute le fond d'écran de la frame (tour du smartphone)
     */
    private void backgroundImage(){
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

    public boolean fileExistingControl(IconPanel ip){
        File f = new File(ip.getFileLocation());
        return f.isFile() ;
    }

    /**
     * Listener du bouton home (revenir au menu)
     */
    class homeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (!fileExistingControl(PanelEcranCenter.wallpaper)){
                PanelEcranCenter.wallpaper.setLocation("Images\\wallpaperBase.png");
            }else {
                panelEcranCenter.wallpaperReader();
            }
            panelEcranCenter.updatePanel();
            panelEcranCenter.gestionnaireCards.show(panelEcranCenter, "menu");
        }
    }


    /**
     * Listener du bouton éteindre
     */
    class btnShutDownListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    }