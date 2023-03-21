package com.badas.springboot.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {
	
	@GetMapping("/public")
	public String publicRoute() {
		return "<h1> Public route, feel free to look around :) </h1>";
	}
	
	@GetMapping("/cookie")
	public String cookie(@AuthenticationPrincipal OidcUser principal ) {
		return String.format("""
				<h1> Oauth2 route >:c </h1>
				<h3> Principal: %s</h3>
				<h3> Email Attribute: %s </h3>
				<h3> Authorities: %s </h3>
				<h3> JWT: %s </h3>
				""", principal, principal.getAttribute("email"), principal.getAuthorities(),
				principal.getIdToken().getTokenValue());
	}
	
	@GetMapping("/private")
	public String privateRoute(@AuthenticationPrincipal OidcUser principal ) {
		return String.format("""
				<h1> Private route, only authorized personal >:( </h1>
				""");
	}
	
	@GetMapping("/jwt")
	String jwt(@AuthenticationPrincipal Jwt infos) {
		return String.format("""
				Principal: %s\n
				Email attribute: %s\n
				JWT: %s\n
				""", infos.getClaims(), infos.getClaim("email"), infos.getTokenValue());
	}
}
