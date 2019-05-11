package ComponentSettings;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JPanel {

    Font policeTitre = new Font("Arial", Font.BOLD, 50);
    Font policeCaracter = new Font("Arial", Font.BOLD, 17);

    JLabel settingsName = new JLabel("Settings");
    JLabel creators = new JLabel("This smartphone was created by" + "\n" + "Aurélien May and Léonard Favre");

    JPanel smartphoneInformations = new JPanel();

    String choices[]= {"Smartphone informations", "Change the wallpaper"};

    JList list = new JList(choices);

    public Settings(){
        setLayout(new BorderLayout());

        //North Panel
        settingsName.setFont(policeTitre);
        list.addListSelectionListener(new smartphoneInfosListener());

        //Smartphone informations
        creators.setText("<html><body>This smartphone was created by :<p><b>" +
                "<font size=\"-1\">Aurélien May</font></b><p>" +
                "<font size=\"-1\">Léonard Favre</font></body></html>" );
        creators.setFont(policeCaracter);
        smartphoneInformations.add(creators);

        add(settingsName, BorderLayout.NORTH);
        add(list, BorderLayout.CENTER);
    }

    class smartphoneInfosListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e){
            String t = list.getSelectedValue().toString();
            if(t == "Smartphone informations"){
                add(smartphoneInformations, BorderLayout.CENTER);
            }
        }
    }

}
