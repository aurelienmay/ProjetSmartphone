package ComponentGallery;

import ComponentIcon.IconButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gallery extends JPanel {

    Font policeTitre = new Font("Arial", Font.BOLD, 50);

    JPanel panelCenter = new JPanel();
    JPanel panelNorth = new JPanel();

    JScrollPane scrollPane = new JScrollPane(panelCenter);

    JLabel title = new JLabel("Gallerie", JLabel.CENTER);

    private int large = 100 ;
    private int length = 100 ;

    IconButton i1 = new IconButton("Gallery\\i1.jpg", large, length);
    IconButton i2 = new IconButton("Gallery\\i2.jpg", large, length);
    IconButton i3 = new IconButton("Gallery\\i3.jpg", large, length);
    IconButton i4 = new IconButton("Gallery\\i4.jpg", large, length);
    IconButton i5 = new IconButton("Gallery\\i5.jpg", large, length);
    IconButton i6 = new IconButton("Gallery\\i6.jpg", large, length);

    CardLayout cardManager = new CardLayout();

    public Gallery(){
        setLayout(new BorderLayout());
        panelCenter.setLayout(cardManager);
        //panelCenter.setLayout(new FlowLayout(15,15,15));

        title.setFont(policeTitre);
        panelNorth.add(title, BorderLayout.CENTER);

        //panelCenter.setPreferredSize(new Dimension(280, 260*5));

        panelCenter.add(i1);
        panelCenter.add(i2);
        panelCenter.add(i3);
        panelCenter.add(i4);
        panelCenter.add(i5);
        panelCenter.add(i6);

        add(panelNorth, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    class pictureListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

        }
    }

}
