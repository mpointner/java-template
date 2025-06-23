package at.ac.tgm.view;

import at.ac.tgm.controller.GameController;
import at.ac.tgm.model.items.BaseGameItemModel;
import at.ac.tgm.model.items.CornModel;
import at.ac.tgm.model.items.HamsterModel;
import at.ac.tgm.view.items.BaseGameItemPanel;
import at.ac.tgm.view.items.CornPanel;
import at.ac.tgm.view.items.HamsterPanel;

import javax.swing.*;
import java.awt.*;

import static at.ac.tgm.consts.Settings.iconSize;

public class CellPane extends JLayeredPane {
    private final GameController controller;
    private final int row;
    private final int col;
    
    protected CellPane(GameController controller, int row, int col) {
        super();
        this.controller = controller;
        this.row = row;
        this.col = col;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(controller);
        addKeyListener(controller);
        JButton button = new JButton("T");
        add(button, JLayeredPane.PALETTE_LAYER);
        setPreferredSize(new Dimension(iconSize, iconSize));
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public void drawGameItem(BaseGameItemModel model) {
        BaseGameItemPanel gameItemView = null;
        int layer = model.getLayer();
        if (model instanceof HamsterModel) {
            gameItemView = new HamsterPanel((HamsterModel) model, controller);
        }
        if (model instanceof CornModel) {
            gameItemView = new CornPanel((CornModel) model, controller);
        }
        if (gameItemView != null) {
            add(gameItemView, Integer.valueOf(layer));
        }
    }
}
