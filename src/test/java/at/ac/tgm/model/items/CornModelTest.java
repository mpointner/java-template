package at.ac.tgm.model.items;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CornModelTest {
    
    @Test
    void testInitializationValid() {
        CornModel corn = new CornModel(3, 4, 5);
        assertEquals(3, corn.getRow());
        assertEquals(4, corn.getCol());
        assertEquals(5, corn.getAmount());
        assertEquals(5, corn.getLayer()); // Layer.CORN
    }
    
    @Test
    void testSetAmountValid() {
        CornModel corn = new CornModel(0, 0, 2);
        corn.setAmount(10);
        assertEquals(10, corn.getAmount());
    }
    
    @Test
    void testSetAmountInvalid() {
        CornModel corn = new CornModel(1, 1, 3);
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> corn.setAmount(0)
        );
        assertTrue(exception.getMessage().contains("Unexpected amount"));
    }
    
    @Test
    void testConstructorThrowsExceptionOnInvalidAmount() {
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> new CornModel(0, 0, -2)
        );
        assertTrue(exception.getMessage().contains("Unexpected amount"));
    }
}
