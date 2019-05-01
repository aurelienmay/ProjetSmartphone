package ComposantCalculatrice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculatrice extends JFrame {

    Font policeEcranCalcul = new Font("Rockwell", Font.BOLD, 30);
    Font policeCaracter = new Font("Arial", Font.BOLD, 10);

    Float nombre, resultat ;
    String operateur, plus ="+", moins="-", fois="*", divise="/";

    String[] calculetteCaracter = { "AC", "+/-", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "="};

    //Dimension des boutons de la calculette, attention le bouton 0 (2x plus grand)
    Dimension buttonsSize = new Dimension(55, 40);
    Dimension button0Size = new Dimension(115, 40);

    JButton[] tabBtn = new JButton[calculetteCaracter.length];

    JPanel chiffreBtn = new JPanel();

    JLabel ecranChiffre = new JLabel("0");

    JLabel test = new JLabel("testeu");

    public Calculatrice() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(250, 275);
        setTitle("Calculette");

        //Paramètre de l'écran de la calculette
        ecranChiffre.setFont(policeEcranCalcul);
        ecranChiffre.setHorizontalAlignment(JLabel.RIGHT);
        ecranChiffre.setPreferredSize(new Dimension(220, 50));
        //ecranChiffre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ecranChiffre.setBackground(Color.black);
        ecranChiffre.setForeground(Color.white);
        ecranChiffre.setOpaque(true);
        //ecranChiffre.setBorder(Border border);

        //Paramètre du tableau des boutons de la calculette
        chiffreBtn.setPreferredSize(new Dimension(250, 230));
        chiffreBtn.setBackground(Color.black);

        buttonInitializer();
        add(ecranChiffre, BorderLayout.NORTH);
        add(chiffreBtn, BorderLayout.SOUTH);

        pack();
    }

    public void buttonInitializer(){
        for(int i=0; i<calculetteCaracter.length; i++){
            //Ajout des caractères dans le tableau à "boutons"
            tabBtn[i] = new JButton(calculetteCaracter[i]);
            //Ajout du tableau dans le panel
            chiffreBtn.add(tabBtn[i]);
            //Taille, police et couleur des boutons par défauts
            tabBtn[i].setPreferredSize(buttonsSize);
            tabBtn[i].setFont(policeCaracter);
            tabBtn[i].setBackground(Color.gray);
            tabBtn[i].setForeground(Color.white);
            switch(i){
                //Paramètre du bouton AC
                case 0:
                    tabBtn[i].setBackground(Color.lightGray);
                    tabBtn[i].setForeground(Color.black);
                    tabBtn[i].addActionListener(new ACListener());
                    break;

                //Paramètre du bouton +/-
                case 1:
                    tabBtn[i].setBackground(Color.lightGray);
                    tabBtn[i].setForeground(Color.black);
                    tabBtn[i].addActionListener(new PlusMoinsListener());
                    break;

                //Paramètre du bouton %
                case 2:
                    tabBtn[i].setBackground(Color.lightGray);
                    tabBtn[i].setForeground(Color.black);

                    break;

                //Paramètre du bouton /
                case 3:
                    tabBtn[i].setBackground(Color.orange);
                    tabBtn[i].setForeground(Color.white);

                    break;

                //Paramètre du bouton *
                case 7:
                    tabBtn[i].setBackground(Color.orange);
                    tabBtn[i].setForeground(Color.white);
                    tabBtn[i].addActionListener(new FoisListener());
                    break;

                //Paramètre du bouton -
                case 11:
                    tabBtn[i].setBackground(Color.orange);
                    tabBtn[i].setForeground(Color.white);

                    break;

                //Paramètre du bouton +
                case 15:
                    tabBtn[i].setBackground(Color.orange);
                    tabBtn[i].setForeground(Color.white);

                    break;

                //Paramètre du bouton =
                case 18:
                    tabBtn[i].setBackground(Color.orange);
                    tabBtn[i].setForeground(Color.white);
                    tabBtn[i].addActionListener(new EgalListener());
                    break;

                //Paramètre du bouton 0
                case 16:
                    tabBtn[i].setPreferredSize(button0Size);
                    tabBtn[i].addActionListener(new ChiffreListener());
                    break;

                //par défaut
                default:
                    //Listener de tous les boutons de type "chiffre" (par défaut)
                    tabBtn[i].addActionListener(new ChiffreListener());
                    break;
            }
            tabBtn[i].setOpaque(true);
        }

    }

    class EgalListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String temp = ecranChiffre.getText();
            float t = Float.parseFloat(temp);
            if(operateur == "*"){
                resultat = nombre*t ;
                temp = String.valueOf(resultat);
                ecranChiffre.setText(temp);
            }


        }
    }

    class FoisListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String temp = ecranChiffre.getText();
            nombre = Float.parseFloat(temp);
            operateur = fois ; //= "*"
            ecranChiffre.setText("0");
        }
    }

    class ChiffreListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //On affiche le chiffre additionnel dans le label
            String nombre = ((JButton) e.getSource()).getText();

            //Enlève le 0 du reset et du début, pour ne pas commencer à 08 par exemple
            if (!ecranChiffre.getText().equals("0"))
                nombre = ecranChiffre.getText() + nombre;

            //Ajout du chiffre sur l'écran de la calculette
            ecranChiffre.setText(nombre);
        }
    }

    class PlusMoinsListener implements ActionListener{
        private String temp ;
        private float chiffre ;
        public void actionPerformed(ActionEvent e){
            temp = ecranChiffre.getText() ;
            chiffre = Float.parseFloat(temp);
            //inverser le signe
            chiffre *= -1.0 ;
            temp = "" + chiffre ;
            ecranChiffre.setText(temp);
        }
    }

    class ACListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            ecranChiffre.setText("0");
        }
    }
}
