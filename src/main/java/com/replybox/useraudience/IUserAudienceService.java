package com.replybox.useraudience;

import java.util.List;

import com.replybox.useraudience.UserAudience;
public interface IUserAudienceService {
     List<UserAudience> getAllUserAudiences();
     UserAudience getUserAudienceById(Integer userAudienceId);
     boolean addUserAudience(UserAudience userAudience);
     void updateUserAudience(UserAudience userAudience);
     void deleteUserAudience(Integer userAudienceId);
     List<UserAudience> getUsersForSurvey(Integer audienceId);
} 