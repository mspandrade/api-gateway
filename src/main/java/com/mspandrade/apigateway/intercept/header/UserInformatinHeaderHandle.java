package com.mspandrade.apigateway.intercept.header;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UserInformatinHeaderHandle extends HttpServletRequestWrapper {

	private static final String USERNAME_HEADERNAME = "X-Username";
	private static final String ROLE_HEADERNAME = "X-Role";
	private static final String PERMISSIONS_HEADERNAME = "X-Permissions";
	
	private static final String DEMILITER_PERMISSIONS = ",";
	
	private final Map<String, String> headers;
	
	public UserInformatinHeaderHandle(HttpServletRequest request) {
		super(request);
		headers = new HashMap<>();
	}
	
	public void setUsername(String username) {
		headers.put(USERNAME_HEADERNAME, username);
	}
	
	public void setRole(String role) {
		headers.put(ROLE_HEADERNAME, role);
	}
	
	public void setPermissions(Set<String> permissions) {
		headers.put(
				PERMISSIONS_HEADERNAME, 
				String.join(DEMILITER_PERMISSIONS, permissions)
				);	
	}
	
	@Override
	public String getHeader(String name) {
		
		String value;
		
		if (headers.containsKey(name)) {
			
			value = headers.get(name);
		} else {
			value = super.getHeader(name);
		}
		return value;
	}
	
}
