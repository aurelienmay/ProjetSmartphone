package ComposantMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

    public class Frame extends JFrame {

        JPanel panelSouth = new JPanel();
        JPanel panelNorth = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelCenter = new JPanel();

        public Frame(){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setUndecorated(true);

            putImage();
            boolean onoff = false ;

            panelCenter.setOpaque(onoff);
            //panelCenter.setPreferredSize();

            panelNorth.setOpaque(onoff);
            panelNorth.setPreferredSize(new Dimension(323, 66));

            panelWest.setOpaque(onoff);
            panelWest.setPreferredSize(new Dimension(14,100));

            panelEast.setOpaque(onoff);
            panelEast.setPreferredSize(new Dimension(14, 100));

            panelSouth.setOpaque(onoff);
            panelSouth.setPreferredSize(new Dimension(323, 66));

            JLabel test = new JLabel("test du cul");

            add(panelNorth, BorderLayout.NORTH);
            add(panelWest, BorderLayout.WEST);
            add(panelEast, BorderLayout.EAST);
            add(panelSouth, BorderLayout.SOUTH);
            add(test, BorderLayout.CENTER);

            pack();
        }

        public void putImage(){
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