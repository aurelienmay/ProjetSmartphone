package ComponentSettings;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

import ComponentGallery.Gallery;
import ComponentIcon.IconButton;

/**
 * Class Settings, application Settings
 *
 * @author aurelienmay
 * @version 12.0
 */
public class Settings extends JPanel implements ListSelectionListener {

    private final Font titleFont = new Font("Arial", Font.BOLD, 20);

    private final JPanel panelCenter = new JPanel();

    private final String[] settingsListContent = {"Informations système", "Fond d'écran"};

    @SuppressWarnings("unchecked")
    private final JList settingsList = new JList(settingsListContent);

    private final CardLayout cardManager = new CardLayout();

    /**
     * Constructeur de l'application Settings
     */
    public Settings(){
        setLayout(new BorderLayout());
        panelCenter.setLayout(cardManager);

        //paramètre du panelNorth
        Font mainFontTitle = new Font("Arial", Font.BOLD, 50);
        JLabel settingsName = new JLabel("Réglages");
        settingsName.setFont(mainFontTitle);
        JPanel panelNorth = new JPanel();
        panelNorth.setBackground(Color.WHITE);
        panelNorth.add(settingsName, BorderLayout.NORTH);

        //paramètre settingsList
        settingsList.addListSelectionListener(this);
        Font fontCharacter = new Font("Arial", Font.BOLD, 18);
        settingsList.setFont(fontCharacter);
        //settingsList.

        //Ajout des panels à la "card" settings (CardLayout)
        JPanel cardSettings = new JPanel();
        cardSettings.setBackground(Color.WHITE);
        cardSettings.add(panelNorth, BorderLayout.NORTH);
        cardSettings.add(settingsList, BorderLayout.CENTER);

        //Ajout des cards au panelCenter
        panelCenter.add(cardSettings, "settings");
        Informations cardInformations = new Informations();
        panelCenter.add(cardInformations, "informations");

        add(panelCenter, BorderLayout.CENTER);
    }

    /**
     * Listener de la liste selon le choix de l'utilisateur
     *
     * @param e ListSelectionEvent
     */
    public void valueChanged(ListSelectionEvent e){
        String t = "";

        try{
            t = settingsList.getSelectedValue().toString();
        }catch (NullPointerException np){

            //np.printStackTrace();
        }

        if(Objects.equals(t, "Informations système")){
            cardManager.show(panelCenter, "informations");
        }
        if(Objects.equals(t, "Fond d'écran")){
            SettingsGallery cardGallery = new SettingsGallery();
            panelCenter.add(cardGallery, "gallery");
            cardManager.show(panelCenter, "gallery");
        }
    }

    /**
     * Listener du bouton back au sommet de la page lorsque l'utilisateur
     * est dans un onglet selectionné auparavant
     */
    class btnBackListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            settingsList.clearSelection();
            cardManager.show(panelCenter, "settings");
        }
    }

    //------------------------------------------------------------------------

    /**
     * Class Informations qui contient diverses informations (pour information)
     */
    class Informations extends JPanel {

        final JLabel text = new JLabel("Crée par :", JLabel.CENTER);
        final JLabel creator1 = new JLabel("Aurélien May", JLabel.CENTER);
        final JLabel creator2 = new JLabel("Léonard Favre", JLabel.CENTER);
        final JLabel host = new JLabel(getHost(), JLabel.CENTER);
        final JLabel ip = new JLabel(getIP(), JLabel.CENTER);

        final JLabel title = new JLabel("Informations système");

        final JPanel panelCenter = new JPanel();
        final JPanel panelNorth = new JPanel();

        final IconButton backBtn = new IconButton("Images\\Icons\\backbtn.png", 20, 20);

        /**
         * Constructeur du panel Informations
         */
        Informations(){
            setLayout(new BorderLayout());

            //Smartphone informations
            Font createdBy = new Font("Arial", Font.BOLD, 16);
            text.setFont(createdBy);
            Font policeCaracter = new Font("Arial", Font.BOLD, 14);
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

            add(panelNorth, BorderLayout.NORTH);
            add(panelCenter, BorderLayout.CENTER);
        }

        /**
         * Retourne le nom de l'host (de la machine)
         *
         * @return le nom de l'host
         */
        private String getHost(){
            String host = "";
            try {
                InetAddress inetadr = InetAddress.getLocalHost();
                //nom de l'host
                host = inetadr.getHostName();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            return host;
        }

        /**
         * Retourne l'adresse IP de la machine
         *
         * @return l'adresse IP de la machine
         */
        private String getIP() {
            String addressIPLocale = "";
            try {
                InetAddress inetadr = InetAddress.getLocalHost();
                //adresse ip de l'host
                addressIPLocale = inetadr.getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            return addressIPLocale;
        }
    }

    //--------------------------------------------------------------------------

    /**
     * Class qui fait appelle à la gallery
     */
    class SettingsGallery extends JPanel{

        final Gallery gallery = new Gallery();

        final JPanel panelNorth = new JPanel();

        final JScrollPane scrollPane = new JScrollPane(gallery.panelCenter);

        final IconButton backBtn = new IconButton("Images\\Icons\\backbtn.png", 20, 20);

        final JLabel title = new JLabel("Gallery");

        /**
         * Constructeur de la card SettingsGallery
         */
        SettingsGallery(){
            setLayout(new BorderLayout());
            gallery.setOpenInSettings(true);

            title.setFont(titleFont);
            backBtn.addActionListener(new btnBackListener());

            panelNorth.add(backBtn, BorderLayout.WEST);
            panelNorth.add(title, BorderLayout.NORTH);

            scrollPane.setBackground(Color.WHITE);
            scrollPane.setOpaque(true);

            add(panelNorth, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);
        }
    }

}
