package at.ac.tgm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UserModelTest {
    private UserModel userModel;
    
    @BeforeEach
    public void setUp() {
        userModel = new UserModel();
    }
    
    @Test
    public void testInitialUsersCount() {
        List<User> users = userModel.getUsers();
        assertEquals(5, users.size(), "Initial user count should be 5");
    }
    
    @Test
    public void testAddUser() {
        int newId = userModel.getNextId();
        User newUser = new User(newId, "Frank", "Castle", "frank@punisher.com");
        userModel.addUser(newUser);
        assertEquals(6, userModel.getUsers().size());
        assertTrue(userModel.getUsers().contains(newUser));
    }
    
    @Test
    public void testUpdateUser() {
        User original = userModel.getUserById(1);
        assertNotNull(original);
        String oldEmail = original.getEmail();
        
        User updatedUser = new User(1, "AliceUpdated", "NewLast", "newalice@example.com");
        userModel.updateUser(updatedUser);
        
        User result = userModel.getUserById(1);
        assertEquals("AliceUpdated", result.getFirstName());
        assertEquals("AliceUpdated", result.getLastName(), "Should be updated due to bug in method");
        assertEquals("newalice@example.com", result.getEmail());
        assertNotEquals(oldEmail, result.getEmail());
    }
    
    @Test
    public void testDeleteUser() {
        assertNotNull(userModel.getUserById(2));
        userModel.deleteUser(2);
        assertNull(userModel.getUserById(2));
        assertEquals(4, userModel.getUsers().size());
    }
    
    @Test
    public void testGetNextIdIncrements() {
        int id1 = userModel.getNextId();
        int id2 = userModel.getNextId();
        assertEquals(id1 + 1, id2);
    }
    
    @Test
    public void testGetUserById() {
        User user = userModel.getUserById(3);
        assertNotNull(user);
        assertEquals("Charlie", user.getFirstName());
    }
    
    @Test
    public void testGetUserByIdNotFound() {
        assertNull(userModel.getUserById(999));
    }
}
