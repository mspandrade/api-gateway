package com.mspandrade.apigateway.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mspandrade.apigateway.dto.TokenDTO;

@Service
public class AuthorizationService {
	
	private static final String AUTHORIZATION="Authorization";

	public static String resolveToken(HttpServletRequest request) {
		
		String bearerToken = request.getHeader(AUTHORIZATION);
		
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			
            return bearerToken.substring(7, bearerToken.length());
        }
		return null;
	}
	
	public TokenDTO getTokenInfo(String token) {
		
		return null;
	}
	
	
	
}
