package Main;

import SmartphoneFrame.Frame;
import java.io.IOException;

/**
 * Main du smartphone
 * @author aurelienmay
 * @version
 */
@SuppressWarnings("JavaDoc")
public class SmartphoneON {

    /**
     * @param args
     */
    public static void main(String []args){
        Frame frame = new Frame();
        frame.setSize(323,645);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
