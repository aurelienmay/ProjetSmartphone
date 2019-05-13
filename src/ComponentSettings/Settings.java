package ComponentSettings;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ComposantIcon.*;

public class Settings extends JPanel implements ListSelectionListener {

    Font policeTitre = new Font("Arial", Font.BOLD, 50);
    Font policeCaracter = new Font("Arial", Font.BOLD, 18);

    JLabel settingsName = new JLabel("Settings");

    JPanel cardSettings = new JPanel();
    Informations cardInformations = new Informations();
    Wallpaper cardWallpaper = new Wallpaper();

    JPanel panelNorth = new JPanel();
    JPanel panelCenter = new JPanel();

    String settingsList[]= {"Informations smartphone", "Changer le fond d'écran"};

    JList list = new JList(settingsList);

    JPanel panelWest = new JPanel();
    Dimension westEastEmptyPannelDim = new Dimension(10,200);

    IconButton backBtn = new IconButton("Images\\Icons\\backbtn.png", 20, 20);

    CardLayout cardManager = new CardLayout();

    public Settings(){
        setLayout(new BorderLayout());
        panelCenter.setLayout(cardManager);

        //North Panel
        settingsName.setFont(policeTitre);
        list.addListSelectionListener(this::valueChanged);
        list.setFont(policeCaracter);

        backBtn.addActionListener(new btnBackListener());

        panelNorth.add(backBtn, BorderLayout.WEST);
        panelNorth.add(settingsName, BorderLayout.NORTH);
        cardSettings.add(list, BorderLayout.CENTER);

        panelCenter.add(cardSettings, "settings");
        panelCenter.add(cardInformations, "informations");
        panelCenter.add(cardWallpaper, "wallpaper");

        add(panelCenter, BorderLayout.CENTER);
        add(panelNorth, BorderLayout.NORTH);
    }

    public void valueChanged(ListSelectionEvent e){
        String t = list.getSelectedValue().toString();
        if(t == "Informations smartphone"){
            cardManager.show(panelCenter, "informations");
        }
        if(t == "Changer le fond d'écran"){
            cardManager.show(panelCenter, "wallpaper");
        }
    }

    class btnBackListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            cardManager.show(panelCenter, "settings");
        }
    }

}
