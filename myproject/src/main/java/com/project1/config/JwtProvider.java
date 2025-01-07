package com.project1.config;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
	
	public static SecretKey key=Keys.hmacShaKeyFor(JwtConstant.SECRETE_KEY.getBytes());
	
	public static String generateToken(org.springframework.security.core.Authentication auth) {
		Collection<? extends GrantedAuthority> authorities=auth.getAuthorities();
		String roles=populateAuthorities(authorities);
		
		
		String jwt=Jwts.builder()
		        .setIssuedAt(new Date())
		        .setExpiration(new Date(new Date().getTime() + 864000))
		        .claim("email",auth.getName())
		        .claim("authorities", roles)
		        .signWith(key)
		        .compact();
		
		return jwt;
		
		
		
	}
	
	public static String getEmailsFromToken(String Token) {
	    String token = Token.substring(7);
	    Claims claims = Jwts.parserBuilder()  // Use parserBuilder() instead of parser()
	            .setSigningKey(key)  // Set the signing key
	            .build()  // Build the JwtParser
	            .parseClaimsJws(token)
	            .getBody();
	    String email = String.valueOf(claims.get("email"));
	    return email;
	}

	private static String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		// TODO Auto-generated method stub
		Set<String> auth=new HashSet<>();
		for(GrantedAuthority ga:authorities)
		{
			auth.add(ga.getAuthority());
		}
		return String.join(",", auth);
		
	}
	

}
