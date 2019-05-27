package ComponentSettings;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import ComponentEcran.PanelEcranCenter;
import ComponentGallery.Gallery;
import ComponentIcon.*;

public class Settings extends JPanel implements ListSelectionListener {

    Font policeTitre = new Font("Arial", Font.BOLD, 50);
    Font policeCaracter = new Font("Arial", Font.BOLD, 18);
    Font titleFont = new Font("Arial", Font.BOLD, 20);

    JLabel settingsName = new JLabel("Settings");

    JPanel cardSettings = new JPanel();
    Informations cardInformations = new Informations();
    Wallpaper cardWallpaper = new Wallpaper();
    JPanel cardGallery = new SettingsGallery();
    Gallery gallery = new Gallery();

    JPanel panelNorth = new JPanel();
    JPanel panelCenter = new JPanel();

    String settingsList[]= {"Informations système", "Changer le fond d'écran", "Gallery"};

    JList list = new JList(settingsList);

    JPanel panelWest = new JPanel();
    Dimension westEastEmptyPannelDim = new Dimension(10,200);

    CardLayout cardManager = new CardLayout();

    public Settings() throws IOException {
        setLayout(new BorderLayout());
        panelCenter.setLayout(cardManager);

        //North Panel
        settingsName.setFont(policeTitre);
        list.addListSelectionListener(this::valueChanged);
        list.setFont(policeCaracter);

        panelNorth.add(settingsName, BorderLayout.NORTH);

        cardSettings.add(panelNorth, BorderLayout.NORTH);
        cardSettings.add(list, BorderLayout.CENTER);

        panelCenter.add(cardSettings, "settings");
        panelCenter.add(cardInformations, "informations");
        panelCenter.add(cardWallpaper, "wallpaper");
        panelCenter.add(cardGallery, "gallery");

        add(panelCenter, BorderLayout.CENTER);
        //add(panelNorth, BorderLayout.NORTH);
    }

    public void valueChanged(ListSelectionEvent e){
        String t = list.getSelectedValue().toString();
        if(t == "Informations système"){
            cardManager.show(panelCenter, "informations");
        }
        if(t == "Changer le fond d'écran"){
            cardManager.show(panelCenter, "wallpaper");
        }
        if(t == "Gallery"){
            cardManager.show(panelCenter, "gallery");
        }
    }

    class btnBackListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            cardManager.show(panelCenter, "settings");
        }
    }

    //------------------------------------------------------------------------

    //INFORMATIONS SYSTEME
    public class Informations extends JPanel {

        Font policeCaracter = new Font("Arial", Font.BOLD, 14);
        Font createdBy = new Font("Arial", Font.BOLD, 16);

        JLabel text = new JLabel("Crée par :", JLabel.CENTER);
        JLabel creator1 = new JLabel("Aurélien May", JLabel.CENTER);
        JLabel creator2 = new JLabel("Léonard Favre", JLabel.CENTER);
        JLabel host = new JLabel(getHost(), JLabel.CENTER);
        JLabel ip = new JLabel(getIP(), JLabel.CENTER);

        JLabel title = new JLabel("Informations système");

        JPanel panelCenter = new JPanel();
        JPanel panelNorth = new JPanel();

        IconButton backBtn = new IconButton("Images\\Icons\\backbtn.png", 20, 20);

        public Informations(){
            setLayout(new BorderLayout());

            //Smartphone informations
            text.setFont(createdBy);
            creator1.setFont(policeCaracter);
            creator2.setFont(policeCaracter);

            panelCenter.setLayout(new GridLayout(6, 1));
            panelCenter.add(text);
            panelCenter.add(creator1);
            panelCenter.add(creator2);
            panelCenter.add(host);
            panelCenter.add(ip);

            backBtn.addActionListener(new btnBackListener());
            panelNorth.add(backBtn, BorderLayout.NORTH);
            title.setFont(titleFont);
            panelNorth.add(title, BorderLayout.WEST);

            System.out.println(System.getProperty("host.name"));

            add(panelNorth, BorderLayout.NORTH);
            add(panelCenter, BorderLayout.CENTER);
        }

        public String getHost(){
            String host = "";
            try {
                InetAddress inetadr = InetAddress.getLocalHost();
                //nom de l'host
                host = (String) inetadr.getHostName();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            return host;
        }

        public String getIP() {
            String adresseIPLocale = "";
            try {
                InetAddress inetadr = InetAddress.getLocalHost();
                //adresse ip de l'host
                adresseIPLocale = (String) inetadr.getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            return adresseIPLocale;
        }
    }

    //------------------------------------------------------------------------

    //WALLPAPER
    public class Wallpaper extends JPanel implements ActionListener {

        private int large = 55 ;
        private int length = 100 ;

        JLabel title = new JLabel("Wallpaper");

        IconButton wallpaper1 = new IconButton("Images\\Wallpapers\\wallpaper1.jpg", large, length);
        IconButton wallpaper2 = new IconButton("Images\\Wallpapers\\wallpaper2.jpg", large, length);
        IconButton wallpaper3 = new IconButton("Images\\Wallpapers\\wallpaper3.jpg", large, length);
        IconButton wallpaper4 = new IconButton("Images\\Wallpapers\\wallpaper4.jpg", large, length);

        JPanel panelCenter = new JPanel();
        JPanel panelNorth = new JPanel();

        IconButton backBtn = new IconButton("Images\\Icons\\backbtn.png", 20, 20);

        public Wallpaper(){
            setLayout(new BorderLayout());
            panelCenter.setLayout(new FlowLayout(15,15,15));

            wallpaper1.addActionListener(this);
            wallpaper2.addActionListener(this);
            wallpaper3.addActionListener(this);
            wallpaper4.addActionListener(this);

            panelCenter.add(wallpaper1);
            panelCenter.add(wallpaper2);
            panelCenter.add(wallpaper3);
            panelCenter.add(wallpaper4);

            title.setFont(titleFont);
            backBtn.addActionListener(new btnBackListener());

            panelNorth.add(backBtn, BorderLayout.WEST);
            panelNorth.add(title, BorderLayout.NORTH);

            add(panelNorth, BorderLayout.NORTH);
            add(panelCenter, BorderLayout.CENTER);
        }

        public void actionPerformed(ActionEvent e){
            Object o = e.getSource();

            if(o == wallpaper1){
                PanelEcranCenter.wallpaper.setIconPanel("Images\\Wallpapers\\wallpaper1.jpg", 298, 529);
            }
            if(o == wallpaper2){
                PanelEcranCenter.wallpaper.setIconPanel("Images\\Wallpapers\\wallpaper2.jpg", 1080, 2320);
            }
            if(o == wallpaper3){
                PanelEcranCenter.wallpaper.setIconPanel("Images\\Wallpapers\\wallpaper3.jpg", 1080, 1920);
            }
            if(o == wallpaper4){
                PanelEcranCenter.wallpaper.setIconPanel("Images\\Wallpapers\\wallpaper4.jpg", 1080, 1920);
            }
        }
    }

    public class SettingsGallery extends JPanel{

        Gallery gallery = new Gallery();

        JPanel panelCenter = new JPanel();
        JPanel panelNorth = new JPanel();

        IconButton backBtn = new IconButton("Images\\Icons\\backbtn.png", 20, 20);

        JLabel title = new JLabel("Gallery");

        public SettingsGallery() throws IOException {
            setLayout(new BorderLayout());

            title.setFont(titleFont);
            backBtn.addActionListener(new btnBackListener());

            panelNorth.add(backBtn, BorderLayout.WEST);
            panelNorth.add(title, BorderLayout.NORTH);
            panelCenter.add(gallery.scrollPane);

            add(panelNorth, BorderLayout.NORTH);
            add(panelCenter, BorderLayout.CENTER);
        }
    }

}
