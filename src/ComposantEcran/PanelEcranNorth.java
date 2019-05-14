package ComposantEcran;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PanelEcranNorth extends JPanel {

    private Font police = new Font("Arial", Font.BOLD, 10);

    private Dimension dimReseau = new Dimension(125, 15);
    private Dimension dimHeure = new Dimension(47, 15);
    private Dimension dimBatterie = new Dimension(125, 15);

    private JLabel reseau = new JLabel("Swisscom", JLabel.LEFT);
    private JLabel heure = new JLabel("14:00", JLabel.CENTER);
    private JLabel batterie = new JLabel("batterie", JLabel.RIGHT);

    boolean clicHeure = true ;

    public static String content = "";
    public static String t = "";

    public PanelEcranNorth(){
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        setOpaque(true);

        reseau.setPreferredSize(dimReseau);
        heure.setPreferredSize(dimHeure);
        batterie.setPreferredSize(dimBatterie);

        reseau.setFont(police);
        //reseau.setBackground(Color.green);
        reseau.setOpaque(true);
        reseau.setText(getWLAN());

        heure.setFont(police);
        //heure.setPreferredSize(dimHeure);
        heure.setOpaque(true);

        batterie.setFont(police);
        //batterie.setBackground(Color.red);
        batterie.setOpaque(true);

        heure.addMouseListener(new SourisListenerHeure());
        clock.start();
        clockSS.start();

        this.add(heure, BorderLayout.CENTER);
        this.add(reseau, BorderLayout.WEST);
        this.add(batterie, BorderLayout.EAST);
    }

    public static String getWLAN(){
        Process p = null;
        String ssid =null;

        try {
            p = Runtime.getRuntime().exec("netsh wlan show interfaces" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        boolean done =false;
        while (true) {
            try {
                if (!((content = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            //content = content.substring(20, 30);
            if(done ==false) {
                if (content.contains("SSID")) {
                    ssid = content.substring(29);
                    done =true;
                }
            }
        }
        return ssid ;
    }

    class SourisListenerHeure implements MouseListener {
        int cpt = 0 ;
        public void mouseClicked(MouseEvent e) {
            //System.out.println(e.getX() + " " + e.getY());
            //if(e.getX()>136 && e.getX()<162 && e.getY()>3 && e.getY()<9) {
                if(cpt==0) {
                    clicHeure = false;
                    cpt++;
                }else{
                    clicHeure=true;
                    cpt=0;
                }
            //}
        }

        public void mousePressed(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {}

        public void mouseEntered(MouseEvent e) {}

        public void mouseExited(MouseEvent e) {}

    }

    Thread clock = new Thread() {
        @Override
        public void run() {
            while (true) {
                Date dateHH_mm = new Date();
                SimpleDateFormat dateFormat ;
                dateFormat = new SimpleDateFormat("HH:mm");
                String t = "" + dateFormat.format(dateHH_mm) ;
                if(clicHeure)
                    heure.setText(t);
                try {
                    sleep(10);
                } catch (InterruptedException ie) {
                }
            }
        }
    };

    Thread clockSS = new Thread() {
        @Override
        public void run() {
            while (true) {
                Date dateHH_mm_ss = new Date();
                SimpleDateFormat dateFormat ;
                dateFormat = new SimpleDateFormat("HH:mm:ss");
                String t = "" + dateFormat.format(dateHH_mm_ss) ;
                if(!clicHeure)
                    heure.setText(t);
                try {
                    sleep(10);
                } catch (InterruptedException ie) {
                }
            }
        }
    };
}
