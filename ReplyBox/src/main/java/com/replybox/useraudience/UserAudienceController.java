package com.replybox.useraudience;

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
import com.replybox.useraudience.UserAudience;


@Controller
@RequestMapping("replyboxuseraudience") // I am not sure why this is needed.
public class UserAudienceController {
	@Autowired
	private IUserAudienceService userAudienceService;
	@GetMapping("useraudience/{id}") 
	public ResponseEntity<UserAudience> getUseerById(@PathVariable("id") Integer id) {
		UserAudience userAudience = userAudienceService.getUserAudienceById(id);
		return new ResponseEntity<UserAudience>(userAudience, HttpStatus.OK);
	}
	@GetMapping("useraudiences")
	public ResponseEntity<List<UserAudience>> getAllUserAudiences() {
		List<UserAudience> list = userAudienceService.getAllUserAudiences();
		return new ResponseEntity<List<UserAudience>>(list, HttpStatus.OK);
	}
	@PostMapping("useraudience")
	public ResponseEntity<Void> addUserAudience(@RequestBody UserAudience userAudience, UriComponentsBuilder builder) {
                boolean flag = userAudienceService.addUserAudience(userAudience);
                if (flag == false) {
        	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/useraudience/{id}").buildAndExpand(userAudience.getUserAudienceId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("useraudience")
	public ResponseEntity<UserAudience> updateUserAudience(@RequestBody UserAudience userAudience) {
		userAudienceService.updateUserAudience(userAudience);
		return new ResponseEntity<UserAudience>(userAudience, HttpStatus.OK);
	}
	@DeleteMapping("useraudience/{id}")
	public ResponseEntity<Void> deleteUserAudience(@PathVariable("id") Integer id) {
		userAudienceService.deleteUserAudience(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}  