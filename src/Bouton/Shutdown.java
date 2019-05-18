package Bouton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Shutdown extends JPanel{

    int large=50,height=50;
    ImageIcon icone =new ImageIcon("Images//Shut down.png");

    JButton buttonexit = new JButton(icone);
    public Shutdown () {
        Image im = icone.getImage();
        im.getScaledInstance(large,height,Image.SCALE_DEFAULT);
        //setPreferredSize(new Dimension(large, height));
        Ecouteur ecouteur=new Ecouteur();
        buttonexit.addActionListener(ecouteur);
        //this.setLocationRelativeTo(null);
        //setUndecorated(true);
        //buttonexit.setSize(50,50);
        add(buttonexit,BorderLayout.NORTH);
        buttonexit.setBorderPainted(false);
        buttonexit.setContentAreaFilled(false);
        buttonexit.setFocusPainted(false);
        buttonexit.setOpaque(false);
        setBackground(new Color(0,0,0,0));
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        //pack();
    }
    class Ecouteur implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //dispose();
            System.exit(0);
        }
    }}
