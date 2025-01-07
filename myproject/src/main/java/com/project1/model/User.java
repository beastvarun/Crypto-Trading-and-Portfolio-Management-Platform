package com.project1.model;
import com.project1.domain.USER_ROLE;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	private String fullName;
	private String email;
	private String password;
	
	private TwoFactorAuth twoFactorAuth= new TwoFactorAuth();
	
	private USER_ROLE role=USER_ROLE.ROLE_CUSTOMER;

	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public USER_ROLE getRole() {
		return role;
	}


	public void setRole(USER_ROLE role) {
		this.role = role;
	}


	public TwoFactorAuth getTwoFactorAuth() {
		return twoFactorAuth;
	}


	public void setTwoFactorAuth(TwoFactorAuth twoFactorAuth) {
		this.twoFactorAuth = twoFactorAuth;
	}


	
}
