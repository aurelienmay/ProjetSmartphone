package ComponentGallery;

import ComponentEcran.PanelEcranCenter;
import ComponentIcon.IconButton;

import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Gallery extends JPanel {

    Font policeTitre = new Font("Arial", Font.BOLD, 40);

    public JPanel panelCenter = new JPanel();
    public JPanel panelNorth = new JPanel();

    JPanel panelWestofPanelNorth = new JPanel();
    JPanel panelEastofPanelNorth = new JPanel();
    JPanel panelCenterofPanelNorth = new JPanel();

    public JScrollPane scrollPane = new JScrollPane(panelCenter);

    public JLabel title = new JLabel("Gallerie", JLabel.CENTER);
    IconButton addPictureBtn = new IconButton("Images\\Icons\\add-image.png", 30,30);
    IconButton deletePictureBtn = new IconButton("Images\\Icons\\delete.png", 30,30);

    private int large = 90 ;
    private int length = 150 ;
    double percentage ;
    private int compteur ;
    private int panelCenterMaxLarge = 280 ;
    private int totalLength ;
    private int marge = 11 ;
    private int listSelection = 2 ;

    ArrayList<IconButton> pictures = new ArrayList<IconButton>();

    File galleryFolder = new File("Gallery");
    String liste[] = galleryFolder.list();

    JComboBox list ;

    public Gallery() throws IOException {
        setLayout(new BorderLayout());
        panelCenter.setLayout(new FlowLayout(15, 25, 15));
        panelWestofPanelNorth.setLayout(new FlowLayout(10,10,2));

        createGalleryPictures(galleryFolder);

        addPictureBtn.addActionListener(new addPictureListener());

        //West of panelNorth
        panelWestofPanelNorth.setBackground(Color.WHITE);
        panelWestofPanelNorth.add(addPictureBtn);
        panelWestofPanelNorth.add(deletePictureBtn);
        panelWestofPanelNorth.setPreferredSize(new Dimension(40,70));
        //panelWestofPanelNorth.setBackground(new Color(0,0,0,0));
        panelWestofPanelNorth.setOpaque(true);
        panelNorth.add(panelWestofPanelNorth, BorderLayout.WEST);

        //Center of panelNorth
        title.setFont(policeTitre);
        panelCenterofPanelNorth.setBackground(Color.WHITE);
        panelCenterofPanelNorth.setPreferredSize(new Dimension(188,70));
        panelCenterofPanelNorth.setLayout(new FlowLayout(10,20,12));
        panelCenterofPanelNorth.add(title, BorderLayout.CENTER);
        panelNorth.add(panelCenterofPanelNorth, BorderLayout.CENTER);

        //East of panelNorth
        Object[] listeDeroulante = new Object[]{"1", "2", "3", "4"};
        list = new JComboBox(listeDeroulante);
        list.setSelectedIndex(1);
        list.addActionListener(new listListener());
        panelEastofPanelNorth.setPreferredSize(new Dimension(40,70));
        panelEastofPanelNorth.add(list, BorderLayout.EAST);
        panelNorth.add(panelEastofPanelNorth, BorderLayout.EAST);

        addPicturesToPanelCenter();

        deletePictureBtn.addActionListener(new deletePictureBtnListener());

        panelNorth.setBackground(Color.WHITE);
        panelNorth.setOpaque(true);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setOpaque(true);

        adaptPanelLength(listSelection);

        add(panelNorth, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    class addPictureListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String filepath = "" ;
            if(e.getSource()==addPictureBtn){
                JFileChooser fc = new JFileChooser();
                fc.setAcceptAllFileFilterUsed(false);
                fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "jpeg"));
                int i=fc.showOpenDialog(null);
                if(i==JFileChooser.APPROVE_OPTION){
                File selectedFile = fc.getSelectedFile();
                filepath = selectedFile.getAbsolutePath();
                }
            }
            compteur = pictures.size() ;
            try {
                pictures.add(new IconButton(filepath));
                adaptPictureLength(large, compteur);
                panelCenter.add(pictures.get(compteur));
            }catch (IOException io){}

            adaptPanelLength(listSelection);
        }
    }

    class listListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            listSelection = Integer.parseInt(list.getSelectedItem().toString());
            try {
                switch (listSelection){
                    case 4 :
                        marge = 5 ;
                        panelCenter.setLayout(new FlowLayout(11, 11, 11));
                        for (int i = 0; i < pictures.size() ; i++) {
                            large = 55 ;
                            adaptPictureLength(large, i);
                        }
                        adaptPanelLength(listSelection);
                        break;

                    case 3 :
                        marge = 8 ;
                        panelCenter.setLayout(new FlowLayout(15, 17, 15));
                        for (int i = 0; i < pictures.size(); i++) {
                            large = 70 ;
                            adaptPictureLength(large, i);
                        }

                        adaptPanelLength(listSelection);
                        break;

                    case 2 :
                        marge = 11 ;
                        panelCenter.setLayout(new FlowLayout(15, 25, 15));
                        for (int i = 0; i < pictures.size(); i++) {
                            large = 100 ;
                            adaptPictureLength(large, i);
                        }

                        adaptPanelLength(listSelection);
                        break;

                    case 1 :
                        marge = 15 ;
                        panelCenter.setLayout(new FlowLayout(15, 30, 15));
                        for (int i = 0; i < pictures.size(); i++) {
                            large = 220 ;
                            adaptPictureLength(large, i);
                        }

                        adaptPanelLength(listSelection);
                        break;
                }
            }catch (NullPointerException io){ }
        }
    }

    public void adaptPanelLength(int listSelection){
        totalLength = 0 ;
        for(int i=0; i<pictures.size(); i+=listSelection){
            totalLength += pictures.get(i).length ;
        }
        totalLength += pictures.size()*marge + marge ;
        panelCenter.setPreferredSize(new Dimension(panelCenterMaxLarge, totalLength));
        panelCenter.revalidate();
    }

    public int adaptPictureLength(int large, int i){
        percentage = large*100/pictures.get(i).pictureLarge;
        length = (int) (pictures.get(i).pictureLength*(percentage/100));
        pictures.get(i).setIconButtonSize(large, length);
        return length ;
    }

    class pictureListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object o = e.getSource();

            for(int i=0; i<pictures.size(); i++){
                if(o == pictures.get(i)){
                    pictures.get(i).setBorder(BorderFactory.createLineBorder(Color.RED));
                    pictures.get(i).setBorderPainted(true);
                    pictures.get(i).toBeDeleted = true ;
                }
            }

        }
    }

    class deletePictureBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            for (int i=0; i<pictures.size(); i++){
                if(pictures.get(i).toBeDeleted){
                    deletePictureFile(pictures.get(i));
                }
            }
            try {
                updateScrollPane();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.out.println("Problème à la suppression.");
            }
        }
    }

    public void addPicturesToPanelCenter(){
        for(int i=0; i<pictures.size(); i++){
            pictures.get(i).addActionListener(new pictureListener());
            panelCenter.add(pictures.get(i));
        }
    }

    public void deletePictureFile(IconButton i){
        File f = new File(i.getFileLocation());
        f.delete();
    }

    public void createGalleryPictures(File folder) throws IOException {
        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                String t = liste[i].substring(1);
                pictures.add(new IconButton("Gallery\\i"+ t));
            }
        } else {
            System.err.println("Nom de repertoire invalide");
        }
    }

    public void updateScrollPane() throws IOException {
        //suppression des données de l'arraylist pour mettre les nouvelles données
        pictures.clear();
        createGalleryPictures(galleryFolder);
    }

}