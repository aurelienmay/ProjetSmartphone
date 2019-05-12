package ComponentSettings;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JPanel implements ListSelectionListener {

    Font policeTitre = new Font("Arial", Font.BOLD, 50);
    Font policeCaracter = new Font("Arial", Font.BOLD, 20);

    JLabel settingsName = new JLabel("Settings");
    JLabel creators = new JLabel();

    JPanel cardSettings = new JPanel();
    JPanel cardInformations = new JPanel();
    JPanel cardWallpaper = new JPanel();

    String settingsList[]= {"Smartphone informations", "Change the wallpaper"};

    JList list = new JList(settingsList);

    Dimension westEastEmptyPannelDim = new Dimension(10,200);


    CardLayout cardManager = new CardLayout();

    public Settings(){
        setLayout(cardManager);

        //North Panel
        settingsName.setFont(policeTitre);
        list.addListSelectionListener(this::valueChanged);
        list.setFont(policeCaracter);

        //Smartphone informations
        creators.setText("<html><body>This smartphone was created by :<p><b>" +
                "<font size=\"-1\">Aurélien May</font></b><p>" +
                "<font size=\"-1\">Léonard Favre</font></body></html>" );
        creators.setFont(policeCaracter);
        creators.setHorizontalTextPosition(JLabel.CENTER);
        creators.setVerticalTextPosition(JLabel.CENTER);
        cardInformations.add(creators);

        cardSettings.add(settingsName, BorderLayout.NORTH);
        cardSettings.add(list, BorderLayout.CENTER);

        this.add(cardSettings, "settings");
        this.add(cardInformations, "informations");
        this.add(cardWallpaper, "wallpaper");
    }

    public void valueChanged(ListSelectionEvent e){
        String t = list.getSelectedValue().toString();
        if(t == "Smartphone informations"){
                cardManager.show(this, "informations");
        }
    }

}
