/*
 * Copyright 2013 the original author or authors.
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

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;

import com.devnexus.ting.core.service.UserService;
import com.devnexus.ting.core.service.impl.SimpleConnectionSignUp;
import com.devnexus.ting.model.User;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;

/**
 * Spring Social Configuration.
 *
 * @author Keith Donald
 */
@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    @Value("#{environment.TING_CLIENT_ID}")
    private String clientId;

    @Value("#{environment.TING_CLIENT_SECRET}")
    private String clientSecret;

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;
    
    /**
     * A proxy to a request-scoped object representing the current user's
     * primary Google account.
     *
     * @throws NotConnectedException if the user is not connected to google.
     */
    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Google google() {
        final ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new GoogleConnectionFactory(clientId, clientSecret));
        
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                registry, Encryptors.noOpText());
        repository.setConnectionSignUp(new SimpleConnectionSignUp());
        
        
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.createConnectionRepository(user.getId().toString()).getPrimaryConnection(Google.class).getApi();
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(new GoogleConnectionFactory(clientId, clientSecret));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new UserIdSource() {

            @Override
            public String getUserId() {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return user.getId().toString();
            }
        };
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        final ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new GoogleConnectionFactory(clientId, clientSecret));
        
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                registry, Encryptors.noOpText());
        repository.setConnectionSignUp(new SimpleConnectionSignUp());
        
        
        
        return repository;
        
    }

}
