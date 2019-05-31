package main.ComponentCalculatrice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Class Calculatrice, application Calculatrice
 *
 * @author aurelienmay
 * @version 12.0
 */
public class Calculatrice extends JPanel {

    private String temp ;

    private final Font policeEcranCalcul = new Font("Arial", Font.BOLD, 50);
    private final Font policeCaracter = new Font("Arial", Font.BOLD, 17);

    private Float nombre;
    private String operateur;
    private Boolean operateurOnOff = false;
    private final Boolean on = true;
    private final Boolean off = false;

    private final String[] calculetteCaracter = { "AC", "+/-", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "="};

    //Dimension des boutons de la calculette, attention le bouton 0 (2x plus grand)
    private final Dimension buttonsSize = new Dimension(60, 60);
    private final Dimension button0Size = new Dimension(125, 60);

    private final JButton[] tabBtn = new JButton[calculetteCaracter.length];

    private final JPanel chiffreBtn = new JPanel();

    private final JLabel ecranChiffre = new JLabel("0");

    /**
     * Constructeur
     */
    public Calculatrice() {
        //taille du panel principal
        setPreferredSize(new Dimension(300, 550));
        setLayout(new BorderLayout());
        //setBackground(Color.orange);

        boolean onoff = true;
        JPanel panelWest = new JPanel();
        panelWest.setOpaque(true);
        Dimension westEastEmptyPannelDim = new Dimension(10, 200);
        panelWest.setPreferredSize(westEastEmptyPannelDim);
        panelWest.setBackground(Color.BLACK);

        JPanel panelEast = new JPanel();
        panelEast.setOpaque(true);
        panelEast.setPreferredSize(westEastEmptyPannelDim);
        panelEast.setBackground(Color.BLACK);

        JPanel panelWest2 = new JPanel();
        panelWest2.setOpaque(true);
        panelWest2.setPreferredSize(new Dimension(20,150));
        panelWest2.setBackground(Color.BLACK);

        JPanel panelEast2 = new JPanel();
        panelEast2.setOpaque(true);
        panelEast2.setPreferredSize(new Dimension(20,150));
        panelEast2.setBackground(Color.BLACK);

        //Paramètre de l'écran de la calculette
        ecranChiffre.setFont(policeEcranCalcul);
        ecranChiffre.setHorizontalAlignment(JLabel.RIGHT);
        //ecranChiffre.setPreferredSize(new Dimension(300, 150));
        //ecranChiffre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ecranChiffre.setBackground(Color.black);
        ecranChiffre.setForeground(Color.white);
        ecranChiffre.setOpaque(true);

        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new BorderLayout());
        panelNorth.add(ecranChiffre, BorderLayout.CENTER);
        panelNorth.add(panelWest2, BorderLayout.WEST);
        panelNorth.add(panelEast2, BorderLayout.EAST);

        //Paramètre du tableau des boutons de la calculette
        //chiffreBtn.setPreferredSize(new Dimension(200, 480));
        chiffreBtn.setBackground(Color.black);

        buttonInitializer();
        add(panelEast, BorderLayout.EAST);
        add(panelWest, BorderLayout.WEST);
        add(panelNorth, BorderLayout.NORTH);
        //add(ecranChiffre, BorderLayout.NORTH);
        add(chiffreBtn, BorderLayout.CENTER);

    }

    /**
     * Méthode qui initialise touts les boutons selon leur listener, leurs taile
     * et leur couleur
     */
    private void buttonInitializer() {
        for (int i = 0; i < calculetteCaracter.length; i++) {
            //Ajout des caractères dans le tableau à "boutons"
            tabBtn[i] = new JButton(calculetteCaracter[i]);
            //Ajout du tableau dans le panel
            chiffreBtn.add(tabBtn[i]);
            //Taille, police et couleur des boutons par défauts
            tabBtn[i].setPreferredSize(buttonsSize);
            tabBtn[i].setFont(policeCaracter);
            tabBtn[i].setBackground(Color.gray);
            tabBtn[i].setForeground(Color.white);
            switch (i) {
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
                    tabBtn[i].addActionListener(new PourcentListener());
                    break;

                //Paramètre du bouton /
                case 3:
                    tabBtn[i].setBackground(Color.green);
                    tabBtn[i].setForeground(Color.black);
                    tabBtn[i].addActionListener(new DiviseListener());
                    break;

                //Paramètre du bouton *
                case 7:
                    tabBtn[i].setBackground(Color.green);
                    tabBtn[i].setForeground(Color.black);
                    tabBtn[i].addActionListener(new FoisListener());
                    break;

                //Paramètre du bouton -
                case 11:
                    tabBtn[i].setBackground(Color.green);
                    tabBtn[i].setForeground(Color.black);
                    tabBtn[i].addActionListener(new MoinsListener());
                    break;

                //Paramètre du bouton +
                case 15:
                    tabBtn[i].setBackground(Color.green);
                    tabBtn[i].setForeground(Color.black);
                    tabBtn[i].addActionListener(new PlusListener());
                    break;

                //Paramètre du bouton =
                case 18:
                    tabBtn[i].setBackground(Color.green);
                    tabBtn[i].setForeground(Color.black);
                    tabBtn[i].addActionListener(new EgalListener());
                    break;

                //Paramètre du bouton 0
                case 16:
                    tabBtn[i].setPreferredSize(button0Size);
                    tabBtn[i].addActionListener(new ChiffreListener());
                    break;

                //Paramètre du bouton .
                case 17 :
                    tabBtn[i].addActionListener(new PointListener());
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

    /**
     * Méthode qui fait les calculs selon l'opérateur sélectionné par
     * l'utilisateur
     */
    private void calculAuto() {
        if(operateur.equals("/")){
            nombre = nombre/Float.parseFloat(ecranChiffre.getText());
            setLength(nombre.toString().length());
            ecranChiffre.setText(String.valueOf(nombre));
            operateur = "" ;
        }
        if(operateur.equals("*")){
            nombre = nombre * Float.parseFloat(ecranChiffre.getText());
            setLength(nombre.toString().length());
            ecranChiffre.setText(String.valueOf(nombre));
            operateur = "" ;
        }
        if(operateur.equals("-")){
            nombre = nombre - Float.parseFloat(ecranChiffre.getText());
            setLength(nombre.toString().length());
            ecranChiffre.setText(String.valueOf(nombre));
            operateur = "" ;
        }
        if(operateur.equals("+")){
            nombre = nombre + Float.parseFloat(ecranChiffre.getText());
            setLength(nombre.toString().length());
            ecranChiffre.setText(String.valueOf(nombre));
            operateur = "" ;
        }
    }

    /**
     * Méthode qui test si un opérateur à déjà été sélectionné
     * en cas de répétiion d'opérateur durant le calcul
     * Fais appelle à calculAuto si un opérateur a déjà été sélectionné
     */
    private void testCalculAuto(){
        if(Objects.equals(operateur, "/")
                || Objects.equals(operateur, "*")
                || Objects.equals(operateur, "+")
                || Objects.equals(operateur, "-")){
            calculAuto();
        }
    }

    /**
     * Class Listener du bouton "."
     * Le point ne peut pas être inséré deux fois !
     *
     * Bouton qui insère le point (virgule) sur l'écran
     */
    class PointListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String nombre = ecranChiffre.getText();
            int length = ecranChiffre.getText().length();

            //Ajout du chiffre sur l'écran de la calculette
            if (!operateurOnOff) {
                if(length < 13)
                    if(!nombre.contains(".")){
                        nombre += "." ;
                        ecranChiffre.setText(nombre);
                    }
                //méthode qui change la police selon la longueur du nombre entré
                setLength(length);
            } else {
                ecranChiffre.setText("");
                ecranChiffre.setText(((JButton) e.getSource()).getText());
                operateurOnOff = off;
            }

        }
    }

    /**
     * Class Listener du bouton "="
     * Qui fait appelle à calculAuto
     *
     * Bouton qui fait le calcul demandé par l'utilisateur
     */
    class EgalListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            ecranChiffre.setFont(policeEcranCalcul);

            //test in case of division by 0
            if(Float.parseFloat(ecranChiffre.getText()) != 0){
                //if the dividend != 0, make the calculation
                calculAuto();
            }else{
                //else show "Erreur"
                ecranChiffre.setText("Erreur");
            }
        }
    }

    /**
     * Class Listener du bouton "/"
     * Fait appelle à testCalculAuto
     *
     * Bouton qui choisi l'opérateur de l'opération
     */
    class DiviseListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //méthode qui test lors de changement d'opérateur durant le calcul
            testCalculAuto();
            nombre = Float.parseFloat(ecranChiffre.getText());
            if(nombre != 0) {
                testCalculAuto();
            }else{
                ecranChiffre.setText("Erreur");
            }
            operateur = "/";
            operateurOnOff = on ;
        }
    }

    /**
     * Class Listener du bouton "+"
     * Fait appelle à testCalculAuto
     *
     * Bouton qui choisi l'opérateur de l'opération
     */
    class PlusListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //méthode qui test lors de changement d'opérateur durant le calcul
            testCalculAuto();
            nombre = Float.parseFloat(ecranChiffre.getText());
            operateur = "+";
            operateurOnOff = on ;
        }
    }

    /**
     * Class Listener du bouton "-"
     * Fait appelle à testCalculAuto
     *
     * Bouton qui choisi l'opérateur de l'opération
     */
    class MoinsListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //méthode qui test lors de changement d'opérateur durant le calcul
            testCalculAuto();
            nombre = Float.parseFloat(ecranChiffre.getText());
            operateur = "-";
            operateurOnOff = on ;
        }
    }

    /**
     * Class Listener du bouton "*"
     * Fait appelle à testCalculAuto
     *
     * Bouton qui choisi l'opérateur de l'opération
     */
    class FoisListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //méthode qui test lors de changement d'opérateur durant le calcul
            testCalculAuto();
            nombre = Float.parseFloat(ecranChiffre.getText());
            operateur = "*";
            operateurOnOff = on ;
        }
    }

    /**
     * Class Listener des chiffres (1,2,3,4,,6,7,8,9,0)
     * Ne laisse pas plus de 13 caractères à entrer
     * Fait appelle à la méthode setLength
     *
     * Bouton qui ajoute le chiffre sur l'écran de la calculette
     */
    class ChiffreListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int length = ecranChiffre.getText().length();
            ecranChiffre.setFont(policeEcranCalcul);

            //On affiche le chiffre additionnel dans le label
            String nombre = ((JButton) e.getSource()).getText();

            //Enlève le 0 du reset et du début, pour ne pas commencer à 08 par exemple
            if (!ecranChiffre.getText().equals("0")) {
                nombre = ecranChiffre.getText() + nombre;
            }

            //en cas de changement de signe, si la calculette affiche 0.0
            if (ecranChiffre.getText().equals("0.0")) {
                ecranChiffre.setText("0");
                nombre = ((JButton) e.getSource()).getText();
            }
            if (ecranChiffre.getText().equals("-0.0") || ecranChiffre.getText().equals("-0")) {
                ecranChiffre.setText("-0");
                nombre = "-" + ((JButton) e.getSource()).getText();
            }

            //Ajout du chiffre sur l'écran de la calculette
            if (!operateurOnOff) {
                if(length < 13)
                    ecranChiffre.setText(nombre);
                //méthode qui change la police selon la longueur du nombre entré
                setLength(length);
            } else {
                ecranChiffre.setText("");
                ecranChiffre.setText(((JButton) e.getSource()).getText());
                operateurOnOff = off;
            }
        }
    }

    /**
     * Class Listener du bouton "+/-"
     *
     * Bouton qui permet d'inverser le signe du chiffre
     */
    class PlusMoinsListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            temp = ecranChiffre.getText() ;
            float chiffre = Float.parseFloat(temp);
            if(Objects.equals(temp, "0") || Objects.equals(temp, "-0")){
                if(Objects.equals(temp, "0"))
                    ecranChiffre.setText("-0");
                if(Objects.equals(temp, "-0"))
                    ecranChiffre.setText("0");
            }else{
                //inverser le signe
                chiffre *= -1 ;
                temp = "" + chiffre;
                ecranChiffre.setText(temp);
            }
        }
    }

    /**
     * Class Listener du bouton "%"
     *
     * Bouton qui permet de faire des pourcentages
     */
    class PourcentListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //création d'un float temporaire pour garder en mémoire le chiffre précédent en cas de 100*8% (garder le 100 en mémoire)
            float t ;
            temp = ecranChiffre.getText();
            t = Float.parseFloat(temp);
            t /= 100 ;
            ecranChiffre.setText(String.valueOf(t));
        }
    }

    /**
     * Class Listener du bouton "AC"
     *
     * Bouton qui permet d'effacer le calcul en cours (nombre et opérateur selectionnés)
     */
    class ACListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            ecranChiffre.setFont(policeEcranCalcul);
            operateurOnOff = off ;
            operateur = "" ;
            ecranChiffre.setText("0");
        }
    }

    /**
     * Met à jour l'écran de la calculette selon le nombre de caractères insérés
     * pour permettre une plus grande quantité de caractères insérés
     *
     * @param length longueur du texte inséré
     */
    private void setLength(int length){
        ecranChiffre.setFont(policeEcranCalcul);
        switch (length) {
            case 9:
                ecranChiffre.setFont(new Font("Arial", Font.BOLD, 45));
                break;
            case 10:
                ecranChiffre.setFont(new Font("Arial", Font.BOLD, 40));
                break;
            case 11:
                ecranChiffre.setFont(new Font("Arial", Font.BOLD, 35));
                break;
            case 12:
                ecranChiffre.setFont(new Font("Arial", Font.BOLD, 34));
                break;
            case 13:
                //noinspection DuplicateBranchesInSwitch, la taille reste la même
                ecranChiffre.setFont(new Font("Arial", Font.BOLD, 34));
                break;
            default:
                ecranChiffre.setFont(policeEcranCalcul);
                break;
        }
    }
}