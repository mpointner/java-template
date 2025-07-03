package at.ac.tgm.view;

import at.ac.tgm.controller.UserController;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Objects;

public class UserListView extends JFrame {
    public JTable userTable;
    public DefaultTableModel tableModel;
    public JButton addButton, editButton, deleteButton;
    
    public UserListView(UserController controller) {
        setTitle("User Administration");
        setSize(650, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(FontIcon.of(FontAwesomeSolid.USER, 16).toImageIcon().getImage());
        
        tableModel = new DefaultTableModel(new Object[]{"ID", "Firstname", "Lastname", "Email"}, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        userTable = new JTable(tableModel);
        userTable.setRowSorter(new TableRowSorter<>(tableModel));
        userTable.setRowHeight(24);
        
        JScrollPane scrollPane = new JScrollPane(userTable);
        
        addButton = new JButton("Add", FontIcon.of(FontAwesomeSolid.PLUS, 16));
        addButton.setActionCommand("add");
        addButton.addActionListener(controller);
        
        editButton = new JButton("Edit", FontIcon.of(FontAwesomeSolid.PEN, 16));
        editButton.setActionCommand("edit");
        editButton.addActionListener(controller);
        
        deleteButton = new JButton("Delete", FontIcon.of(FontAwesomeSolid.TRASH, 16));
        deleteButton.setActionCommand("delete");
        deleteButton.addActionListener(controller);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}

