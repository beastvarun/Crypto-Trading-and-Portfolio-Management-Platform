package com.project1.service;

import com.project1.model.User;

public interface UserService {
	
	public User findUserProfileByJwt(String jwt) throws Exception;
	public User findByEmail(String email) throws Exception;
	public User findById(Long userId) throws Exception;
	
	

}
