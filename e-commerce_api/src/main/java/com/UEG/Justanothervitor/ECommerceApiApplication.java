package com.UEG.Justanothervitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ECommerceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApiApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/products/**").allowedOrigins("http://localhost:3000");
				registry.addMapping("api/auth/login").allowedOrigins("http://localhost:3000");
				registry.addMapping("api/auth/signup").allowedOrigins("http://localhost:3000");
				registry.addMapping("/api/test/**").allowedOrigins("http://localhost:3000");
			}
		};
	}
}
