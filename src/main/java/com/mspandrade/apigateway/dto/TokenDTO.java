package com.mspandrade.apigateway.dto;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class TokenDTO {

	private String username;
	private Date validUntil;
	private Boolean isLocked;
	private Boolean isDisable;
	
	private UserDetails userDetails;
	
	private Authentication authentication;
	
	public TokenDTO() {
		
		authentication = new UsernamePasswordAuthenticationToken(
				userDetails, 
				"", 
				userDetails.getAuthorities()
				);
	}
	
	public Boolean isActive() {
		return isLocked && isDisable && validUntil.after(new Date());
	}
	
}
