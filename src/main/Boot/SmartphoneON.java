package main.Boot;

import main.SmartphoneFrame.Frame;

/**
 * main.Boot du smartphone
 * @author aurelienmay
 * @version
 */
@SuppressWarnings("JavaDoc")
class SmartphoneON {

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
