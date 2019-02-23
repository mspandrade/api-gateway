package com.mspandrade.apigateway.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.mspandrade.apigateway.dto.TokenDTO;
import com.mspandrade.apigateway.service.AuthorizationService;

public class AuthorizationFilter extends GenericFilterBean {
	
	
	private AuthorizationService authorizationService;
	
	public AuthorizationFilter(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
        HttpServletResponse response = (HttpServletResponse) res;
        String token = AuthorizationService.resolveToken((HttpServletRequest) req);
        
		if (token != null) {
			
			TokenDTO tokenDTO = authorizationService.getTokenInfo(token);
			
			if (tokenDTO != null && tokenDTO.isActive()) {
				
				SecurityContextHolder.getContext()
										.setAuthentication(tokenDTO.getAuthentication());
				
			} else {
				
				RuntimeException e = new InvalidAuthorizationException();
				
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
				throw e;
			}
        }
		chain.doFilter(req, res);	
		
	}
	
}
