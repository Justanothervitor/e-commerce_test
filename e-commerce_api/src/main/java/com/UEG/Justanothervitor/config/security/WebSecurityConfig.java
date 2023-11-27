package com.UEG.Justanothervitor.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

import com.UEG.Justanothervitor.config.CustomizeAuthenticationSucessHandler;
import com.UEG.Justanothervitor.services.CustomUserDetailsServices;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WsConfigurerAdapter
{
	@Autowired
	private BCryptPasswordEncoder bCryptoPasswordEncoder;
	
	@Autowired
	CustomizeAuthenticationSucessHandler customizeAuthenticationSuccessHandler;
	
	@Bean
	public UserDetailsService mongoUserDetails ()
	{
		return new CustomUserDetailsServices();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		UserDetailsService userDetailsService = mongoUserDetails();
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptoPasswordEncoder);
	}
	
	@SuppressWarnings("deprecation")
	protected void configure (HttpSecurity http) throws Exception{
		http.authorizeHttpRequests().requestMatchers("/login").permitAll()
		.requestMatchers("/signup").permitAll()
		.requestMatchers("/dashboard").hasAuthority("CLIENT").anyRequest()
		.authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
		.loginPage("/login").failureUrl("/login?error=true")
		.usernameParameter("email")
		.passwordParameter("password")
		.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/").and().exceptionHandling();
	}
	
	public void configure (WebSecurity web) throws Exception{
		web.ignoring().requestMatchers("/resources/**","/static/**","/css/**","/js/**","/images/**");
	}
}