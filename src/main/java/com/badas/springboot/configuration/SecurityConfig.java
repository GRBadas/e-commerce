package com.badas.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	//filtros
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(
						authorizeConfig -> {
							authorizeConfig.antMatchers("/public").permitAll(); // Essa URI será publica
							authorizeConfig.antMatchers("/logout").permitAll(); // Essa URI será publica
							authorizeConfig.anyRequest().authenticated();  // Qualquer requisição deve ser feita por alguém autenticado : primeira coisa que fazemos por convenção  
						}) // Dentro dos parênteses configura como autorizar as requisições
				.oauth2Login(Customizer.withDefaults()) // manter o formulario de login default
				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
				.build();
	}
	
}
