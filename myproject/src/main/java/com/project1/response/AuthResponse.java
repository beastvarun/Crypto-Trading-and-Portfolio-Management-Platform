package com.project1.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
	private String jwt;
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isTwoFactorAuthEnabled() {
		return isTwoFactorAuthEnabled;
	}
	public void setTwoFactorAuthEnabled(boolean isTwoFactorAuthEnabled) {
		this.isTwoFactorAuthEnabled = isTwoFactorAuthEnabled;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	private boolean status;
	private String message;
	private boolean isTwoFactorAuthEnabled;
	private String session;
	

}
