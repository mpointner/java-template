package at.ac.tgm.model.items;

import at.ac.tgm.consts.Direction;
import at.ac.tgm.exceptions.InvalidMoveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HamsterModelTest {
    
    private HamsterModel hamster;
    
    @BeforeEach
    void setUp() {
        hamster = new HamsterModel(2, 2, 5, 5); // 5x5 grid, starting at (2,2)
    }
    
    @Test
    void testInitialValues() {
        assertEquals(2, hamster.getRow());
        assertEquals(2, hamster.getCol());
        assertEquals(Direction.EAST, hamster.getDirection());
        assertEquals(10, hamster.getLayer()); // Layer.HAMSTER
    }
    
    @Test
    void testSetPosition() {
        hamster.setPosition(1, 3);
        assertEquals(1, hamster.getRow());
        assertEquals(3, hamster.getCol());
    }
    
    @Test
    void testRotateLeft() {
        hamster.rotateLeft(); // EAST -> NORTH
        assertEquals(Direction.NORTH, hamster.getDirection());
        hamster.rotateLeft(); // NORTH -> WEST
        assertEquals(Direction.WEST, hamster.getDirection());
        hamster.rotateLeft(); // WEST -> SOUTH
        assertEquals(Direction.SOUTH, hamster.getDirection());
        hamster.rotateLeft(); // SOUTH -> EAST
        assertEquals(Direction.EAST, hamster.getDirection());
    }
    
    @Test
    void testMoveForwardEast() throws InvalidMoveException {
        hamster.moveForward();
        assertEquals(2, hamster.getRow());
        assertEquals(3, hamster.getCol());
    }
    
    @Test
    void testMoveForwardNorth() throws InvalidMoveException {
        hamster.rotateLeft(); // EAST -> NORTH
        hamster.moveForward();
        assertEquals(1, hamster.getRow());
        assertEquals(2, hamster.getCol());
    }
    
    @Test
    void testMoveForwardWest() throws InvalidMoveException {
        hamster.rotateLeft(); // EAST -> NORTH
        hamster.rotateLeft(); // NORTH -> WEST
        hamster.moveForward();
        assertEquals(2, hamster.getRow());
        assertEquals(1, hamster.getCol());
    }
    
    @Test
    void testMoveForwardSouth() throws InvalidMoveException {
        hamster.rotateLeft(); // EAST -> NORTH
        hamster.rotateLeft(); // NORTH -> WEST
        hamster.rotateLeft(); // WEST -> SOUTH
        hamster.moveForward();
        assertEquals(3, hamster.getRow());
        assertEquals(2, hamster.getCol());
    }
    
    @Test
    void testInvalidMoveThrowsException() {
        HamsterModel edgeHamster = new HamsterModel(0, 0, 5, 5);
        edgeHamster.rotateLeft(); // EAST -> NORTH
        assertThrows(InvalidMoveException.class, edgeHamster::moveForward);
    }
    
    @Test
    void testUpdatePositionValid() throws InvalidMoveException {
        hamster.updatePosition(-1, 1); // Move to (1,3)
        assertEquals(1, hamster.getRow());
        assertEquals(3, hamster.getCol());
    }
    
    @Test
    void testUpdatePositionInvalidRow() {
        assertThrows(InvalidMoveException.class, () -> hamster.updatePosition(-3, 0));
    }
    
    @Test
    void testUpdatePositionInvalidCol() {
        assertThrows(InvalidMoveException.class, () -> hamster.updatePosition(0, 5));
    }
}

