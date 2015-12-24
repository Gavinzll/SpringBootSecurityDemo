package com.tenmax.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.csrf().disable();
		http
			.authorizeRequests()
						.antMatchers("/about").permitAll()
						.antMatchers("/admin/**").hasRole("ADMIN")
						.antMatchers("/provider/**").access("hasRole('ADMIN') and hasRole('PROVIDER')")
						.antMatchers("/**").hasRole("USER")
						.and()
						.httpBasic()
						;
		// @formatter:on				
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		auth
			.inMemoryAuthentication()
						.withUser("admin").password("adminTenmax").roles("ADMIN", "PROVIDER", "USER")
						.and()
						.withUser("provider").password("providerTenmax").roles("PROVIDER")
						.and()
						.withUser("user").password("userTenmax").roles("USER")
						;
		// @formatter:on
	}

}
