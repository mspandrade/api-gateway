package com.mspandrade.apigateway.service;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mspandrade.apigateway.data.SsoCurrentUserResponse;
import com.mspandrade.apigateway.dto.TokenDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthorizationService {
	
	@Value("${sso.authentication-url}")
	private String authenticationUrl;
	
	private Gson gson;
	
	private static final String AUTHORIZATION="Authorization";
	
	public AuthorizationService() {
		gson = new Gson();
	}

	public static String resolveToken(HttpServletRequest request) {
		
		String bearerToken = request.getHeader(AUTHORIZATION);
		
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			
            return bearerToken;
        }
		return null;
	}
	
	public TokenDTO getTokenInfo(String token) {
		
		TokenDTO tokenDTO = null;
		
		try {
			
			HttpResponse<String> response = Unirest.get(authenticationUrl).header(AUTHORIZATION, token).asString();
			
			if (response.getStatus() == HttpStatus.OK.value()) {
				
				SsoCurrentUserResponse user = gson.fromJson(
														response.getBody(), 
														SsoCurrentUserResponse.class
														);
				
				final User userDetails = new User(
							user.getUsername(),
							"", 
							Arrays.asList(new SimpleGrantedAuthority(user.getLoginType()))
							);
				
				tokenDTO = new TokenDTO(userDetails);
				
			} else {
				log.error("FALHA AO TENTAR VALIDAR TOKEN EM SSO mensagem: " + response.getBody());
			}
			
		} catch (UnirestException e) {
			log.error("ERRO AO TENTAR VALIDAR TOKEN EM SSO", e);
		}
		
		return tokenDTO;
	}
	
	
	
}
