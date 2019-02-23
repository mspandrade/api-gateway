package com.mspandrade.apigateway.config.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mspandrade.apigateway.service.AuthorizationService;

public class AuthorizationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private AuthorizationService authorizationService;
	
	public AuthorizationConfigurer(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
		AuthorizationFilter authorizationFilter = new AuthorizationFilter(authorizationService);
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }
	
}
