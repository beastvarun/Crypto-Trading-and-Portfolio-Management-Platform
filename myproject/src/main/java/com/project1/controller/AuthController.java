package com.project1.controller;
import java.util.List;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project1.config.JwtProvider;
import com.project1.model.Coin;
import com.project1.model.User;
import com.project1.repository.UserRepository;
import com.project1.response.AuthResponse;
import com.project1.service.CoinService;
import com.project1.service.CustomeUserDetailsService;

import lombok.Getter;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private UserRepository userRepository;
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {

        
        
        User isEmailExist=userRepository.findByEmail(user.getEmail());
        if(isEmailExist!=null) {
        	throw new Exception("Email is already used with other account");
        }
        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        User savedUser = userRepository.save(newUser);
        
        UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
		
		
		SecurityContextHolder .getContext().setAuthentication(auth);
		
		
		String jwt=	JwtProvider.generateToken(auth);
		AuthResponse res=new AuthResponse();
		res.setJwt(jwt);
		res.setStatus(true);
		res.setMessage("register successfully");
		
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    
    
    
    
    
    //signup
    
    
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception {

       
        String userName=user.getEmail()
;
        String password =user.getPassword();
        
      
        
        UsernamePasswordAuthenticationToken auth=authenticate(userName,password);
		
		
		SecurityContextHolder .getContext().setAuthentication(auth);
		
		
		String jwt=	JwtProvider.generateToken(auth);
		AuthResponse res=new AuthResponse();
		res.setJwt(jwt);
		res.setStatus(true);
		res.setMessage("login successfully");
		
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    

    
    
    

	private UsernamePasswordAuthenticationToken authenticate(String userName, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails=customeUserDetailsService.loadUserByUsername(userName);
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid Username");
		}
		
		if(!password.equals(userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
		else {
			return new UsernamePasswordAuthenticationToken(userDetails,password);
		}
		
		
	}
}
