package ComponentGallery;

import ComponentEcran.PanelEcranCenter;
import ComponentIcon.IconButton;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Gallery extends JPanel {

    PanelEcranCenter pec ;

    Font policeTitre = new Font("Arial", Font.BOLD, 40);

    JPanel panelCenter = new JPanel();
    JPanel panelNorth = new JPanel();

    JPanel panelEastofPanelNorth = new JPanel();
    JPanel panelWestofPanelNorth = new JPanel();
    JPanel panelCenterofPanelNorth = new JPanel();

    JScrollPane scrollPane = new JScrollPane(panelCenter);

    JLabel title = new JLabel("Gallerie", JLabel.CENTER);
    IconButton addPicture = new IconButton("Images\\Icons\\add-image.png", 30,30);

    private int large = 100 ;
    private int length = 100 ;

    IconButton[] pictures = new IconButton[50];

    IconButton i1 = new IconButton("Gallery\\i1.jpg", large, length);
    IconButton i2 = new IconButton("Gallery\\i2.jpg", large, length);
    IconButton i3 = new IconButton("Gallery\\i3.jpg", large, length);
    IconButton i4 = new IconButton("Gallery\\i4.jpg", large, length);
    IconButton i5 = new IconButton("Gallery\\i5.jpg", large, length);
    IconButton i6 = new IconButton("Gallery\\i6.jpg", large, length);

    CardLayout cardManager = new CardLayout();
    FlowLayout fl = new FlowLayout(15,15,15);

    JComboBox list ;

    public Gallery(){
        pictures[0] =  i1;
        pictures[1] =  i2;
        pictures[2] =  i3;
        pictures[3] =  i4;
        pictures[4] =  i5;
        pictures[5] =  i6;

        setLayout(new BorderLayout());
        panelCenter.setLayout(cardManager);
        panelCenter.setLayout(fl);

        addPicture.addActionListener(new addPictureListener());

        panelEastofPanelNorth.add(addPicture, BorderLayout.WEST);
        panelEastofPanelNorth.setPreferredSize(new Dimension(50,50));
        panelEastofPanelNorth.setOpaque(true);
        panelNorth.add(panelEastofPanelNorth, BorderLayout.WEST);

        title.setFont(policeTitre);
        panelCenterofPanelNorth.add(title, BorderLayout.CENTER);
        panelNorth.add(panelCenterofPanelNorth, BorderLayout.CENTER);

        Object[] listeDeroulante = new Object[]{"25%", "50%", "75%", "100%"};
        list = new JComboBox(listeDeroulante);
        list.addActionListener(new listZoomListener());
        panelWestofPanelNorth.add(list, BorderLayout.EAST);
        panelNorth.add(panelWestofPanelNorth, BorderLayout.EAST);

        panelCenter.add(pictures[0]);
        panelCenter.add(pictures[1]);
        panelCenter.add(pictures[2]);
        panelCenter.add(pictures[3]);
        panelCenter.add(pictures[4]);
        panelCenter.add(pictures[5]);

        int cpt = 0 ;
        try {
            for (int i = 0; i < pictures.length; i++) {
                if (pictures[i].getText() != "") { // passe pas dans le if
                    cpt++;
                    System.out.println("la");
                }
            }
        }catch (NullPointerException io){}

        panelCenter.setPreferredSize(new Dimension(280, 260*5));
        panelNorth.setBackground(Color.WHITE);
        panelNorth.setOpaque(true);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setOpaque(true);

        add(panelNorth, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    class addPictureListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String filepath = "" ;
            if(e.getSource()==addPicture){
                JFileChooser fc = new JFileChooser();
                fc.setAcceptAllFileFilterUsed(false);
                fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
                int i=fc.showOpenDialog(null);
                if(i==JFileChooser.APPROVE_OPTION){
                File f = fc.getSelectedFile();
                //filepath = f.getPath();
                File selectedFile = fc.getSelectedFile();
                //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                filepath = selectedFile.getAbsolutePath();
                }
            }
            pictures[6] = new IconButton(filepath, 100, 100);
            panelCenter.add(pictures[6]);
            try {
                pec.gestionnaireCards.show(pec, "gallery");
            }catch (NullPointerException io){}
        }
    }

    class listZoomListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String t = list.getSelectedItem().toString();
            try {
                if (t == "25%") {
                    panelCenter.setLayout(new FlowLayout(5, 5, 5));
                    for (int i = 0; i < pictures.length; i++) {
                        pictures[i].setIconButtonSize(50, 50);
                    }
                    pec.gestionnaireCards.show(pec, "gallery");
                }
                if (t == "50%") {
                    panelCenter.setLayout(new FlowLayout(15, 15, 15));
                    for (int i = 0; i < pictures.length; i++) {
                        pictures[i].setIconButtonSize(75, 75);
                    }
                    pec.gestionnaireCards.show(pec, "gallery");
                }
                if (t == "75%") {
                    panelCenter.setLayout(new FlowLayout(25, 25, 25));
                    for (int i = 0; i < pictures.length; i++) {
                        pictures[i].setIconButtonSize(100, 100);
                    }
                    pec.gestionnaireCards.show(pec, "gallery");
                }
                if (t == "100%") {
                    panelCenter.setLayout(new FlowLayout(35, 35, 35));
                    for (int i = 0; i < pictures.length; i++) {
                        pictures[i].setIconButtonSize(200, 200);
                    }
                    panelCenter.setPreferredSize(new Dimension(280, 500*5));
                    pec.gestionnaireCards.show(pec, "gallery");
                }
            }catch (NullPointerException io){ }
        }
    }

    class pictureListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

        }
    }

}
