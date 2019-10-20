package com.assignment.contactservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
				.password("password").roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/contacts").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/contacts/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/contactsbulk").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/contactsbulk/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/contacts").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/contacts/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/contacts").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/contacts/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/contacts").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.GET,"/contacts/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/","/heartbeat").permitAll()
		.and().httpBasic();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	

}
