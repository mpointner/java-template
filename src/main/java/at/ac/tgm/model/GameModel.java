package at.ac.tgm.model;

import at.ac.tgm.model.items.CornModel;
import at.ac.tgm.model.items.HamsterModel;

public class GameModel {
    private final int rowsGrid;
    private final int colsGrid;
    private HamsterModel hamster;
    private CornModel[][] corns;
    
    public GameModel(int rowsGrid, int colsGrid) {
        if (rowsGrid < 1 || colsGrid < 1) {
            throw new IllegalArgumentException("rowsGrid and colsGrid must be greater than 0");
        }
        this.rowsGrid = rowsGrid;
        this.colsGrid = colsGrid;
        this.hamster = new HamsterModel(Math.min(1, rowsGrid-1), Math.min(1, colsGrid-1), rowsGrid, colsGrid);
        this.corns = new CornModel[rowsGrid][colsGrid];
    }
    
    public void createHamster(int row, int col) {
        if (this.hamster == null) {
            this.hamster = new HamsterModel(row, col, rowsGrid, colsGrid);
        } else {
            this.hamster.setPosition(row, col);
        }
    }
    
    public void createCorn(int row, int col, int amount) {
        if (corns[row][col] == null) {
            corns[row][col] = new CornModel(row, col, amount);
        } else {
            corns[row][col].setAmount(amount);
        }
    }
    
    public HamsterModel getHamster() {
        return hamster;
    }
    
    public CornModel[][] getCorns() {
        return corns;
    }
    
    public int getRowsGrid() {
        return rowsGrid;
    }
    
    public int getColsGrid() {
        return colsGrid;
    }
}
