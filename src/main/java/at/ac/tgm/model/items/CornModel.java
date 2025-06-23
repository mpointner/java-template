package at.ac.tgm.model.items;

import at.ac.tgm.consts.Layer;

public class CornModel extends BaseGameItemModel {
    private int amount;
    
    public CornModel(int row, int col, int anzahl) {
        super(Layer.CORN, row, col);
        setAmount(anzahl);
    }
    
    public void setAmount(int amount) {
        if (amount < 1) {
            throw new IllegalStateException("Unexpected amount: " + amount);
        }
        this.amount = amount;
    }
    
    public int getAmount() {
        return amount;
    }
}
