package com.replybox.userresponse;

import java.util.List;
import com.replybox.userresponse.UserResponse;
public interface IUserResponseService {
     List<UserResponse> getAllUserResponses();
     UserResponse getUserResponseById(Integer userResponseId);
     boolean addUserResponse(UserResponse userResponse);
     void updateUserResponse(UserResponse userResponse);
     void deleteUserResponse(Integer userResponseId);
     UserResponse getUserResponseByPhone(String phoneNumber);
} 