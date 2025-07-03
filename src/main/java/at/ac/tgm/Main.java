package at.ac.tgm;

import at.ac.tgm.controller.UserController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        SwingUtilities.invokeLater(UserController::new);
    }
}