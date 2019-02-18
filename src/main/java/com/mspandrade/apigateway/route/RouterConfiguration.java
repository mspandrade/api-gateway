package com.mspandrade.apigateway.route;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mspandrade.apigateway.route.register.ImageLoaderRouteRegister;
import com.mspandrade.apigateway.route.register.RouteRegister;

@Configuration
public class RouterConfiguration {

	@Bean
	@Autowired
	public Set<RouteRegister> routes(
			
			ImageLoaderRouteRegister imageRouteRegister
			) {
		
		Set<RouteRegister> routes = new HashSet<>(); 
		
		routes.add(imageRouteRegister);
		
		return routes;
	}
	
}
