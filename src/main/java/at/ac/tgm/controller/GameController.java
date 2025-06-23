package at.ac.tgm.controller;

import at.ac.tgm.exceptions.InvalidMoveException;
import at.ac.tgm.model.GameModel;
import at.ac.tgm.model.items.HamsterModel;
import at.ac.tgm.view.CellPane;
import at.ac.tgm.view.GameWindow;

import javax.swing.*;
import java.awt.event.*;

public class GameController implements MouseListener, ActionListener, KeyListener {
    private GameWindow view;
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
            case "positionHamster":
                view.setMessage("Klicke auf ein Feld des Spielfelds um den Hamster zu positionieren");
                break;
            case "addCorn":
                view.setMessage("Klicke auf ein Feld des Spielfelds um die Körner zu positionieren");
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
            view.setMessage("Du musst zuerst den Hamster platzieren");
            return;
        }
        try {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    hamster.moveForward();
                    break;
                case KeyEvent.VK_LEFT:
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
        if (e.getSource() instanceof CellPane) {
            CellPane cellPanel = (CellPane) e.getSource();
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
                    model.createCorn(row, col, getCornAmount());
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
    
    private int getCornAmount() {
        boolean valid = false;
        int anzahl = 0;
        while (!valid) {
            String eingabe = JOptionPane.showInputDialog("Wie viele Körner sollen positioniert werden?");
            try {
                anzahl = Integer.parseInt(eingabe);
                if (anzahl < 1) {
                    JOptionPane.showMessageDialog(null, "Bitte eine Zahl >= 1 eingeben!", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                view.setMessage("Bitte eine Zahl eingaben");
                JOptionPane.showMessageDialog(null, "Bitte eine Zahl eingeben!", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
            }
            if (anzahl >= 1){
                valid = true;
            }
        }
        return anzahl;
    }
}
