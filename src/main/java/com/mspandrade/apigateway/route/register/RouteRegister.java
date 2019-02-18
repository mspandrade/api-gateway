package com.mspandrade.apigateway.route.register;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

public interface RouteRegister {

	public void register(RouteLocatorBuilder.Builder builder);
	
}
