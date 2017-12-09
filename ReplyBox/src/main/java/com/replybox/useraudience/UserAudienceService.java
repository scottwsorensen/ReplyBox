package com.replybox.useraudience;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.replybox.useraudience.IUserAudienceDAO;
import com.replybox.useraudience.UserAudience;
@Service
public class UserAudienceService implements IUserAudienceService {
	@Autowired
	private IUserAudienceDAO userAudienceDAO;
	@Override
	public UserAudience getUserAudienceById(Integer userAudienceId) {
		UserAudience obj = userAudienceDAO.getUserAudienceById(userAudienceId);
		return obj;
	}	
	@Override
	public List<UserAudience> getAllUserAudiences(){
		return userAudienceDAO.getAllUserAudiences();
	}
	@Override
	public synchronized boolean addUserAudience(UserAudience userAudience){
        if (userAudienceDAO.userAudienceExists(userAudience.getUserId(), userAudience.getAudienceId())) {
            return false;
        } else {
            userAudienceDAO.addUserAudience(userAudience);
            return true;
        }
	}
	@Override
	public void updateUserAudience(UserAudience userAudience) {
		userAudienceDAO.updateUserAudience(userAudience);
	}
	@Override
	public void deleteUserAudience(Integer userAudienceId) {
		userAudienceDAO.deleteUserAudience(userAudienceId);
	}
	@Override
    public List<UserAudience> getUsersForSurvey(Integer audienceId) {
		List<UserAudience> usersForSurvey = userAudienceDAO.getUsersForSurvey(audienceId);
		return usersForSurvey;
	}
} 