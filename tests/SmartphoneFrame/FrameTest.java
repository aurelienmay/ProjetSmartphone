package SmartphoneFrame;

import ComponentIcon.IconPanel;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Classe FrameTest qui test une méthode de la classe Frame
 *
 * @author aurelienmay
 * @version 12.0
 */
public class FrameTest {

    Frame f = new Frame();
    IconPanel iconPanel = new IconPanel("Gallery\\i4.jpg");

    /**
     * Méthode de test qui test si l'iconPanel (le fichier lié à celui-ci)
     * est bien existant
     */
    @Test
    public void fileExistingControl(){
        assertEquals(true, f.fileExistingControl(iconPanel));
    }

}