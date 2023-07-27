package com.bagas.OAuth2_SpringDoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
	{

		http
        .authorizeHttpRequests(a -> a
        		.requestMatchers("/", "/error", "webjars/**").permitAll()
        		.anyRequest().authenticated())
        .logout(l -> l
        		.logoutSuccessUrl("/").permitAll())
        .csrf(c -> c
        		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
		.oauth2Login();
		return http.build();
					
	}
}
