package com.replybox.replymessage;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.replybox.userresponse.IUserResponseService;
import com.replybox.userresponse.UserResponse;

@Controller
@RequestMapping("message")
public class MessageController {
	@Autowired
	private IUserResponseService userResponseService;

	@PostMapping("process")
	public void processMessage(@RequestBody Map<String, Object> payload) {
		try {
			UserResponse userResponse = userResponseService.getUserResponseByPhone((String)payload.get("from"));
			userResponse.setCustomAnswer((String)payload.get("text"));
			userResponse.setAnswerTime(new Date()); 
			userResponseService.updateUserResponse(userResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	
}