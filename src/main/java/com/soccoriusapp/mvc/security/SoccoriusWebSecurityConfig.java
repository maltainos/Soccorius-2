package com.soccoriusapp.mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SoccoriusWebSecurityConfig {

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Bean
	public UserDetailsService userDetailService() {
		return new SoccoriusUserDetailService();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailService());
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		  http
		  	.authorizeHttpRequests((requests) -> 
		  		requests
		  			.anyRequest().authenticated()
		  	).formLogin((form) -> 
		  		form
		  			.loginPage("/login")
		  			.usernameParameter("email")
		  			.defaultSuccessUrl("/")
		  			.permitAll()
		  	).logout((logout) -> 
		  		logout
		  			.permitAll()
		  	).rememberMe()
		  		.key("WebTokenRememberEquillibriumMaltainosDeveloper")
		  		.tokenValiditySeconds(60 * 60 * 60);
		  
		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer(){

		return (web) -> web.ignoring().antMatchers("/images/**", "/css/**", "/js/**", "/webjars/**");
	}
}
