package ComponentContact;




import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.*;
public class Contactnew extends JPanel implements MouseListener{
    int largeur = 100, hauteur=20;
    JPanel info = new JPanel();
    JPanel controlbuton = new JPanel();
    JTextField nom = new JTextField("nom");
    JTextField prenom = new JTextField("prenom");
    JTextField numero = new JTextField("n° telephone");
    JButton save = new JButton("save");
    SaveListener saving = new SaveListener();
    String defaultcontactpicture = new String("image\\inconnu.jpg");
    Contactnew() {

        save.addActionListener(saving);
        //set things on the texts
        nom.addMouseListener(this);
        nom.setPreferredSize(new Dimension(largeur,hauteur));
        prenom.addMouseListener(this);
        prenom.setPreferredSize(new Dimension(largeur,hauteur));
        numero.addMouseListener(this);
        numero.setPreferredSize(new Dimension(largeur,hauteur));

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        info.setLayout(gb);
        gbc.gridx = 1;
        gb.setConstraints(nom,gbc);
        gb.setConstraints(prenom,gbc);
        gb.setConstraints(numero,gbc);
        info.add(nom);
        info.add(prenom);
        info.add(numero);
        controlbuton.add(save);


        this.setLayout(new BorderLayout());
        add(controlbuton,BorderLayout.SOUTH);
        add(info,BorderLayout.CENTER);
    }

    protected boolean savecontact(){
        boolean test=true;
        String name= nom.getText();
        String surname= prenom.getText();
        String number= numero.getText();
        int filenumber,valeurint;
        String valeur;
        try {
            File chiffre = new File("ser\\number.txt");
            BufferedReader buff = new BufferedReader(new FileReader(chiffre));
            valeur = buff.readLine();
            valeurint = Integer.parseInt(valeur);
            valeurint += 1;
            System.out.println(valeurint);
            buff.close();
            PrintWriter writer = new PrintWriter(chiffre);
            valeur = String.valueOf(valeurint);
            writer.write(valeur);
            writer.close();
            String fichiercontact = "contact\\" + name + surname + valeurint + ".txt";
            if (legitcontact(test)) {
                File file = new File(fichiercontact);
                PrintWriter output = new PrintWriter(file);
                output.println(name);
                output.println(surname);
                output.println(number);
                output.println(defaultcontactpicture);
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

    class SaveListener  implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if(obj == save) {
                savecontact();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        Object obj = e.getSource();
        if(obj==nom) {
            nom.setText("");
        }
        if(obj==prenom) {
            prenom.setText("");
        }
        if(obj==numero) {
            numero.setText("");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}






