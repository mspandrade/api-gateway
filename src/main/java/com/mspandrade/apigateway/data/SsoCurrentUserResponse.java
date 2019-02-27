package com.mspandrade.apigateway.data;

import lombok.Data;

@Data
public class SsoCurrentUserResponse {

	private Long id;
	private String username;
	private String loginType;
	
}
