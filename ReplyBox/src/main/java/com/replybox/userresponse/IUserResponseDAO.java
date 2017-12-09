package com.replybox.userresponse;

import java.util.List;
import com.replybox.userresponse.UserResponse;
public interface IUserResponseDAO {
    List<UserResponse> getAllUserResponses();
    UserResponse getUserResponseById(Integer userResponseId);
    void addUserResponse(UserResponse userResponse);
    void updateUserResponse(UserResponse userResponse);
    void deleteUserResponse(Integer userResponseId);
    boolean userResponseExists(Integer userId, Integer surveyQuestionId);
    List<UserResponse> getUserResponsesByUserId(Integer userId);
} 