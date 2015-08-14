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
package com.devnexus.ting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.devnexus.ting.core.applicationlistener.SecurityEventListener;
import com.devnexus.ting.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author Gunnar Hillert
 *
 */
@Configuration
@SuppressWarnings("deprecation")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("#{environment['https.enabled']}")
	private boolean httpsEnabled;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		HttpSecurity httpSecurity = http
		.csrf().disable() //TODO Refactor login form
		.authorizeRequests().antMatchers("/s/admin/cfp**").hasAnyAuthority("ADMIN", "CFP_REVIEWER").and()
		.authorizeRequests().antMatchers("/s/admin/index").hasAnyAuthority("ADMIN", "CFP_REVIEWER").and()
		.authorizeRequests().antMatchers("/s/admin/**").hasAuthority("ADMIN").and()
		.authorizeRequests().antMatchers("/**").permitAll().anyRequest().anonymous().and()
		.logout().logoutSuccessUrl("/s/index").logoutUrl("/s/logout").permitAll().and();

		if (httpsEnabled) {
			httpSecurity = httpSecurity.requiresChannel().antMatchers("/s/admin/**").requiresSecure().and();
		}

		httpSecurity.formLogin().loginProcessingUrl("/s/login")
			.defaultSuccessUrl("/s/admin/index")
			.loginPage("/s/login")
			.failureUrl("/s/login?status=error")
			.permitAll();
                
                
            http.apply(new SpringSocialConfigurer()
                .postLoginUrl("/")
                
                .alwaysUsePostLoginUrl(true));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public SecurityEventListener securityEventListener() {
		return new SecurityEventListener();
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
