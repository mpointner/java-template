package at.ac.tgm.view.util;

import javax.swing.*;
import java.util.NoSuchElementException;

public class InputUtil {
    public static int getIntInput(String message) {
        return getIntInput(message, null, null);
    }
    
    public static int getIntInput(String message, Integer min) {
        return getIntInput(message, min, null);
    }
    
    public static int getIntInput(String message, Integer min, Integer max) {
        if (min != null && max != null && max < min) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        boolean valid = false;
        int amount = 0;
        while (!valid) {
            String input = JOptionPane.showInputDialog(null, message, "Please enter an integer", JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                throw new NoSuchElementException("Input was cancelled by the user.");
            }
            try {
                amount = Integer.parseInt(input);
                if (min != null && max != null && (amount < min || amount > max)) {
                    JOptionPane.showMessageDialog(null, "Please enter a number between " + min + " and " + max + "!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } else if (min != null && max == null && amount < min) {
                    JOptionPane.showMessageDialog(null, "Please enter a number >= " + min + "!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } else if (max != null && min == null && amount > max) {
                    JOptionPane.showMessageDialog(null, "Please enter a number <= " + max + "!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a Integer!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
            if (amount >= 1) {
                valid = true;
            }
        }
        return amount;
    }
}
