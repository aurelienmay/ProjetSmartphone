package ComponentGallery;

import ComponentIcon.IconButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Picture extends JPanel {

    IconButton backBtn = new IconButton("Images\\Icons\\backbtn.png", 20, 20);
    IconButton deleteBtn = new IconButton("Images\\Icons\\backbtn.png", 20, 20);

    Gallery g = new Gallery();



    public Picture(){
        g.panelNorth.add(backBtn, BorderLayout.WEST);
        g.panelNorth.add(deleteBtn, BorderLayout.EAST);


        add(g.panelNorth, BorderLayout.NORTH);
    }

    class btnBackListener implements ActionListener {
        public void actionPerformed(ActionEvent e){

        }
    }
}
