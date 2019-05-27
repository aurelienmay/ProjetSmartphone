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
    JPanel freePanel = new JPanel();

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

    IconButton i1 = new IconButton("Gallery\\i1.jpg");
    IconButton i2 = new IconButton("Gallery\\i2.jpg");
    IconButton i3 = new IconButton("Gallery\\i3.jpg");
    IconButton i4 = new IconButton("Gallery\\i4.jpg");
    IconButton i5 = new IconButton("Gallery\\i5.jpg");
    IconButton i6 = new IconButton("Gallery\\i6.jpg");
    IconButton i7 = new IconButton("Gallery\\i7.jpg");
    IconButton i8 = new IconButton("Gallery\\i8.jpg");
    IconButton i9 = new IconButton("Gallery\\i9.jpg");
    IconButton i10 = new IconButton("Gallery\\i10.jpg");
    IconButton i11 = new IconButton("Gallery\\i11.png");
    IconButton i12 = new IconButton("Gallery\\i12.jpg");
    IconButton i13 = new IconButton("Gallery\\i13.jpeg");
    IconButton i14 = new IconButton("Gallery\\i14.jpeg");
    IconButton i15 = new IconButton("Gallery\\i15.jpg");
    IconButton i16 = new IconButton("Gallery\\i16.jpg");

    JComboBox list ;

    public Gallery() throws IOException {
        setLayout(new BorderLayout());
        panelCenter.setLayout(new FlowLayout(15, 25, 15));
        panelWestofPanelNorth.setLayout(new FlowLayout(10,10,2));

        pictures.addAll(Arrays.asList(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16));

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
        list.addActionListener(new listZoomListener());
        panelEastofPanelNorth.setPreferredSize(new Dimension(40,70));
        panelEastofPanelNorth.add(list, BorderLayout.EAST);
        panelNorth.add(panelEastofPanelNorth, BorderLayout.EAST);

        addPicturesToPanelCenter();

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
                File f = fc.getSelectedFile();
                //filepath = f.getPath();
                File selectedFile = fc.getSelectedFile();
                //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
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

    class listZoomListener implements ActionListener {
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
                }
            }

        }
    }

    class deletePictureBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            for (int i=0; i<pictures.size(); i++){
                pictures.get(i).getBorder();
            }
        }
    }

    public void addPicturesToPanelCenter(){
        for(int i=0; i<pictures.size(); i++){
            pictures.get(i).addActionListener(new pictureListener());
            panelCenter.add(pictures.get(i));

        }
    }

}