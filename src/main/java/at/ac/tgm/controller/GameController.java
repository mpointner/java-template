package at.ac.tgm.controller;

import at.ac.tgm.exceptions.InvalidMoveException;
import at.ac.tgm.model.GameModel;
import at.ac.tgm.model.items.BaseGameItemModel;
import at.ac.tgm.model.items.CornModel;
import at.ac.tgm.model.items.HamsterModel;
import at.ac.tgm.view.CellPane;
import at.ac.tgm.view.GameWindow;
import at.ac.tgm.view.items.BaseGameItemPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.NoSuchElementException;

import static at.ac.tgm.view.util.InputUtil.getIntInput;

public class GameController implements MouseListener, ActionListener, KeyListener {
    private final GameWindow view;
    private GameModel model;
    
    private String lastButtonClickedActionCommand = null;
    
    public GameController() {
        model = new GameModel(15, 10);
        view = new GameWindow(this);
    }
    
    public GameModel getModel() {
        return model;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("actionPerformed " + e.getActionCommand());
        lastButtonClickedActionCommand = e.getActionCommand();
        switch (e.getActionCommand()) {
            case "newGrid":
                try {
                    int width = getIntInput("Width:", 1, 40);
                    int height = getIntInput("Height:", 1, 40);
                    model = new GameModel(height, width);
                    view.drawGame(model);
                } catch (NoSuchElementException ex) {
                    view.setMessage(ex.getMessage());
                }
                break;
            case "positionHamster":
                view.setMessage("Click on a field on the playing field to position the hamster");
                break;
            case "addCorn":
                view.setMessage("Click on a field of the playing field to position the grains");
                break;
            case "deleteGameItem":
                view.setMessage("Click on a field of the playing field to delete a game item");
                break;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("keyTyped char: " + e.getKeyChar() + " code: " + e.getKeyCode());
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("keyPressed char: " + e.getKeyChar() + " code: " + e.getKeyCode());
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased char: " + e.getKeyChar() + " code: " + e.getKeyCode());
        HamsterModel hamster = model.getHamster();
        if (hamster == null) {
            view.setMessage("You have to place the hamster first");
            return;
        }
        try {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    hamster.moveForward();
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    hamster.rotateLeft();
                    break;
                default:
                    break;
            }
            view.drawGame(model);
        } catch (InvalidMoveException ex) {
            view.setMessage(ex.getMessage());
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof CellPane cellPanel) {
            int row = cellPanel.getRow();
            int col = cellPanel.getCol();
            System.out.println("mouseClicked Cell " + row + " " + col);
            if (lastButtonClickedActionCommand == null) {
                return;
            }
            switch (lastButtonClickedActionCommand) {
                case "positionHamster":
                    model.createHamster(row, col);
                    break;
                case "addCorn":
                    int cornAmount = getIntInput("How many Corn should be positioned?", 1);
                    model.createCorn(row, col, cornAmount);
                    break;
                case "deleteGameItem":
                    Component[] components = cellPanel.getComponents();
                    if (components.length > 0) {
                        BaseGameItemPanel component = (BaseGameItemPanel) components[0];
                        BaseGameItemModel componentModel = component.getModel();
                        if (componentModel instanceof HamsterModel) {
                            model.removeHamster();
                        } else if (componentModel instanceof CornModel) {
                            model.removeCorn(componentModel.getRow(), componentModel.getCol());
                        }
                    }
                    break;
            }
            view.setMessage("");
            view.drawGame(model);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("mousePressed " + e.getButton());
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("mouseReleased " + e.getButton());
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("mouseEntered " + e.getButton());
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("mouseExited " + e.getButton());
    }
    
    
}
