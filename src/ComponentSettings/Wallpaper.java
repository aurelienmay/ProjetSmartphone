package ComponentSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ComponentEcran.*;

import ComponentIcon.*;

public class Wallpaper extends JPanel implements ActionListener {

    private int large = 55 ;
    private int length = 100 ;
    IconButton wallpaper1 = new IconButton("Images\\Wallpapers\\wallpaper1.jpg", large, length);
    IconButton wallpaper2 = new IconButton("Images\\Wallpapers\\wallpaper2.jpg", large, length);
    IconButton wallpaper3 = new IconButton("Images\\Wallpapers\\wallpaper3.jpg", large, length);
    IconButton wallpaper4 = new IconButton("Images\\Wallpapers\\wallpaper4.jpg", large, length);

    public Wallpaper(){
        setLayout(new FlowLayout(15,15,15));

        wallpaper1.addActionListener(this);
        wallpaper2.addActionListener(this);
        wallpaper3.addActionListener(this);
        wallpaper4.addActionListener(this);

        add(wallpaper1);
        add(wallpaper2);
        add(wallpaper3);
        add(wallpaper4);
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
