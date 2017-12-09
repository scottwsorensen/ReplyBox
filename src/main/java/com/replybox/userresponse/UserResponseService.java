package com.replybox.userresponse;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.replybox.user.IUserService;
import com.replybox.user.User;
import com.replybox.userresponse.IUserResponseDAO;
import com.replybox.userresponse.UserResponse;
@Service
public class UserResponseService implements IUserResponseService {
	@Autowired
	private IUserResponseDAO userResponseDAO;
	@Autowired
	private IUserService userService;
	@Override
	public UserResponse getUserResponseById(Integer userResponseId) {
		UserResponse obj = userResponseDAO.getUserResponseById(userResponseId);
		return obj;
	}	
	@Override
	public List<UserResponse> getAllUserResponses(){
		return userResponseDAO.getAllUserResponses();
	}
	@Override
	public synchronized boolean addUserResponse(UserResponse userResponse){
                if (userResponseDAO.userResponseExists(userResponse.getUserId(), userResponse.getSurveyQuestionId())) {
    	            return false;
                } else {
    	            userResponseDAO.addUserResponse(userResponse);
    	            return true;
                }
	}
	@Override
	public void updateUserResponse(UserResponse userResponse) {
		userResponseDAO.updateUserResponse(userResponse);
	}
	@Override
	public void deleteUserResponse(Integer userResponseId) {
		userResponseDAO.deleteUserResponse(userResponseId);
	}
	@Override
	public UserResponse getUserResponseByPhone(String phoneNumber) {
		User user = userService.getUserByPhone(phoneNumber);
		List<UserResponse> userResponses = userResponseDAO.getUserResponsesByUserId(user.getUserId());
		//{SWS} should I get the last result that DOESN'T HAVE AN ANSWER ALREADY?
		return userResponses.get(userResponses.size()-1);

	}
} 