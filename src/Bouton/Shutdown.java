package Bouton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;
import ComposantIcon.*;
import java.lang.String;
public class Shutdown extends JPanel{

    int large=50,length=50;
    Icon icone =new Icon("src//Shut down.png",large,length);
    JButton buttonexit = new JButton(icone);
    public Shutdown () {
        setPreferredSize(new Dimension(large, length));
        Ecouteur ecouteur=new Ecouteur();
        buttonexit.addActionListener(ecouteur);
        //this.setLocationRelativeTo(null);
        //setUndecorated(true);
        buttonexit.setSize(50,50);
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
