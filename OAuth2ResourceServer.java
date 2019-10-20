package com.assignment.contactservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter  {

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http
    	.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/contacts**").hasAuthority("READ")
		.antMatchers(HttpMethod.POST, "/contacts**").hasAuthority("WRITE")
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
