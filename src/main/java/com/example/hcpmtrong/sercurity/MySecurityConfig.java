package com.example.hcpmtrong.sercurity;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		AccountAuthenticationFilter accountAuthenticatorFilter =
			new AccountAuthenticationFilter(authenticationManagerBean());
		//config lai duong dan login
		accountAuthenticatorFilter.setFilterProcessesUrl("/api/v1/accounts/login");
		http.sessionManagement().sessionCreationPolicy(STATELESS);
		http.authorizeHttpRequests().antMatchers("/api/v1/accounts/token/refresh").permitAll();
		//phan quyen user
		http.authorizeHttpRequests().antMatchers(GET, "/api/v1/accounts/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeHttpRequests().antMatchers(PUT, "/api/v1/accounts/**").hasAnyAuthority("ROLE_USER");
		http.authorizeHttpRequests().antMatchers(DELETE, "/api/v1/accounts/**").hasAnyAuthority("ROLE_SUPER_ADMIN");

		http.authorizeHttpRequests()
			.antMatchers("/api/v1/accounts", "/api/v1/accounts/login").permitAll()
			//khong duoc duplicate dong nay
			.anyRequest().authenticated()
			.and().csrf().disable();
		http.addFilter(accountAuthenticatorFilter);
		http.addFilterBefore(new AccountAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception  {
		return super.authenticationManagerBean();
	}


}
