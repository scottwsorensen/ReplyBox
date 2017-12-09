package com.replybox.user;
import java.util.List;
import com.replybox.user.User;
public interface IUserDAO {
    List<User> getAllUsers();
    User getUserById(Integer userId);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Integer userId);
    boolean userExists(String firstName, String lastName);
    User getUserByPhone(String phoneNumber);
} 