package com.backendecommerce.backendecommerce.auth;


import java.io.IOException;
import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;


@Configuration
public class MyAppWebMvcConfigurer implements WebMvcConfigurer {

	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/repository/uploads3/**"
				,"/*"
				,"/"
				).permitAll();		

		http.authorizeRequests()
		//.antMatchers(HttpMethod.POST, "/*").permitAll()

		.antMatchers("/index.html").permitAll()
		.antMatchers("/login/config").permitAll()
		.antMatchers("/assets/*").permitAll()
		.antMatchers("/socket/**").permitAll()
		.antMatchers("/api/**").permitAll()


		.antMatchers(HttpMethod.POST, "/*").hasAnyRole("ROOT", "ADMIN","ADMINISTRADOR","LOGUEADO")
		
		.antMatchers(HttpMethod.POST,"/*").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		//.cors().configurationSource(corsConfigurationSource())
				;
	
	}

		

}