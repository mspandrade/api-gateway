package com.mspandrade.apigateway;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import com.mspandrade.apigateway.route.register.RouteRegister;

@SpringBootApplication
public class ApiGatewayApplication {
	
	@Autowired
	private Set<RouteRegister> routeRegisters;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		
		RouteLocatorBuilder.Builder routeBuilder = builder.routes(); 
		
		routeRegisters.forEach(routeRegister -> routeRegister.register(routeBuilder));
		
		return routeBuilder.build();
	}
	
}
