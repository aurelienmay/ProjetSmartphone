package ComponentContact;

import ComponentGallery.Gallery;
import ComponentIcon.IconButton;
import ComponentSettings.Settings;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

import javax.swing.*;

public class Contactinfo extends JPanel implements ActionListener{
    int largeur = 100, hauteur=20;
    JPanel info = new JPanel();
    JPanel controlbuton = new JPanel();
    JPanel panelBG = new JPanel();
    JPanel pContact = new JPanel();
    JTextField nom = new JTextField();

    String name;
    JTextField prenom = new JTextField();
    String surname;
    JTextField numero = new JTextField();
    String number;
    JButton save = new JButton("save");
    JButton modify = new JButton("modify");
    JButton delete = new JButton("delete");
    String nomfichier,nomimage;
    String pathtoExplore= "contact\\";
    Iconlistener ecouteurimage = new Iconlistener();
    private final CardLayout cardManager = new CardLayout();
    private final Font titleFont = new Font("Arial", Font.BOLD, 20);

    Contactinfo(String filename){
        panelBG.setLayout(cardManager);
        pathtoExplore+=filename;
        nomfichier=pathtoExplore;

        try {
            File f = new File(pathtoExplore);
            BufferedReader buff  = new BufferedReader(new FileReader(f));
            name=buff.readLine();
            nom.setText(name);
            surname=buff.readLine();
            prenom.setText(surname);
            number= buff.readLine();
            numero.setText(number);
            nomimage= buff.readLine();
            buff.close();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        IconButton icon = new IconButton(nomimage,200,200);

        nom.disable();
        prenom.disable();
        numero.disable();
        nom.setPreferredSize(new Dimension(largeur,hauteur));
        prenom.setPreferredSize(new Dimension(largeur,hauteur));
        numero.setPreferredSize(new Dimension(largeur,hauteur));

        delete.addActionListener(this);
        modify.addActionListener(this);
        save.addActionListener(this);
        icon.addActionListener(ecouteurimage);

        save.setVisible(false);
        controlbuton.add(delete);
        controlbuton.add(modify);
        controlbuton.add(save);

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        info.setLayout(gb);
        gbc.gridx = 1;


        gb.setConstraints(icon,gbc);
        gb.setConstraints(nom,gbc);
        gb.setConstraints(prenom,gbc);
        gb.setConstraints(numero,gbc);
        info.add(icon);
        info.add(nom);
        info.add(prenom);
        info.add(numero);

        //this.setLayout(new BorderLayout());
        JPanel test = new JPanel();
        JLabel lable = new JLabel("alsakdlp");
        test.add(lable);
        //pContact.add(info,BorderLayout.CENTER);
        //pContact.add(controlbuton,BorderLayout.SOUTH);
        this.add(controlbuton,BorderLayout.SOUTH);
        System.out.println("ici");
        panelBG.add(info, "contactInfo");
        panelBG.add(test, "essai");
        cardManager.show(panelBG, "contactInfo");
    }

    protected boolean savecontactmodification() {
            boolean test = true;
        String name = nom.getText();
        String surname = prenom.getText();
        String number = numero.getText();
            try {
                if (legitcontact(test)) {
                    File file = new File(nomfichier);
                    PrintWriter output = new PrintWriter(file);
                    output.println(name);
                    output.println(surname);
                    output.println(number);
                    output.println();
                    output.close();
                } else {
                    JOptionPane.showMessageDialog(info, "Veuilllez remplir les champs", "Inane warning", JOptionPane.WARNING_MESSAGE);
                    test = false;
                }
            } catch (FileNotFoundException e) {
                System.out.printf("ERROR: %s\n", e);
            } catch (IOException e) {
                System.out.printf("ERROR: %s\n", e);
            } catch (NumberFormatException e) {
                System.out.printf("ERROR: %s\n", e);
            }
            return test;
        }
        private boolean legitcontact(boolean test) {
            if(nom.getText().isBlank()|| nom.getText().contentEquals("nom")
                    ||prenom.getText().isBlank()||prenom.getText().contentEquals("prenom")
                    ||numero.getText().isBlank()||numero.getText().contentEquals("n° telephone")) {
                test=false;
            }
            return test;
        }

    class Iconlistener implements ActionListener{
            public void actionPerformed(ActionEvent e){


                IconButton ib = (IconButton) e.getSource();
                System.out.println("hello");
                ContactGallery cardGallery = new ContactGallery();
                panelBG.add(cardGallery, "gallery");
                cardManager.show(panelBG, "essai");
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object obj = e.getSource();
        if(obj == modify) {
            nom.enable();
            prenom.enable();
            numero.enable();
            save.setVisible(true);
            modify.setVisible(false);
            delete.setVisible(false);

        }
        if(obj == save) {
            savecontactmodification();
            nom.disable();
            prenom.disable();
            numero.disable();
            save.setVisible(false);
            modify.setVisible(true);
            delete.setVisible(true);

        }
        if (obj== delete) {
            File f = new File(pathtoExplore);
            f.delete();
        }
    }
    /**
     * Class qui fait appelle à la gallery
     */
    class ContactGallery extends JPanel{

        final Gallery gallery = new Gallery();

        final JPanel panelNorth = new JPanel();

        final JScrollPane scrollPane = new JScrollPane(gallery.panelCenter);

        final IconButton backBtn = new IconButton("Images\\Icons\\backbtn.png", 20, 20);

        final JLabel title = new JLabel("Gallery");

        /**
         * Constructeur de la card ContactGallery
         */
        ContactGallery(){
            setLayout(new BorderLayout());
            gallery.setOpenInContact(true);

            title.setFont(titleFont);
            //backBtn.addActionListener(new Contactinfo.btnBackListener());

            panelNorth.add(backBtn, BorderLayout.WEST);
            panelNorth.add(title, BorderLayout.NORTH);

            scrollPane.setBackground(Color.WHITE);
            scrollPane.setOpaque(true);

            add(panelNorth, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);
        }
    }
    class btnBackListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //settingsList.clearSelection();
            cardManager.show(panelBG, "contact");
        }
    }
}
