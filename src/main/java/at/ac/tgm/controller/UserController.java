package at.ac.tgm.controller;

import at.ac.tgm.model.User;
import at.ac.tgm.model.UserModel;
import at.ac.tgm.view.UserFormView;
import at.ac.tgm.view.UserListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class UserController implements ActionListener {
    private final UserModel model;
    private final UserListView listView;
    private UserFormView formView;
    private User userToEdit;
    
    public UserController() {
        this.model = new UserModel();
        this.listView = new UserListView(this);
        
        refreshTable();
    }
    
    private void openUserForm() {
        formView = new UserFormView(listView, userToEdit, this);
        formView.setVisible(true);
    }
    
    private void refreshTable() {
        listView.tableModel.setRowCount(0);
        for (User u : model.getUsers()) {
            listView.tableModel.addRow(new Object[]{u.getId(), u.getFirstName(), u.getLastName(), u.getEmail()});
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add":
                addUser();
                break;
            case "edit":
                editUser();
                break;
            case "delete":
                deleteUser();
                break;
            case "save":
                saveUser();
                break;
        }
    }
    
    private void saveUser() {
        String firstName = formView.getFirstNameInput();
        String lastName = formView.getLastNameInput();
        String email = formView.getEmailInput();
        
        if (firstName.length() < 3) {
            JOptionPane.showMessageDialog(formView, "Firstname must be at least 3 characters.");
            return;
        }
        if (lastName.length() < 3) {
            JOptionPane.showMessageDialog(formView, "Lastname must be at least 3 characters.");
            return;
        }
        if (!Pattern.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$", email)) {
            JOptionPane.showMessageDialog(formView, "Invalid email address.");
            return;
        }
        
        if (userToEdit == null) {
            model.addUser(new User(model.getNextId(), firstName, lastName, email));
        } else {
            model.updateUser(new User(userToEdit.getId(), firstName, lastName, email));
        }
        
        formView.setVisible(false);
        
        refreshTable();
    }
    
    private void addUser() {
        userToEdit = null;
        openUserForm();
    }
    
    private void deleteUser() {
        int selectedRow = listView.userTable.getSelectedRow();
        if (selectedRow >= 0) {
            int modelRow = listView.userTable.convertRowIndexToModel(selectedRow);
            int userId = (int) listView.tableModel.getValueAt(modelRow, 0);
            int confirm = JOptionPane.showConfirmDialog(listView, "Delete this user?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.deleteUser(userId);
                refreshTable();
            }
        } else {
            JOptionPane.showMessageDialog(listView, "Select a user to delete.");
        }
    }
    
    private void editUser() {
        int selectedRow = listView.userTable.getSelectedRow();
        if (selectedRow >= 0) {
            int modelRow = listView.userTable.convertRowIndexToModel(selectedRow);
            int userId = (int) listView.tableModel.getValueAt(modelRow, 0);
            userToEdit = model.getUserById(userId);
            openUserForm();
        } else {
            JOptionPane.showMessageDialog(listView, "Select a user to edit.");
        }
    }
}

