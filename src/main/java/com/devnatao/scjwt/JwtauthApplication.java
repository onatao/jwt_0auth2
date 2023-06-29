package com.devnatao.scjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class JwtauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtauthApplication.class, args);
	}

}

@RestController
class HttpController { 
	
	@GetMapping("/public")
	String publicRouder() {
		return "<h1>Public route, fell free to look around!<h1>";
	}
	
	/*
	 * OidcUser - Open ID connect
	 */
	@GetMapping("/private")
	String privateRouder() {
		return "<h1>Private  route, only authorized!<h1>";
	}
	
	/*
	 * 
	 */
	@GetMapping("/cookie")
	String cookie(@AuthenticationPrincipal OidcUser principal) {
		return String.format("""
				<h1>Oauth2<h1>
				Principal: %s</h3>
				Email attribute: %s</h3>
				Authorities: %s</h3>
				JWT: %s</h3>
				""",principal,  // return principal
				principal.getAttribute("email"), // return attribute named "email"
				principal.getAuthorities(), // return authorites
				principal.getIdToken().getTokenValue());  // return principals token jwt
	}
	
	/*
	 * 
	 */
	@GetMapping("/jwt")
	String jwt() {
		return String.format("""
				<h1> JWT </h1>
				""");
	}
}
