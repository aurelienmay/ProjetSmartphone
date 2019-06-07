package ComponentExceptions;

import ComponentEcran.PanelEcranCenter;

import javax.swing.*;
import java.awt.*;

public class wallpaperSuppressionException extends Throwable {

    public wallpaperSuppressionException(){

        JLabel jl = new JLabel("<html><center>Impossible de supprimer" + "</br>" + "le fond d'écran !</center></html>", JLabel.CENTER);
        jl.setHorizontalAlignment(JLabel.CENTER);
        jl.setVerticalAlignment(JLabel.CENTER);

        JDialog jd = new JDialog();

        jl.setPreferredSize(new Dimension(153,50));
        jl.setForeground(Color.WHITE);
        jl.setBackground(Color.BLACK);
        jl.setOpaque(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1500);
                    jd.dispose();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
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
