package ComponentEcran;

import ComponentIcon.IconButton;
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
import java.util.Objects;

/**
 * Class PanelEcranNorth (barre de "tâche" au sommet de l'écran
 *
 * @author aurelienmay
 * @version 12.0
 */
public class PanelEcranNorth extends JPanel{

    private final JLabel reseau = new JLabel("", JLabel.LEFT);
    private final JLabel heure = new JLabel("", JLabel.CENTER);

    private boolean clicHeure = true ;

    private String content = "" ;
    private String ssid = "" ;

    private final IconButton wifi0 = new IconButton("Images\\Icons\\wifi0.png", 18,18);

    /**
     * Constructeur
     */
    public PanelEcranNorth(){
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        setOpaque(true);

        Dimension dimReseau = new Dimension(123, 18);
        reseau.setPreferredSize(dimReseau);
        Dimension dimHeure = new Dimension(51, 18);
        heure.setPreferredSize(dimHeure);
        JPanel panelEast = new JPanel();
        Dimension dimBatterie = new Dimension(123, 18);
        panelEast.setPreferredSize(dimBatterie);

        Font police = new Font("Arial", Font.BOLD, 13);
        reseau.setFont(police);
        reseau.setForeground(Color.black);
        reseau.setBackground(Color.WHITE);
        reseau.setOpaque(true);

        heure.setFont(police);
        heure.setBackground(Color.white);
        heure.setOpaque(true);
        heure.addMouseListener(new mouseListenerHeure());

        //batterie.setFont(police);
        //batterie.setBackground(Color.WHITE);
        //batterie.setOpaque(true);
        panelEast.setBackground(Color.white);
        panelEast.setOpaque(true);

        //Mise à jour de l'heure
        Thread clock = new Thread(() -> {
            while (true) {
                Date dateHH_mm = new Date();
                SimpleDateFormat dateFormat;
                dateFormat = new SimpleDateFormat("HH:mm");
                String t = "" + dateFormat.format(dateHH_mm);
                if (clicHeure)
                    heure.setText(t);
                try {
                    //update chaque seconde
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        });
        clock.start();

        //Mise à jour de l'heure en seconde
        Thread clockSS = new Thread(() -> {
            while (true) {
                Date dateHH_mm_ss = new Date();
                SimpleDateFormat dateFormat;
                dateFormat = new SimpleDateFormat("HH:mm:ss");
                String t = "" + dateFormat.format(dateHH_mm_ss);
                if (!clicHeure)
                    heure.setText(t);
                try {
                    //update chaque seconde
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        });
        clockSS.start();

        //Met à jour le signal et le ssid
        Thread updateSignal = new Thread(() -> {
            Process p = null;
            boolean done = false;
            String signal;

            while (true) {
                try {
                    p = Runtime.getRuntime().exec("netsh wlan show interfaces");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(p).getInputStream()));
                while (true) {
                    try {
                        if ((content = reader.readLine()) == null) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(content.contains("d�connect�")){
                        ssid = "déconnecté";
                        signal = "0";
                        done = false;
                    }else{
                        //Test pour récupérer le signal
                        if (content.contains("Signal")) {
                            signal = content.substring(29, 31);
                        } else {
                            signal = "0";
                        }
                        //Test pour récupérer le ssid
                        if (done == false) {
                            if (content.contains("SSID")) {
                                ssid = content.substring(29);
                                done = true;
                            } else {
                                ssid = "déconnecté";
                            }
                        }
                    }
                    //méthode qui modifie le logo du wifi selon le pourcentage de signal reçu
                    System.out.println(ssid + " " + signal);
                    setSignalIcon(signal);
                    reseau.setText(ssid);
                    try {
                        //timing de la boucle (plus le nombre est élévé plus l'update du ssid et du signal est lente)
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        });
        updateSignal.start();

        FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 100, 0);
        panelEast.setLayout(fl);
        panelEast.add(wifi0);

        this.add(heure, BorderLayout.CENTER);
        this.add(reseau, BorderLayout.WEST);
        this.add(panelEast, BorderLayout.EAST);
    }

    /**
     * Class Listener en cas de clic sur l'heure
     */
    class mouseListenerHeure implements MouseListener {
        int cpt = 0 ;
        public void mouseClicked(MouseEvent e) {
            if(cpt==0) {
                clicHeure = false;
                cpt++;
            }else{
                clicHeure=true;
                cpt=0;
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    /**
     * Met à jour le logo du wi-fi (signal)
     *
     * @param signal signal récupéré
     */
    private void setSignalIcon(String signal){
        int i;

        if (!Objects.equals(signal, "")) {
            i = Integer.parseInt(signal);
            if (content.contains("d�connect�")) {
                wifi0.setNewLocation("Images\\Icons\\wifi0.png");
            }else{
                if (i > 0) {
                    wifi0.setNewLocation("Images\\Icons\\wifi1.png");
                    if (i >= 25) {
                        wifi0.setNewLocation("Images\\Icons\\wifi2.png");
                        if (i >= 50) {
                            wifi0.setNewLocation("Images\\Icons\\wifi3.png");
                            if (i >= 80) {
                                wifi0.setNewLocation("Images\\Icons\\wifi4.png");
                            }
                        }
                    }
                }
            }
        }
        wifi0.repaint();
    }

}