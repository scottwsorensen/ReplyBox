package com.replybox.userresponse;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;
import com.replybox.userresponse.UserResponse;


@Controller
@RequestMapping("replyboxuserresponse") 
public class UserResponseController {
	@Autowired
	private IUserResponseService userResponseService;
	@GetMapping("userresponse/{id}") 
	public ResponseEntity<UserResponse> getUserResponseById(@PathVariable("id") Integer id) {
		UserResponse userResponse = userResponseService.getUserResponseById(id);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}
	@GetMapping("userresponses")
	public ResponseEntity<List<UserResponse>> getAllUserResponses() {
		List<UserResponse> list = userResponseService.getAllUserResponses();
		return new ResponseEntity<List<UserResponse>>(list, HttpStatus.OK);
	}
	@PostMapping("userresponse")
	public ResponseEntity<Void> addUserResponse(@RequestBody UserResponse userResponse, UriComponentsBuilder builder) {
                boolean flag = userResponseService.addUserResponse(userResponse);
                if (flag == false) {
        	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/userResponse/{id}").buildAndExpand(userResponse.getUserResponseId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("userresponse")
	public ResponseEntity<UserResponse> updateUserResponse(@RequestBody UserResponse userResponse) {
		userResponseService.updateUserResponse(userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}
	@DeleteMapping("userresponse/{id}")
	public ResponseEntity<Void> deleteUserResponse(@PathVariable("id") Integer id) {
		userResponseService.deleteUserResponse(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}  