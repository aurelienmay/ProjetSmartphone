package ComponentIcon;

import org.junit.Test;

import static org.junit.Assert.*;

public class IconButtonTest {

    IconButton ib = new IconButton("Images\\Icons\\calculator.png", 50, 100);

    @Test
    public void getFileLocation() {
        assertEquals("Images\\Icons\\calculator.png", ib.getFileLocation());

        ib.setNewLocation("Images\\Smartphone.png");
        assertEquals("Images\\Smartphone.png", ib.getFileLocation());
    }

    @Test
    public void getLarge() {
        assertEquals(50, ib.getLarge());

        ib.setIconButtonSize(100,80);
        assertEquals(100, ib.getLarge());
    }

    @Test
    public void getLength() {
        assertEquals(100, ib.getLength());

        ib.setIconButtonSize(80,100);
        assertEquals(100, ib.getLength());
    }
}