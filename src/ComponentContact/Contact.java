package ComponentContact;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Scrollable;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.http.WebSocket;
import java.util.Arrays;
import java.util.Objects;


/**
 * Class Contact application contact
 *
 * @author Favre Léonard
 * @version 11.0
 */
public class Contact extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;
    //Usefull panel
    private final JPanel boutoncontact = new JPanel();
    private final JPanel panelcontrol = new JPanel();
    public final JPanel panelCenter = new JPanel();
    // Panel for the Cardlayout
    private final JPanel accueil = new JPanel();
    private final CardLayout cards = new CardLayout();
    //String
    private final String pathtoExplore= "contact\\";
    //Dimension
    private final Dimension boutonDim = new Dimension(280,50);
    //Button
    private final JButton newContact = new JButton("new Contact");
    private final JButton back = new JButton("back");
    private final JButton invisible= new JButton();
    //Scrollpane

    // background color
    private final Color emeraude = new Color(1,215,88);
    //Font fontbouton = new Font("Verdana",Font.BOLD,15);
    private boolean openInContact = false ;
    private final Ecouteur ecouteur=new Ecouteur();

    /**
     * Constructeur Contact
     */
   public  Contact() {
        //new panel for the cardlayout

        // New listener on button newContact
        back.addActionListener(ecouteur);
        newContact.addActionListener(ecouteur);
        // change background for the buttons
        newContact.setBackground(emeraude);

        panelcontrol.setLayout(new GridLayout(1,3,10,0));
        panelcontrol.add(back);
        panelcontrol.add(newContact);
        panelcontrol.add(invisible);
        back.setVisible(false);
        invisible.setVisible(false);
        add(panelcontrol,BorderLayout.NORTH);

    try {
        File personne = new File(pathtoExplore);
        File[] file = personne.listFiles();
        Arrays.sort(Objects.requireNonNull(file));
        //Int
        int contactsum = file.length;
        JButton[] listebouton = new JButton[contactsum];
        for (int i = 0; i < listebouton.length; i++) {
            String name;
            String surname;
            String image;
            BufferedReader contactread = null;
            contactread = new BufferedReader(new FileReader(file[i]));
            name = contactread.readLine();
            surname = contactread.readLine();
            contactread.readLine();
            image = contactread.readLine();
            JButton people = new JButton(name + " " + surname);
            people.setPreferredSize(boutonDim);
            ImageIcon icon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
            people.setIcon(icon);
            people.setName(file[i].getName());
            listebouton[i] = people;
            people.addActionListener(this);
            boutoncontact.setLayout(new GridLayout(contactsum, 1, 0, 0));
            boutoncontact.setMaximumSize(getMaximumSize());
            boutoncontact.add(people);
            contactread.close();
        }
    }
    catch(IOException e){
        e.printStackTrace();
    }

        JScrollPane bar = new JScrollPane(boutoncontact);
        bar.setVerticalScrollBarPolicy(bar.VERTICAL_SCROLLBAR_AS_NEEDED);
        bar.revalidate();

        // lecture de boutons

        accueil.setLayout(cards);
        //accueil.setPreferredSize(new Dimension(300, 200));
        accueil.add(bar,"les contacts");

        add(accueil);
    }




    /**
     * Listener des boutons newContact et back
     */
    class Ecouteur extends Contactnew implements ActionListener {

        /**
         * méthode permettant d'accéder au cardlayout de la création de contact
         * et de revenir sur le cardLayout précédent tout en réactualisant les contacts
         *
         *
         * @param e event du bouton
         */
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if(obj == newContact) {
                    Contactnew ajouter = new Contactnew();
                    accueil.add(ajouter, "ajout d'un contact");
                    cards.show(accueil, "ajout d'un contact");
                    newContact.setVisible(false);
                    back.setVisible(true);
                    save.setVisible(true);
            }
            if(obj == back) {
                cards.show(accueil,"les contacts");
                newContact.setVisible(true);
                back.setVisible(false);
                save.setVisible(false);
                try {
                    File personne2 = new File(pathtoExplore);
                    File[] file2 = personne2.listFiles();
                    Arrays.sort(Objects.requireNonNull(file2));
                    int contactsum2 = file2.length;
                    updatebuttonlist(contactsum2,file2);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * méthode permettant l'actualisation des contacts
     *
     * @param contactsum2 somme des contacts
     * @param file2 tableau des boutons
     * @throws IOException gère l'exception lors de la lecture des fichiers
     */
    private void updatebuttonlist(int contactsum2, File[] file2) throws IOException {
        JButton[] listebouton2 = new JButton[contactsum2];
        boutoncontact.removeAll();
        for(int i=0; i<listebouton2.length;i++) {
            String name2;
            String surname2;
            String image;
            BufferedReader contactread = null;
            try {
                contactread = new BufferedReader(new FileReader(file2[i]));
                name2=contactread.readLine();
                surname2=contactread.readLine();
                contactread.readLine();
                image=contactread.readLine();
                JButton people = new JButton(name2+" "+surname2);
                people.addActionListener(this);
                people.setPreferredSize(boutonDim);
                ImageIcon icon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                people.setIcon(icon);
                people.setName(file2[i].getName());
                listebouton2[i]=people;
                boutoncontact.setLayout(new GridLayout(contactsum2,1,0,0));
                boutoncontact.setMaximumSize(getMaximumSize());
                boutoncontact.add(people);
                contactread.close();
            }
            catch(FileNotFoundException e) {
                System.out.println("fichier introuvable");
            }
        }
    }

    /**
     * méthode permettant l'accès aux informations des contacts
     *
     * @param e evenement des boutons de contact
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String nombouton = ((JButton) e.getSource()).getName();
        Contactinfo contactinfo = new Contactinfo(nombouton);
        accueil.add(contactinfo,"info d'un contact");
        cards.show(accueil,"info d'un contact");

        newContact.setVisible(false);
        back.setVisible(true);
    }


}
