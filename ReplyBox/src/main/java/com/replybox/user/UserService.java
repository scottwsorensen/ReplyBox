package com.replybox.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.replybox.user.IUserDAO;
import com.replybox.user.User;
@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDAO userDAO;
	@Override
	public User getUserById(Integer userId) {
		User obj = userDAO.getUserById(userId);
		return obj;
	}	
	@Override
	public List<User> getAllUsers(){
		return userDAO.getAllUsers();
	}
	@Override
	public synchronized boolean addUser(User user){
                if (userDAO.userExists(user.getFirstName(), user.getLastName())) {
    	            return false;
                } else {
    	            userDAO.addUser(user);
    	            return true;
                }
	}
	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}
	@Override
	public void deleteUser(Integer userId) {
		userDAO.deleteUser(userId);
	}
	@Override
	public User getUserByPhone(String phoneNumber) {
		return userDAO.getUserByPhone(phoneNumber);
	}
	
} 