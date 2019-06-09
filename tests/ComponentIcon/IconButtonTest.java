package ComponentIcon;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe IconButtonTest qui test la classe IconButton
 *
 * @author aurelienmay
 * @version 12.0
 */
public class IconButtonTest {

    private final IconButton ib = new IconButton("Images\\Icons\\calculator.png", 50, 100);

    /**
     * Test si la méthode retourne bien la location du fichier
     */
    @Test
    public void getFileLocation() {
        assertEquals("Images\\Icons\\calculator.png", ib.getFileLocation());

        ib.setNewLocation("Images\\Smartphone.png");
        assertEquals("Images\\Smartphone.png", ib.getFileLocation());
    }

    /**
     * Test si la méthode retourne bien la largeur de l'IconButton
     */
    @Test
    public void getLarge() {
        assertEquals(50, ib.getLarge());

        ib.setIconButtonSize(100,80);
        assertEquals(100, ib.getLarge());
    }

    /**
     * Test si la méthode retourne bien la longueur de l'IconButton
     */
    @Test
    public void getLength() {
        assertEquals(100, ib.getLength());

        ib.setIconButtonSize(80,100);
        assertEquals(100, ib.getLength());
    }
}