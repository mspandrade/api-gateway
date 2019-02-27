package com.mspandrade.apigateway.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class TokenDTO {

	private String username;
	
	private UserDetails userDetails;
	
	private Authentication authentication;
	
	public TokenDTO(UserDetails userDetails) {
		
		username = userDetails.getUsername();
		
		this.userDetails = userDetails;
		
		authentication = new UsernamePasswordAuthenticationToken(
				userDetails, 
				"", 
				userDetails.getAuthorities()
				);
	}
	
}
