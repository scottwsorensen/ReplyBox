package com.replybox.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.replybox.user.IUserService;
import com.replybox.user.User;
import com.replybox.user.UserService;
import com.bandwidth.sdk.BandwidthClient;
import com.bandwidth.sdk.model.*;
import com.bandwidth.sdk.model.Message;

//{SWS} TODO this is how I build a callback to get messages from users so that I can write them to the db
@RestController
public class HelloController {
	@Autowired
	private IUserService userService;
	@RequestMapping("/hello")
	public String hello(@RequestParam String name) {
		try {
			//{SWS} TODO credentials should go somewhere else as a setting or something. #numbers and messages should be read from database
			BandwidthClient.getInstance().setCredentials("u-flugzrabh6dnvrwhv5xl3la", "t-viyjwby6m4vjs6uhqjv4cza", "uziser57ihcsx3ddgxxbuucjehp5uzozpsgtvdq");
			Message msg = Message.create("+18013588575", "+18014309264", "test message from bandwidth:"+name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		User user = userService.getUserById(1);
		
		return "Hello how are you "+user.getFirstName();
	}
}