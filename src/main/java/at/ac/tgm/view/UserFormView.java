package at.ac.tgm.view;

import at.ac.tgm.controller.UserController;
import at.ac.tgm.model.User;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class UserFormView extends JDialog {
    private final JTextField firstNameField, lastNameField, emailField;
    
    public UserFormView(JFrame parent, User user, UserController controller) {
        super(parent, (user == null ? "Add" : "Edit") + " User", true);
        setSize(300, 200);
        setLayout(new GridLayout(4, 2, 10, 5));
        setLocationRelativeTo(parent);
        setIconImage(FontIcon.of(user == null ? FontAwesomeSolid.USER_PLUS : FontAwesomeSolid.USER_EDIT, 16).toImageIcon().getImage());
        
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailField = new JTextField();
        
        if (user != null) {
            firstNameField.setText(user.getFirstName());
            lastNameField.setText(user.getLastName());
            emailField.setText(user.getEmail());
        }
        
        add(new JLabel("First name:"));
        add(firstNameField);
        add(new JLabel("Last name:"));
        add(lastNameField);
        add(new JLabel("Email:"));
        add(emailField);
        
        JButton saveButton = new JButton("Save", FontIcon.of(FontAwesomeSolid.SAVE, 16));
        JButton cancelButton = new JButton("Cancel", FontIcon.of(FontAwesomeSolid.BAN, 16));
        
        add(saveButton);
        add(cancelButton);
        
        saveButton.setActionCommand("save");
        saveButton.addActionListener(controller);
        
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(e -> setVisible(false));
    }
    
    public String getFirstNameInput() {
        return firstNameField.getText().trim();
    }
    
    public String getLastNameInput() {
        return lastNameField.getText().trim();
    }
    
    public String getEmailInput() {
        return emailField.getText().trim();
    }
}

