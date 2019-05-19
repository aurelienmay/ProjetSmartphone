package ComponentEcran;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PanelEcranNorth extends JPanel{

    private Font police = new Font("Arial", Font.BOLD, 12);

    private Dimension dimReseau = new Dimension(123, 14);
    private Dimension dimHeure = new Dimension(51, 14);
    private Dimension dimBatterie = new Dimension(123, 14);

    private JLabel reseau = new JLabel("", JLabel.LEFT);
    private JLabel heure = new JLabel("", JLabel.CENTER);
    private JLabel batterie = new JLabel("batterie", JLabel.RIGHT);

    boolean clicHeure = true ;

    private String content = "" ;
    private String ssid = "" ;
    private String signal = "" ;

    public PanelEcranNorth(){
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        setOpaque(true);

        reseau.setPreferredSize(dimReseau);
        heure.setPreferredSize(dimHeure);
        batterie.setPreferredSize(dimBatterie);

        reseau.setFont(police);
        getSSID();
        reseau.setBackground(Color.WHITE);
        reseau.setOpaque(true);

        heure.setFont(police);
        heure.setBackground(Color.WHITE);
        heure.setOpaque(true);

        batterie.setFont(police);
        batterie.setBackground(Color.WHITE);
        batterie.setOpaque(true);

        heure.addMouseListener(new sourisListenerHeure());
        clock.start();
        clockSS.start();

        this.add(heure, BorderLayout.CENTER);
        this.add(reseau, BorderLayout.WEST);
        this.add(batterie, BorderLayout.EAST);
    }

    class reseauListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            getSSID();
        }
    }

    public void getSSID(){
        Process p = null;
        boolean done = false;

            try {
                p = Runtime.getRuntime().exec("netsh wlan show interfaces");
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while (true) {
                try {
                    if (!((content = reader.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //System.out.println(content);
                if (content.contains("Signal")) {
                    signal = content.substring(29);
                }
                if (done == false) {
                    if (content.contains("SSID")) {
                        ssid = content.substring(29);
                        done = true;
                    }
                }
                reseau.setText(ssid + " " + signal);
            }
    }



    class sourisListenerHeure implements MouseListener {
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
