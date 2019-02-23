package com.mspandrade.apigateway.config.security;

import org.springframework.http.HttpStatus;

public class InvalidAuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final HttpStatus httpStatus;

	public InvalidAuthorizationException() {
		super("Invalid authorization");
		this.httpStatus = HttpStatus.UNAUTHORIZED;
	}
	
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
	
}
