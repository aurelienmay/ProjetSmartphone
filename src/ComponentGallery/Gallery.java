package ComponentGallery;

import ComponentIcon.IconButton;
import ComponentGallery.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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

    JComboBox list ;

    public Gallery() throws IOException {
        setLayout(new BorderLayout());
        panelCenter.setLayout(new FlowLayout(15, 25, 15));
        panelWestofPanelNorth.setLayout(new FlowLayout(10,10,2));

        createGalleryPictures();

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
        Object[] listeDeroulante = new Object[]{"1", "2", "3"};
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
            String sourcePath = "" ;
            String fileType = "" ;
            String fileDestination = "";
            if(e.getSource()==addPictureBtn){
                JFileChooser fc = new JFileChooser();
                fc.setAcceptAllFileFilterUsed(false);
                fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "jpeg"));
                int i=fc.showOpenDialog(null);
                if(i==JFileChooser.APPROVE_OPTION){
                File selectedFile = fc.getSelectedFile();
                fileType = fc.getSelectedFile().toString();
                sourcePath = selectedFile.getAbsolutePath();
                }
            }
            compteur = pictures.size() ;
            try {
                pictures.add(new IconButton(sourcePath));
                adaptPictureLength(large, compteur);
                panelCenter.add(pictures.get(compteur));
            }catch (IOException io){}
            
            File source = new File(sourcePath);
            //File destination = new File("Gallery\\i" + compteur + fileType);
            fileDestination = "Gallery\\i" + compteur + fileType.substring(fileType.lastIndexOf("."),fileType.length()) ;
            File destination = new File(fileDestination);
            copyFile(source, destination);

            adaptPanelLength(listSelection);
        }
    }

    class listListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            listSelection = Integer.parseInt(list.getSelectedItem().toString());
            setPicturesDisposition(listSelection);
        }
    }

    public void setPicturesDisposition(int listSelection){
        try {
            switch (listSelection){
                case 3 :
                    marge = 8 ;
                    panelCenter.setLayout(new FlowLayout(15, 17, 15));
                    for (int i = 0; i < pictures.size(); i++) {
                        large = 70 ;
                        adaptPictureLength(large, i);
                        pictureToBeDeleted(i);
                    }

                    adaptPanelLength(listSelection);
                    break;

                case 2 :
                    marge = 11 ;
                    panelCenter.setLayout(new FlowLayout(15, 25, 15));
                    for (int i = 0; i < pictures.size(); i++) {
                        large = 100 ;
                        adaptPictureLength(large, i);
                        pictureToBeDeleted(i);
                    }

                    adaptPanelLength(listSelection);
                    break;

                case 1 :
                    marge = 15 ;
                    panelCenter.setLayout(new FlowLayout(15, 30, 15));
                    for (int i = 0; i < pictures.size(); i++) {
                        large = 220 ;
                        adaptPictureLength(large, i);
                        pictureToBeDeleted(i);
                    }

                    adaptPanelLength(listSelection);
                    break;
            }
        }catch (NullPointerException io){ }
    }

    public void adaptPanelLength(int listSelection){
        totalLength = 0 ;
        for(int i=0; i<pictures.size(); i++){
            //adaptPanel selon la listSelection (nombre de photo par ligne)
            switch (listSelection){
                case 1 :
                    totalLength += pictures.get(i).length ;
                    break;

                case 2 :
                    //Test pour ne pas sortir de l'arrayList
                    if(i < pictures.size()-1) {
                        if(pictures.get(i).length > pictures.get(i+1).length) {
                            totalLength += pictures.get(i).length;
                        }
                        else {
                            totalLength += pictures.get(i+1).length;
                        }
                    }else {
                        //Test si le nombre d'image est impaire (pour prendre la plus grande image de la dernière ligne)
                        if(pictures.size()%2 != 0){
                            totalLength += pictures.get(pictures.size()-1).length ;
                        }else {
                            testLastAndBeforeLast();
                        }
                    }
                    i++;
                    break;

                case 3 :
                    //Test pour ne pas sortir de l'arrayList
                    if(i+1 < pictures.size()-1) {
                        if (pictures.get(i).length > pictures.get(i + 1).length) {
                            if (pictures.get(i).length > pictures.get(i + 2).length) {
                                totalLength += pictures.get(i).length;
                            } else {
                                totalLength += pictures.get(i + 2).length;
                            }
                        }else {
                            if (pictures.get(i + 1).length > pictures.get(i + 2).length) {
                                totalLength += pictures.get(i + 1).length;
                            }else{
                                totalLength += pictures.get(i + 2).length;
                            }
                        }
                    }else {
                        if(pictures.size()%3 == 1) {
                            totalLength += pictures.get(pictures.size()-1).length;
                        }
                        if(pictures.size()%3 == 2){
                            if(pictures.get(pictures.size() - 1).length > pictures.get(pictures.size() - 2).length){
                                totalLength += pictures.get(pictures.size() - 1).length;
                            }else{
                                totalLength += pictures.get(pictures.size() - 2).length;
                            }
                        }
                        if(pictures.size()%3 == 0){
                            if (pictures.get(pictures.size()-3).length > pictures.get(pictures.size()-2).length) {
                                if (pictures.get(pictures.size()-3).length > pictures.get(pictures.size()-1).length) {
                                    totalLength += pictures.get(pictures.size()-3).length;
                                } else {
                                    totalLength += pictures.get(pictures.size()-1).length;
                                }
                            }else {
                                testLastAndBeforeLast();
                            }
                        }
                    }
                    i += 2;
                    break;
            }
        }
        totalLength += pictures.size()*marge + marge ;
        panelCenter.setPreferredSize(new Dimension(panelCenterMaxLarge, totalLength));
        panelCenter.revalidate();
    }

    //Test utilisé dans plusieurs case (switch), éviter les doublures
    private void testLastAndBeforeLast() {
        if (pictures.get(pictures.size()-2).length > pictures.get(pictures.size()-1).length) {
            totalLength += pictures.get(pictures.size()-2).length;
        }else{
            totalLength += pictures.get(pictures.size()-1).length;
        }
    }

    //Méthode qui adapt la taille de l'image selon la listSelection de l'utilisation
    //Les images ont toutes la même largeur mais longueur différente d'où ce calcul qui taille l'image selon sa taille réelle
    public int adaptPictureLength(int large, int i){
        percentage = large*100/pictures.get(i).pictureLarge;
        length = (int) (pictures.get(i).pictureLength*(percentage/100));
        pictures.get(i).setIconButtonSize(large, length);
        return length ;
    }

    //Listener de l'IconButton (l'image) qui entoure l'image en rouge (set la bordure en rouge) image qu'on souhaite supprimé
    class pictureListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object o = e.getSource();

            for(int i=0; i<pictures.size(); i++){
                if(o == pictures.get(i)){
                    if(pictures.get(i).toBeDeleted){
                        pictures.get(i).setBorderPainted(false);
                        pictures.get(i).toBeDeleted = false ;
                    }else{
                        pictures.get(i).setBorder(BorderFactory.createLineBorder(Color.RED));
                        pictures.get(i).setBorderPainted(true);
                        pictures.get(i).toBeDeleted = true ;
                    }
                }
            }
        }
    }

    //Méthode qui garde l'image entouré en rouge (bordure en rouge) même lors de changement d'application (mais pas lors de shut down du smartphone, pas très utile de stocké cette information)
    public void pictureToBeDeleted(int i){
            if(pictures.get(i).toBeDeleted){
                pictures.get(i).setBorder(BorderFactory.createLineBorder(Color.RED));
                pictures.get(i).setBorderPainted(true);
            }
    }

    //Listener du bouton corbeille, qui fait appelle à la méthode updatePanelCenter
    class deletePictureBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

            for (int i=0; i<pictures.size(); i++){
                if(pictures.get(i).toBeDeleted){
                    deletePictureFile(pictures.get(i));
                }
            }
            updatePanelCenter();
        }
    }

    //Méthode qui supprime le fichier (l'image) dans le dossier Gallery du projet
    public void deletePictureFile(IconButton i){
        File f = new File(i.getFileLocation());
        f.delete();
    }

    //Méthode qui update le panelCenter, seulement lors de suppression d'images !
    public void updatePanelCenter(){
        pictures.clear();
        panelCenter.removeAll();
        try {
            createGalleryPictures();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problème à la méthode updateScrollPane.");
        }
        addPicturesToPanelCenter();
        setPicturesDisposition(listSelection);
    }

    //Méthode qui ajoute les images au panelCenter
    public void addPicturesToPanelCenter(){
        for(int i=0; i<pictures.size(); i++){
            pictures.get(i).addActionListener(new pictureListener());
            panelCenter.add(pictures.get(i));
        }
    }

    //Méthode qui crée la gallery et l'ajoute au tableau pictures (qui est ensuite ajouté au panel méthode au dessus^^^)
    public void createGalleryPictures() throws IOException {
        File galleryFolder = new File("Gallery");
        String liste[] = galleryFolder.list();

        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                String t = liste[i].substring(1);
                pictures.add(new IconButton("Gallery\\i"+ t));
            }
        } else {
            System.err.println("Nom de repertoire invalide");
        }
    }

    //Méthode qui copie l'image selectionné par l'utilisateur pour la mettre dans le dossier Gallery
    public boolean copyFile(File source, File destination){
        try {
            // Declaration et ouverture des flux
            java.io.FileInputStream sourceFile = new java.io.FileInputStream(source);

            try {
                java.io.FileOutputStream destinationFile = null;

                try {
                    destinationFile = new FileOutputStream(destination);

                    // Lecture par segment de 0.5Mo
                    byte buffer[] = new byte[512 * 1024];
                    int nbLecture;

                    while ((nbLecture = sourceFile.read(buffer)) != -1) {
                        destinationFile.write(buffer, 0, nbLecture);
                    }
                } finally {
                    destinationFile.close();
                }
            } finally {
                sourceFile.close();
            }
        }catch (FileNotFoundException f){
            f.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
            return false; // Erreur
        }

        return true; // Résultat OK
    }

}