package main.ComponentGallery;

import main.ComponentIcon.IconButton;

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
import java.util.Objects;

/**
 * Class Gallery, application Gallery
 */
public class Gallery extends JPanel {

    public final JPanel panelCenter = new JPanel();

    private final IconButton addPictureBtn = new IconButton("Images\\Icons\\add-image.png", 30,30);

    private int large = 90 ;
    private int totalLength ;
    private int marge = 11 ;
    private int listSelection = 2 ;

    private final ArrayList<IconButton> pictures = new ArrayList<>();

    private final JComboBox list ;

    /**
     * Constructeur de la Gallery
     */
    @SuppressWarnings("MagicConstant")
    public Gallery() {
        setLayout(new BorderLayout());
        panelCenter.setLayout(new FlowLayout(15, 25, 15));
        JPanel panelWestofPanelNorth = new JPanel();
        panelWestofPanelNorth.setLayout(new FlowLayout(10,10,2));

        createGalleryPictures();

        addPictureBtn.addActionListener(new addPictureListener());

        //West of panelNorth
        panelWestofPanelNorth.setBackground(Color.WHITE);
        panelWestofPanelNorth.add(addPictureBtn);
        IconButton deletePictureBtn = new IconButton("Images\\Icons\\delete.png", 30, 30);
        panelWestofPanelNorth.add(deletePictureBtn);
        panelWestofPanelNorth.setPreferredSize(new Dimension(40,70));
        //panelWestofPanelNorth.setBackground(new Color(0,0,0,0));
        panelWestofPanelNorth.setOpaque(true);
        JPanel panelNorth = new JPanel();
        panelNorth.add(panelWestofPanelNorth, BorderLayout.WEST);

        //Center of panelNorth
        JLabel title = new JLabel("Gallerie", JLabel.CENTER);
        Font policeTitre = new Font("Arial", Font.BOLD, 40);
        title.setFont(policeTitre);
        JPanel panelCenterofPanelNorth = new JPanel();
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
        JPanel panelEastofPanelNorth = new JPanel();
        panelEastofPanelNorth.setPreferredSize(new Dimension(40,70));
        panelEastofPanelNorth.add(list, BorderLayout.EAST);
        panelNorth.add(panelEastofPanelNorth, BorderLayout.EAST);

        addPicturesToPanelCenter();

        deletePictureBtn.addActionListener(new deletePictureBtnListener());

        panelNorth.setBackground(Color.WHITE);
        panelNorth.setOpaque(true);
        JScrollPane scrollPane = new JScrollPane(panelCenter);
        //scrollPane.setBackground(Color.yellow);
        //scrollPane.setOpaque(true);

        adaptPanelLength(listSelection);

        add(panelNorth, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Listener du bouton d'ajout d'une image
     */
    class addPictureListener implements ActionListener{
        /**
         * Méthode qui va donner la possibilité à l'utilisateur d'ajouter
         * une image (IconButton) qui sera copiée et renommée dans le dossier
         * du projet
         * Fait appelle à copyFile, adaptPictureLength et adaptPanelLength
         *
         * @param e event du bouton
         */
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
            int compteur = pictures.size();
            try {
                pictures.add(new IconButton(sourcePath));
                adaptPictureLength(large, compteur);
                panelCenter.add(pictures.get(compteur));
            }catch (IOException io){
                System.out.println("Impossible d'ajouter l'image à la gallerie.");
            }
            
            File source = new File(sourcePath);
            try {
                fileDestination = "Gallery\\i" + (compteur+1) + fileType.substring(fileType.lastIndexOf("."));
            }catch (StringIndexOutOfBoundsException s){
                System.out.println("Impossible de créer la destination du fichier.");
            }
            File destination = new File(fileDestination);
            copyFile(source, destination);

            adaptPanelLength(listSelection);
        }
    }

    /**
     * Listener de la list selon l'affichage choisir par l'utilisateur
     */
    class listListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            listSelection = Integer.parseInt(Objects.requireNonNull(list.getSelectedItem()).toString());
            setPicturesDisposition(listSelection);
        }
    }

    /**
     * Met à jour la disposition des images sur le panel selon la list choisi
     * et qui fait appelle aux méthode adapatPictureLength, pictureToBeDeleted
     * et adaptPanelLength
     *
     * @param listSelection numéro de la liste choisi par l'utilisateur
     */
    private void setPicturesDisposition(int listSelection){
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
        }catch (NullPointerException io){
            io.printStackTrace();
        }
    }

    /**
     * Méthode qui adapat la longueur du panel (pour la scrollPane)
     * selon la longueur des images et du choix d'affichage de l'utilisateur
     *
     * @param listSelection numéro de la liste choisi par l'utilisateur
     */
    private void adaptPanelLength(int listSelection){
        totalLength = 0 ;
        for(int i=0; i<pictures.size(); i++){
            /*
            adaptPanel selon la listSelection (nombre de photo par ligne)
            le switch va regarder le numéro de la liste sélectionné
            puis va compter l'image la plus longue de chaque ligne
            */

            switch (listSelection){
                case 1 :
                    totalLength += pictures.get(i).length ;
                    break;

                case 2 :
                    //test pour ne pas sortir de l'arrayList
                    if(i < pictures.size()-1) {
                        if(pictures.get(i).length > pictures.get(i+1).length) {
                            totalLength += pictures.get(i).length;
                        }
                        else {
                            totalLength += pictures.get(i+1).length;
                        }
                    }else {
                        //test si le nombre d'image est impaire (pour prendre la plus grande image de la dernière ligne)
                        if(pictures.size()%2 != 0){
                            totalLength += pictures.get(pictures.size()-1).length ;
                        }else {
                            testLastAndBeforeLast();
                        }
                    }
                    i++;
                    break;

                case 3 :
                    //test pour ne pas sortir de l'arrayList
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
        int panelCenterMaxLarge = 280;
        panelCenter.setPreferredSize(new Dimension(panelCenterMaxLarge, totalLength));
        panelCenter.revalidate();
    }

    /**
     * Méthode qui test la dernière et l'avant dernière image
     * Eviter les doublures car code identique à deux endroit de la
     * méthode ci-dessus : adaptPanelLength
     */
    private void testLastAndBeforeLast() {
        if (pictures.get(pictures.size()-2).length > pictures.get(pictures.size()-1).length) {
            totalLength += pictures.get(pictures.size()-2).length;
        }else{
            totalLength += pictures.get(pictures.size()-1).length;
        }
    }

    /**
     * Met à jour la taille (longueur) du panel selon le choix de l'utilisateur
     * Les images ont toutes la même largeur mais longueur différente d'où ce
     * calcul qui taille l'image selon sa taille réelle
     *
     * @param large largueur de l'image prédéfinie (selon le choix)
     * @param i "numéro" de l'image
     */
    private void adaptPictureLength(int large, int i){
        double percentage = large * 100 / pictures.get(i).pictureLarge;
        int length = (int) (pictures.get(i).pictureLength * (percentage / 100));
        pictures.get(i).setIconButtonSize(large, length);
    }

    /**
     * Class Listener de l'IconButton (image)
     * Entoure l'image en rouge (image à supprimée)
     */
    class pictureListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object o = e.getSource();

            for (IconButton picture : pictures) {
                if (o == picture) {
                    if (picture.toBeDeleted) {
                        picture.setBorderPainted(false);
                        picture.toBeDeleted = false;
                    } else {
                        picture.setBorder(BorderFactory.createLineBorder(Color.RED));
                        picture.setBorderPainted(true);
                        picture.toBeDeleted = true;
                    }
                }
            }
        }
    }

    /**
     * Méthode qui garde l'image entouré en rouge (bordure en rouge) même
     * lors de changement d'application (mais pas lors de shut down du
     * smartphone, pas très utile de stocké cette information)
     *
     * @param i "numéro" de l'image
     */
    private void pictureToBeDeleted(int i){
            if(pictures.get(i).toBeDeleted){
                pictures.get(i).setBorder(BorderFactory.createLineBorder(Color.RED));
                pictures.get(i).setBorderPainted(true);
            }
    }

    /**
     * Classe Listener du bouton corbeille
     * Fait appelle à la méthode deletePictureFile et updatePanelCenter
     */
    class deletePictureBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            for (IconButton picture : pictures) {
                if (picture.toBeDeleted) {
                    deletePictureFile(picture);
                }
            }
            updatePanelCenter();
        }
    }

    /**
     * Méthode qui supprime le fichier (l'image) dans le dossier Gallery du projet
     *
     * @param i "numéro" de l'image
     */
    private void deletePictureFile(IconButton i){
        File f = new File(i.getFileLocation());
        f.delete();
    }

    /**
     * Met à jour le panelCenter en appelant les méthodes :
     * - createGalleryPictures
     * - addPicturesToPanelCenter
     * - setPicturesDisposition
     * Méthode utilisée lors de la suppression d'une image
     */
    private void updatePanelCenter(){
        pictures.clear();
        panelCenter.removeAll();
        createGalleryPictures();
        addPicturesToPanelCenter();
        setPicturesDisposition(listSelection);
    }

    /**
     * Ajoute les images au panelCenter
     */
    private void addPicturesToPanelCenter(){
        for (IconButton picture : pictures) {
            picture.addActionListener(new pictureListener());
            panelCenter.add(picture);
        }
    }

    /**
     * Création de la gallery selon les images dans le dossier Gallery
     */
    private void createGalleryPictures(){
        File galleryFolder = new File("Gallery");
        String[] liste = galleryFolder.list();

        if (liste != null) {
            for (String s : liste) {
                String t = s.substring(1);
                try {
                    pictures.add(new IconButton("Gallery\\i" + t));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.err.println("Nom de repertoire invalide");
        }
    }

    /**
     * Méthode qui copie le file sélectionné et le dépose dans le dossier
     * Gallery du projet
     *
     * @param source source du fichier
     * @param destination destination du fichier
     */
    //Méthode qui copie l'image selectionné par l'utilisateur pour la mettre dans le dossier Gallery
    private void copyFile(File source, File destination){
        try {
            // Declaration et ouverture des flux

            try (java.io.FileInputStream sourceFile = new java.io.FileInputStream(source)) {
                try (FileOutputStream destinationFile = new FileOutputStream(destination)) {
                    // Lecture par segment de 0.5Mo
                    byte[] buffer = new byte[512 * 1024];
                    int nbLecture;
                    while ((nbLecture = sourceFile.read(buffer)) != -1) {
                        destinationFile.write(buffer, 0, nbLecture);
                    }
                }
            }
        }catch (FileNotFoundException f){
            System.out.println("Impossible de copier le fichier.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}