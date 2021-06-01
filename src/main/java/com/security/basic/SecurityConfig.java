package com.security.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@ComponentScan("com")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		//.antMatchers("/").hasAnyRole("ADMIN").anyRequest().authenticated()
		.antMatchers("/api/recipes").hasAnyRole("ADMIN").anyRequest().authenticated()
		.and().httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Autowired
	protected void globalAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("PARASU").password("{noop}Raman").roles("ADMIN");
	}

}
