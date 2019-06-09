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

/**
 * Class Contactinfo
 *
 * @author Favre Léonard
 * @version 11.0
 */
public class Contactinfo extends JPanel implements ActionListener{
    private final int largeur = 100;
    private final int hauteur=20;
    private final JPanel info = new JPanel();
    private final JPanel controlbuton = new JPanel();
    private final JTextField nom = new JTextField();
    private String name;
    private final JTextField prenom = new JTextField();
    private String surname;
    private final JTextField numero = new JTextField();
    private String number;
    private final JButton save = new JButton("save");
    private final JButton modify = new JButton("modify");
    private final JButton delete = new JButton("delete");
    private final String nomfichier;
    private String nomimage;
    private String pathtoExplore= "contact\\";
    private final Font titleFont = new Font("Arial", Font.BOLD, 20);

    /**
     * Constructeur des informations de contact
     *
     * @param filename nom du fichier correspondant au bouton sur lequel l'utilisateur click
     */
    Contactinfo(String filename){
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
        } catch (IOException e) {
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

        this.setLayout(new BorderLayout());
        this.add(controlbuton,BorderLayout.SOUTH);
        this.add(info,BorderLayout.CENTER);


    }

    /**
     * méthode sauvegardant les modifications apportant au contact
     */
    private void savecontactmodification() {
            boolean test = true;
        String name = nom.getText();
        String surname = prenom.getText();
        String number = numero.getText();
            try {
                if (legitcontact(true)) {
                    File file = new File(nomfichier);
                    PrintWriter output = new PrintWriter(file);
                    output.println(name);
                    output.println(surname);
                    output.println(number);
                    output.println(nomimage);
                    output.close();
                } else {
                    JOptionPane.showMessageDialog(info, "Veuilllez remplir les champs", "Inane warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException | IOException e) {
                System.out.printf("ERROR: %s\n", e);
            }
    }

    /**
     * @param test valeur boolean true/false
     * @return true/false si le contact respect les entrées
     */
        private boolean legitcontact(boolean test) {
            if(nom.getText().isBlank()|| nom.getText().contentEquals("nom")
                    ||prenom.getText().isBlank()||prenom.getText().contentEquals("prenom")
                    ||numero.getText().isBlank()||numero.getText().contentEquals("n° telephone")) {
                test=false;
            }
            return test;
        }


    /**
     * méthode permettant d'afficher ou de cacher les boutons
     * et ou de permettre d'écrire dans les JTextfield
     *
     * @param e event des boutons modify/save/delete
     */
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
}
