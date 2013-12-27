/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.devnexus.ting.web.config;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Gunnar Hillert
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() //TODO Refactor login form
		.authorizeRequests().antMatchers("/s/admin/cfp**").hasAnyAuthority("ADMIN", "CFP_REVIEWER").and()
		.authorizeRequests().antMatchers("/s/admin/index").hasAnyAuthority("ADMIN", "CFP_REVIEWER").and()
		.authorizeRequests().antMatchers("/s/admin/**").hasAuthority("ADMIN").and()
		.authorizeRequests().antMatchers("/**").permitAll().anyRequest().anonymous().and()
		.logout().logoutSuccessUrl("/s/index").logoutUrl("/s/logout").permitAll().and()
		//.requiresChannel().antMatchers("/s/admin/**").requiresSecure().and()
		.formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/s/admin/index").loginPage("/s/login").failureUrl("/s/login?status=error").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public StandardStringDigester stringDigester() {
		StandardStringDigester standardStringDigester = new StandardStringDigester();
		standardStringDigester.setAlgorithm("SHA-512");
		standardStringDigester.setIterations(100000);
		standardStringDigester.setSaltSizeBytes(16);
		return standardStringDigester;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		passwordEncoder.setStringDigester(stringDigester());
		return passwordEncoder;
	}

}
