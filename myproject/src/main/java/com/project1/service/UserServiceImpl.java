package com.project1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.project1.config.JwtProvider;
import com.project1.model.User;
import com.project1.repository.UserRepository;

import jakarta.websocket.server.ServerEndpoint;
@RestController
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserProfileByJwt(String jwt) throws Exception{
		
		
		// TODO Auto-generated method stub
		String email=JwtProvider.getEmailsFromToken(jwt);
		User user=userRepository.findByEmail(email);
		if(user==null)
		{
			throw new Exception("User not found");
		}
		return user;
	}

	@Override
	public User findByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email);
		if(user==null)
		{
			throw new Exception("User not found");
		}
		return user;
	}

	@Override
	public User findById(Long userId) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> user=userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new Exception("user not found");
		}
		return user.get();
	}
	

}
