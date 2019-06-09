package Boot;

import SmartphoneFrame.Frame;

/**
 * Boot du smartphone
 * @author aurelienmay
 * @version
 */
@SuppressWarnings("JavaDoc")
class SmartphoneON {

    /**
     * @param args
     */
    public static void main(String []args){
        System.out.println("Démarrage du SmartPhone en cours...");
        Frame frame = new Frame();
        frame.setSize(323,645);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
