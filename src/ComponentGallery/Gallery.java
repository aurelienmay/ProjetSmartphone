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
import java.io.IOException;
import java.util.ArrayList;

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

    private int large = 90 ;
    private int length = 150 ;
    double percentage ;
    private int compteur ;

    ArrayList<IconButton> picture = new ArrayList<IconButton>();

    IconButton i1 = new IconButton("Gallery\\i1.jpg");
    IconButton i2 = new IconButton("Gallery\\i2.jpg");
    IconButton i3 = new IconButton("Gallery\\i3.jpg");
    IconButton i4 = new IconButton("Gallery\\i4.jpg");
    IconButton i5 = new IconButton("Gallery\\i5.jpg");
    IconButton i6 = new IconButton("Gallery\\i6.jpg");

    CardLayout cardManager = new CardLayout();
    FlowLayout fl = new FlowLayout(25, 25, 25);
    GridLayout gl = new GridLayout(50,3);

    JComboBox list ;

    public Gallery() throws IOException {
        picture.add(i1);
        picture.add(i2);
        picture.add(i3);
        picture.add(i4);
        picture.add(i5);
        picture.add(i6);

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

        Object[] listeDeroulante = new Object[]{"1", "2", "3", "4"};
        list = new JComboBox(listeDeroulante);
        list.setSelectedIndex(1);
        list.addActionListener(new listZoomListener());
        panelWestofPanelNorth.add(list, BorderLayout.EAST);
        panelNorth.add(panelWestofPanelNorth, BorderLayout.EAST);

        panelCenter.add(picture.get(0));
        panelCenter.add(picture.get(1));
        panelCenter.add(picture.get(2));
        panelCenter.add(picture.get(3));
        panelCenter.add(picture.get(4));
        panelCenter.add(picture.get(5));

        panelCenter.setPreferredSize(new Dimension(280, picture.size()*100));
        //panelCenter.setMinimumSize(new Dimension(280,100));
        //panelCenter.setMaximumSize(new Dimension(280, 10000));
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
            compteur = picture.size() ;
            try {
                picture.add(new IconButton(filepath));
                panelCenter.add(picture.get(compteur));
                compteur++ ;
            }catch (IOException io){}

            panelCenter.setPreferredSize(new Dimension(280, picture.size()*150));

            try{
                pec.gestionnaireCards.show(pec, "gallery");
            }catch (NullPointerException io){}
        }
    }

    class listZoomListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String t = list.getSelectedItem().toString();
            try {
                if (t == "4") {
                    panelCenter.setLayout(new FlowLayout(11, 11, 11));
                    for (int i = 0; i < picture.size() ; i++) {
                        large = 55 ;
                        percentage = large*100/picture.get(i).pictureLarge;
                        length = (int) (picture.get(i).pictureLength*(percentage/100));
                        picture.get(i).setIconButtonSize(large, length);
                    }
                    pec.gestionnaireCards.show(pec, "gallery");
                }
                if (t == "3") {
                    panelCenter.setLayout(new FlowLayout(15, 15, 15));
                    for (int i = 0; i < picture.size(); i++) {
                        large = 70 ;
                        percentage = large*100/picture.get(i).pictureLarge;
                        length = (int) (picture.get(i).pictureLength*(percentage/100));
                        picture.get(i).setIconButtonSize(large, length);
                    }
                    panelCenter.setPreferredSize(new Dimension(280, picture.size()*50));
                    pec.gestionnaireCards.show(pec, "gallery");
                }
                //affichage de base
                if (t == "2") {
                    panelCenter.setLayout(fl);
                    for (int i = 0; i < picture.size(); i++) {
                        large = 100 ;
                        percentage = large*100/picture.get(i).pictureLarge;
                        length = (int) (picture.get(i).pictureLength*(percentage/100));
                        picture.get(i).setIconButtonSize(large, length);
                    }
                    panelCenter.setPreferredSize(new Dimension(280, picture.size()*100));
                    pec.gestionnaireCards.show(pec, "gallery");
                }
                if (t == "1") {
                    panelCenter.setLayout(new FlowLayout(15, 30, 15));
                    for (int i = 0; i < picture.size(); i++) {
                        large = 220 ;
                        percentage = large*100/picture.get(i).pictureLarge;
                        length = (int) (picture.get(i).pictureLength*(percentage/100));
                        picture.get(i).setIconButtonSize(large, length);
                    }
                    panelCenter.setPreferredSize(new Dimension(280, picture.size()*360));
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
