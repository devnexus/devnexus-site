/*
 * Copyright 2002-2016 the original author or authors.
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
package com.devnexus.ting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import com.devnexus.ting.core.applicationlistener.SecurityEventListener;
import com.devnexus.ting.model.User;
import com.devnexus.ting.security.RoleAwareSimpleUrlAuthenticationSuccessHandler;

/**
 * @author Gunnar Hillert
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UsersConnectionRepository usersConnectionRepository;

	@SuppressWarnings("deprecation")
	@Autowired
	private org.springframework.security.authentication.encoding.PasswordEncoder passwordEncoder;

	@Autowired
	private Environment environment;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		HttpSecurity httpSecurity = http
		.csrf().disable() //TODO Refactor login form
		.authorizeRequests().antMatchers("/s/admin/cfp**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CFP_REVIEWER", "ROLE_APP_USER").and()
		.authorizeRequests().antMatchers("/s/admin/index").hasAnyAuthority("ROLE_ADMIN", "ROLE_CFP_REVIEWER", "ROLE_APP_USER").and()
		.authorizeRequests().antMatchers("/s/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_APP_USER").and()
		.authorizeRequests().antMatchers(
				"/s/cfp/index**", "/s/cfp/speaker**", "/s/cfp/abstract**",
				"/s/cfp/add-cfp-success**", "/s/cfp/speaker/**")
			.hasAnyAuthority("ROLE_ADMIN", "ROLE_APP_USER").and()
		.authorizeRequests().antMatchers("/**").permitAll().anyRequest().anonymous().and()
		.logout().logoutSuccessUrl("/s/index").logoutUrl("/s/logout").permitAll().and();

		if (environment.getRequiredProperty("server.ssl.enabled", Boolean.class)) {
			httpSecurity = httpSecurity.requiresChannel().antMatchers("/s/admin/**").requiresSecure().and();
			httpSecurity = httpSecurity.requiresChannel().antMatchers("/s/cfp/**").requiresSecure().and();
		}

		final RoleAwareSimpleUrlAuthenticationSuccessHandler successHandler = new RoleAwareSimpleUrlAuthenticationSuccessHandler();
		successHandler.setUseReferer(false);
		successHandler.setTargetUrlParameter("target");
		successHandler.setDefaultTargetUrl("/s/admin/index");

		httpSecurity.formLogin().loginProcessingUrl("/s/login")
			.loginPage("/s/login")
			.failureUrl("/s/login?status=error")
			.successHandler(successHandler)
			.permitAll();

		http.apply(new SpringSocialConfigurer()
			.postLoginUrl("/s/cfp/index"));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(socialAuthenticationProvider());
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public SecurityEventListener securityEventListener() {
		return new SecurityEventListener();
	}

	@Bean
	public SocialAuthenticationProvider socialAuthenticationProvider(){
		return new SocialAuthenticationProvider(usersConnectionRepository, socialUsersDetailService());
	}

	@Bean
	public SocialUserDetailsService socialUsersDetailService() {
		return new SocialUserDetailsService() {

			@Override
			public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
						User userDetails = (User) userDetailsService.loadUserByUsername(userId);
						return userDetails;
			}
		};
	}

}
