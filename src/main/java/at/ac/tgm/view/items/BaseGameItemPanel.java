package at.ac.tgm.view.items;

import at.ac.tgm.controller.GameController;

import javax.swing.*;

import static at.ac.tgm.consts.Settings.iconSize;

public abstract class BaseGameItemPanel extends JPanel {
    protected BaseGameItemPanel(GameController controller) {
        addKeyListener(controller);
        setOpaque(false);
        setBounds(0, 0, iconSize, iconSize);
    }
}
