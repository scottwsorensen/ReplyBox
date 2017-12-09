package com.replybox.user;

import java.util.List;
import com.replybox.user.User;
public interface IUserService {
     List<User> getAllUsers();
     User getUserById(Integer userId);
     boolean addUser(User user);
     void updateUser(User user);
     void deleteUser(Integer userId);
 	User getUserByPhone(String phoneNumber);
} 