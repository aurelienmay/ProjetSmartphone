package ComponentCalculatrice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculatrice extends JPanel {

    private String temp ;

    private Font policeEcranCalcul = new Font("Arial", Font.BOLD, 50);
    private Font policeCaracter = new Font("Arial", Font.BOLD, 17);

    private Float nombre, resultat ;
    private String operateur, plus ="+", moins="-", fois="*", divise="/";
    private Boolean operateurOnOff = false;
    private Boolean on = true, off = false;

    private String[] calculetteCaracter = { "AC", "+/-", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "="};

    //Dimension des boutons de la calculette, attention le bouton 0 (2x plus grand)
    private Dimension buttonsSize = new Dimension(60, 60);
    private Dimension button0Size = new Dimension(125, 60);
    private Dimension westEastEmptyPannelDim = new Dimension(10,200);

    private JButton[] tabBtn = new JButton[calculetteCaracter.length];

    private JPanel chiffreBtn = new JPanel();
    private JPanel panelEast = new JPanel();
    private JPanel panelWest = new JPanel();
    private JPanel panelEast2 = new JPanel();
    private JPanel panelWest2 = new JPanel();
    private JPanel panelNorth = new JPanel();

    private JLabel ecranChiffre = new JLabel("0");

    private boolean onoff = true ;

    public Calculatrice() {
        //taille du panel principal
        setPreferredSize(new Dimension(300, 550));
        setLayout(new BorderLayout());
        //setBackground(Color.orange);

        panelWest.setOpaque(onoff);
        panelWest.setPreferredSize(westEastEmptyPannelDim);
        panelWest.setBackground(Color.BLACK);

        panelEast.setOpaque(onoff);
        panelEast.setPreferredSize(westEastEmptyPannelDim);
        panelEast.setBackground(Color.BLACK);

        panelWest2.setOpaque(onoff);
        panelWest2.setPreferredSize(new Dimension(20,150));
        panelWest2.setBackground(Color.BLACK);

        panelEast2.setOpaque(onoff);
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

    public void buttonInitializer() {
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

        public void calculAuto() {
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

        public void testCalculAuto(){
            if(operateur == "/" || operateur == "*" || operateur == "+" || operateur == "-"){
                calculAuto();
            }
        }

    class PointListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String nombre = ecranChiffre.getText();
            if(!nombre.contains(".")){
                nombre += "." ;
                ecranChiffre.setText(nombre);
            }
        }
    }

    class EgalListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            ecranChiffre.setFont(policeEcranCalcul);

            //Test in case of division by 0
            if(Float.parseFloat(ecranChiffre.getText()) != 0){
                //if the dividend != 0, make the calculation
                calculAuto();
            }else{
                //else show "Erreur"
                ecranChiffre.setText("Erreur");
            }
        }
    }

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
            operateur = divise ;
            operateurOnOff = on ;
        }
    }

    class PlusListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //méthode qui test lors de changement d'opérateur durant le calcul
            testCalculAuto();
            nombre = Float.parseFloat(ecranChiffre.getText());
            operateur = plus ;
            operateurOnOff = on ;
        }
    }

    class MoinsListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //méthode qui test lors de changement d'opérateur durant le calcul
            testCalculAuto();
            nombre = Float.parseFloat(ecranChiffre.getText());
            operateur = moins ;
            operateurOnOff = on ;
        }
    }

    class FoisListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //méthode qui test lors de changement d'opérateur durant le calcul
            testCalculAuto();
            nombre = Float.parseFloat(ecranChiffre.getText());
            operateur = fois ;
            operateurOnOff = on ;
        }
    }

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
                System.out.println("je passe ici");
                ecranChiffre.setText("");
                ecranChiffre.setText(((JButton) e.getSource()).getText());
                operateurOnOff = off;
            }
        }
    }

    //Méthode qui set la size de l'écran de la calculette lorsque le chiffre ne passe pas sur celui-ci
    public void setLength(int length){
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
                ecranChiffre.setFont(new Font("Arial", Font.BOLD, 34));
                break;
            default:
                ecranChiffre.setFont(policeEcranCalcul);
                break;
        }
    }

    class PlusMoinsListener implements ActionListener{
        private float chiffre ;
        public void actionPerformed(ActionEvent e){
            temp = ecranChiffre.getText() ;
            chiffre = Float.parseFloat(temp);
            if(temp == "0" || temp == "-0"){
                if(temp == "0")
                    ecranChiffre.setText("-0");
                if(temp == "-0")
                    ecranChiffre.setText("0");
            }else{
                //inverser le signe
                chiffre *= -1 ;
                temp = "" + chiffre ;
                ecranChiffre.setText(temp);
            }
        }
    }

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

    class ACListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            ecranChiffre.setFont(policeEcranCalcul);
            operateurOnOff = off ;
            operateur = "" ;
            ecranChiffre.setText("0");
        }
    }
}