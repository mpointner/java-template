package at.ac.tgm.model.items;

public abstract class BaseGameItemModel {
    protected final int layer;
    protected int row;
    protected int col;
    
    protected BaseGameItemModel(int layer, int row, int col) {
        this.layer = layer;
        this.row = row;
        this.col = col;
    }
    
    public int getLayer() {
        return layer;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
}
