/*
 * Copyright 2002-2011 the original author or authors.
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
package com.devnexus.ting.core.service.impl;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;

import org.jasypt.digest.StringDigester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.userinfo.GoogleUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.devnexus.ting.core.dao.UserDao;
import com.devnexus.ting.core.model.AuthorityType;
import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.model.UserAuthority;
import com.devnexus.ting.core.service.UserService;
import com.devnexus.ting.core.service.exception.DuplicateUserException;
import java.util.HashSet;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Provides user specific services.
 *
 * @author Gunnar Hillert
 *
 * @since 1.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService, SignInAdapter {

	/**
	 *   Initialize Logging.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * User Dao.
	 */
	@Autowired
	private UserDao userDao;

	@Autowired
	private StringDigester stringDigester;
//
//    //~~~~~Business Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//
	/** {@inheritDoc} */
	public User addUser(final User user) throws DuplicateUserException {

		Assert.notNull(user, "User must not be null.");

		User duplicateUser;

		try {
			duplicateUser = userDao.getUserByUsername(user.getUsername());
		} catch (EmptyResultDataAccessException e) {
			duplicateUser = null;
		}

		if (duplicateUser!= null) {
			throw new DuplicateUserException("User " + duplicateUser.getUsername()
				+ "(Id="  + duplicateUser.getId() + ") already exists!");
		}

		final User userToSave = new User();

		userToSave.setCreatedDate(new Date());
		userToSave.setEmail(user.getEmail());
		userToSave.setFirstName(user.getFirstName());
		userToSave.setLastName(user.getLastName());
		userToSave.setPassword(this.stringDigester.digest(user.getPassword()));
		userToSave.setRegistrationDate(new Date());
		userToSave.setUsername(user.getUsername());

		final User savedUser = userDao.save(userToSave);
		return savedUser;
	}

	/** {@inheritDoc} */
	@Override
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException, DataAccessException {

		final User user = userDao.getUserByUsername(userName.trim());

		if (user==null){
			LOGGER.warn("loadUserByUsername() - No user with id " + userName + " found.");
			throw new UsernameNotFoundException("loadUserByUsername() - No user with id " + userName + " found.");
		}

		LOGGER.info("User {} ({}) loaded.", new Object[] { user.getUsername(), user.getEmail()});

		return user;
	}

	@Override
	public void updateUser(User user) {
		userDao.save(user);
	}

	@Override
	public User getUser(Long userId) {
		return userDao.get(userId);
	}


    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {

        assert userDao != null;

        assert false;

        Person person = ((Google) connection.getApi()).plusOperations().getGoogleProfile();
        GoogleUserInfo info = ((Google) connection.getApi()).userOperations().getUserInfo();
        User u = new User();
        u.setEmail(info.getEmail());
        u.setFirstName(person.getGivenName());
        u.setLastName(person.getFamilyName());
        u.setUsername(info.getId());
        u.setUserAuthorities(new HashSet<UserAuthority>(1));
        u.getUserAuthorities().add(new UserAuthority(u, AuthorityType.APP_USER));
        u.setId((long) info.getId().hashCode());

        if (null == userDao.getUserByUsername(u.getUsername())) {
            byte[] password = new byte[16];
            new SecureRandom().nextBytes(password);

            LOGGER.info(Arrays.toString(password));

            u.setPassword(Arrays.toString(password));
            userDao.save(u);
        }

        u = userDao.getUserByUsername(u.getUsername());
        
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities()));

        return null;
    }

    @Override
    public void initializeUserforEvent(User user, String eventKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
