package ComponentSettings;

import javax.swing.*;
import java.awt.*;
import ComponentSettings.*;
import ComposantIcon.IconButton;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Informations extends JPanel {

    Font policeCaracter = new Font("Arial", Font.BOLD, 18);
    Font fontTitle = new Font("Arial", Font.BOLD, 20);

    JLabel text = new JLabel("Crée par :", JLabel.CENTER);
    JLabel creator1 = new JLabel("Aurélien May", JLabel.CENTER);
    JLabel creator2 = new JLabel("Léonard Favre", JLabel.CENTER);
    JLabel host = new JLabel(getHost(), JLabel.CENTER);
    JLabel ip = new JLabel(getIP(), JLabel.CENTER);

    JPanel panelCenter = new JPanel();

    IconButton backBtn = new IconButton("Images\\Icons\\backbtn.png", 20, 20);

    public Informations(){
        setLayout(new BorderLayout());

        //Smartphone informations
        text.setFont(fontTitle);
        creator1.setFont(policeCaracter);
        creator2.setFont(policeCaracter);

        panelCenter.setLayout(new GridLayout(6, 1));
        panelCenter.add(text);
        panelCenter.add(creator1);
        panelCenter.add(creator2);
        panelCenter.add(host);
        panelCenter.add(ip);

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
