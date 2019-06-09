package ComponentExceptions;

import ComponentEcran.PanelEcranCenter;

import javax.swing.*;
import java.awt.*;

/**
 * Class wallpaperSuppressionException
 * Cette exception ne laisse pas le droit à l'utlisateur de supprimer l'image dans gallery
 * si elle est utilisé pour le fond d'écran (un message apparait)
 *
 * @author aurelienmay
 * @version 12.0
 */
public class wallpaperSuppressionException extends Throwable {

    /**
     * Constructeur wallpaperSuppressionException
     *
     * Affiche un message en cas de suppression du fond d'écran actuel
     */
    public wallpaperSuppressionException(){

        JLabel jl = new JLabel("<html><center>Impossible de supprimer" + "</br>" + "le fond d'écran !</center></html>", JLabel.CENTER);
        jl.setHorizontalAlignment(JLabel.CENTER);
        jl.setVerticalAlignment(JLabel.CENTER);

        JDialog jd = new JDialog();

        jl.setPreferredSize(new Dimension(153,50));
        jl.setForeground(Color.WHITE);
        jl.setBackground(Color.BLACK);
        jl.setOpaque(true);

        new Thread(() -> {
            try{
                Thread.sleep(1500);
                jd.dispose();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }).start();
        jd.add(jl);

        try {
            jd.setUndecorated(true);
        } catch (IllegalComponentStateException icse){
            icse.printStackTrace();
        }

        jd.setLocation(695,180); // 890, 283
        jd.setVisible(true);
        jd.pack();
    }
}
