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
public class SoccoriusWebSecurity{
		
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Bean
	public UserDetailsService userDetailService() {
		return new SoccoriusUserDetailService();
	}
	
	public DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailService());
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
		
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("users/login").permitAll()
				.antMatchers("/**").hasAuthority("Admin")
					.anyRequest()
						.authenticated()
			.and()
			.formLogin( form -> {
				try {
					form
						.loginPage("login").loginProcessingUrl("login")
							.usernameParameter("email")
							.defaultSuccessUrl("/dashboard")
							.failureUrl("/login?error=true")
						.permitAll()
					.and()
						.logout()
						.logoutSuccessUrl("/login?logout=true")
						.permitAll()
					.and()
						.rememberMe()
							.key("acchcgcBCVfdsIOCGojg13vy85f43c0k98nbcx7")
							.tokenValiditySeconds(7 * 24 * 60 * 60);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				);
		
		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer(){
		
		return (web) -> web.ignoring().antMatchers("/images/**", "/css/**", "/js/**", "/webjars/**");
	}

}
