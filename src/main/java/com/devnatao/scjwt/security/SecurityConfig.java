package com.devnatao.scjwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // class will be known as web configurer
public class SecurityConfig {
	
	/*
	 * Security are made through filters, filters can
	 * intercept an request and verify it
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests( // requests to public and private routes
					authorizeConfig -> {
						authorizeConfig.requestMatchers("/public").permitAll(); // public endpoints
						authorizeConfig.requestMatchers("/logout").permitAll(); // doesnt need to be authenticated
						authorizeConfig.anyRequest().authenticated(); // only authenticated users can perfom requests
					}
				)
				// .formLogin(Customizer.withDefaults()) // default spring security login screen
				.oauth2Login(Customizer.withDefaults()) // Oauth2 login screen - Google credentials
				.build();
	}

}
