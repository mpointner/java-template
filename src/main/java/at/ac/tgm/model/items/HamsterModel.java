package at.ac.tgm.model.items;

import at.ac.tgm.consts.Direction;
import at.ac.tgm.exceptions.InvalidMoveException;
import at.ac.tgm.consts.Layer;

public class HamsterModel extends BaseGameItemModel {
    private final int rowsGrid;
    private final int colsGrid;
    private int direction = Direction.EAST;
    
    public HamsterModel(int row, int col, int rowsGrid, int colsGrid) {
        super(Layer.HAMSTER, row, col);
        this.rowsGrid = rowsGrid;
        this.colsGrid = colsGrid;
    }
    
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public int getDirection() {
        return direction;
    }
    
    public void rotateLeft() {
        direction = switch (direction) {
            case Direction.EAST -> Direction.NORTH;
            case Direction.SOUTH -> Direction.EAST;
            case Direction.WEST -> Direction.SOUTH;
            case Direction.NORTH -> Direction.WEST;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }
    
    public void updatePosition(int diff_row, int diff_col) throws InvalidMoveException {
        int newRow = row + diff_row;
        int newCol = col + diff_col;
        if (newRow < 0 || newRow >= rowsGrid || newCol < 0 || newCol >= colsGrid) {
            throw new InvalidMoveException("Hamster cannot leave the playing field");
        }
        row = newRow;
        col = newCol;
    }
    
    public void moveForward() throws InvalidMoveException {
        switch (direction) {
            case Direction.EAST:
                updatePosition(0, 1);
                break;
            case Direction.WEST:
                updatePosition(0, -1);
                break;
            case Direction.NORTH:
                updatePosition(-1, 0);
                break;
            case Direction.SOUTH:
                updatePosition(1, 0);
                break;
        }
    }
}
