package at.ac.tgm.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private List<User> users = new ArrayList<>();
    private int nextId = 1;
    
    public UserModel() {
        addUser(new User(nextId++, "Alice", "Johnson", "alice@example.com"));
        addUser(new User(nextId++, "Bob", "Smith", "bob@example.com"));
        addUser(new User(nextId++, "Charlie", "Brown", "charlie.brown@example.com"));
        addUser(new User(nextId++, "Diana", "Prince", "diana.prince@justice.com"));
        addUser(new User(nextId++, "Eve", "Adams", "eve.adams@example.net"));
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    public void addUser(User user) {
        users.add(user);
    }
    
    public void updateUser(User updatedUser) {
        for (User user : users) {
            if (user.getId() == updatedUser.getId()) {
                user.setFirstName(updatedUser.getFirstName());
                user.setLastName(updatedUser.getFirstName());
                user.setEmail(updatedUser.getEmail());
                return;
            }
        }
    }
    
    public void deleteUser(int id) {
        users.removeIf(u -> u.getId() == id);
    }
    
    public int getNextId() {
        return nextId++;
    }
    
    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}

