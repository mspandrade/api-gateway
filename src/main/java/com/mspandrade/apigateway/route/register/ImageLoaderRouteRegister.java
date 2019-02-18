package com.mspandrade.apigateway.route.register;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class ImageLoaderRouteRegister implements RouteRegister {

	private static final String ID = "render_file";
	
	@Value("${imageloader.basepath}")
	private String path;
	
	@Value("${imageloader.url}")
	private String url;
	
	@Value("${imageloader.path.render}")
	private String pathRender;
	
	@Override
	public void register(Builder builder) {
		
		builder.route(ID, route -> route
				
				.path(path).and()
				.method(HttpMethod.GET)
				.filters(filter -> filter.rewritePath("imageloader", "api/images"))
				.uri(url)
		);
	}

}
