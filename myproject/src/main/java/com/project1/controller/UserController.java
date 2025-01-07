package com.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project1.model.User;
import com.project1.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService ;
	
	@GetMapping("/api/users/profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader ("Authorization") String jwt) throws Exception{
		User user =userService.findUserProfileByJwt(jwt);
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}

}
