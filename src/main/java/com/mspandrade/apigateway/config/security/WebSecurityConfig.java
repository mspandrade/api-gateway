package com.mspandrade.apigateway.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.mspandrade.apigateway.service.AuthorizationService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private AuthorizationService authorizationService;
	
	public WebSecurityConfig(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();
        
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        http.authorizeRequests()
//                .antMatchers("/**/authorizations/**","/lastn").permitAll()
//                .anyRequest().authenticated();
        
        http.authorizeRequests()
        				.antMatchers("/**").permitAll();
        
        http.apply(new AuthorizationConfigurer(authorizationService));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        
        web.ignoring().antMatchers("/*/")//
                .antMatchers("/eureka/**")//
                .antMatchers(HttpMethod.OPTIONS, "/**"); 
    }

}
