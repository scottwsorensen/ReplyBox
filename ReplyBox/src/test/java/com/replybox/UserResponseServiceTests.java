package com.replybox;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.replybox.userresponse.IUserResponseService;
import com.replybox.userresponse.UserResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserResponseServiceTests {
	@Autowired
	private IUserResponseService userResponseService;    

	@Test
	public void contexLoads() throws Exception {
		assert(userResponseService != null);
	}
//	@Test
	public void getAllUserResponsesTest() {
		List<UserResponse> userResponses = userResponseService.getAllUserResponses();
		//{SWS} will this break if test data changes?
		assert(userResponses.get(0).getCustomAnswer().contains("A"));	
	}
	@Test
	public void getUserResponseByPhoneTest() {
		UserResponse userResponse = userResponseService.getUserResponseByPhone("+18013588575");
		//{SWS} will this always be my userId?
		assert(userResponse.getUserId() == 2);
	}
//	@Test
	public void userResponseServiceCRUDTest() {
		// Add userResponse
		UserResponse userResponse = new UserResponse();
		//{SWS} this will break if this UserResponse already exists.
		userResponse.setSurveyQuestionId(100);
		userResponse.setUserId(100);
		userResponse.setTestData(1);
		boolean userResponseAdded = userResponseService.addUserResponse(userResponse);
		assert(userResponseAdded == true);
		// Add it again to make sure it can't be added twice
		boolean userResponseAddedAgain = userResponseService.addUserResponse(userResponse);
		assert(userResponseAddedAgain == false);
		// Update the userResponse.
		userResponse.setSurveyQuestionId(2);
		userResponseService.updateUserResponse(userResponse);
		// Get userResponse by userResponseId and test for update.
		userResponse = userResponseService.getUserResponseById(userResponse.getUserResponseId());
		assert(userResponse.getSurveyQuestionId()==2);
		// Delete userResponse
		if((userResponseAdded == true) || (userResponseAddedAgain == true)){
			userResponseService.deleteUserResponse(userResponse.getUserResponseId());
			userResponse = userResponseService.getUserResponseById(userResponse.getUserResponseId());
			assert(userResponse == null);
		} 
	}
}
