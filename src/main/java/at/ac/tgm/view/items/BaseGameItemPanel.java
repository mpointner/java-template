package at.ac.tgm.view.items;

import at.ac.tgm.controller.GameController;
import at.ac.tgm.model.items.BaseGameItemModel;

import javax.swing.*;

import static at.ac.tgm.consts.Settings.iconSize;

public abstract class BaseGameItemPanel extends JPanel {
    protected BaseGameItemPanel(GameController controller) {
        setFocusable(false);
        setOpaque(false);
        setBounds(0, 0, iconSize, iconSize);
    }
    
    public abstract BaseGameItemModel getModel();
}
