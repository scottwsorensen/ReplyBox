package com.replybox.useraudience;

import java.util.List;

import com.replybox.useraudience.UserAudience;
public interface IUserAudienceDAO {
    List<UserAudience> getAllUserAudiences();
    UserAudience getUserAudienceById(Integer userAudienceId);
    void addUserAudience(UserAudience userAudience);
    void updateUserAudience(UserAudience userAudience);
    void deleteUserAudience(Integer userAudienceId);
    boolean userAudienceExists(Integer userId, Integer audienceId);
	List<UserAudience> getUsersForSurvey(Integer audienceId);
} 
