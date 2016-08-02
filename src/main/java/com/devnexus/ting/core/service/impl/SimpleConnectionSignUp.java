/*
 * Copyright 2013-2015 the original author or authors.
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import com.devnexus.ting.core.service.UserService;
import com.devnexus.ting.core.service.exception.DuplicateUserException;
import com.devnexus.ting.model.AuthorityType;
import com.devnexus.ting.model.User;
import com.devnexus.ting.model.UserAuthority;

/**
 * Simple little {@link ConnectionSignUp} command that allocates new userIds in memory.
 * Doesn't bother storing a user record in any local database, since this quickstart just stores the user id in a cookie.
 *
 * @author Keith Donald
 * @author Gunnar Hillert
 */
public final class SimpleConnectionSignUp implements ConnectionSignUp {

	/**
	 * Initialize Logging.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	final UserService userService;

	public SimpleConnectionSignUp(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute(Connection<?> connection) {
		final UserProfile profile = connection.fetchUserProfile();
		final User user = new User();
		user.setFirstName(profile.getFirstName());
		user.setEmail(profile.getEmail());
		user.setLastName(profile.getLastName());
		user.setUsername(connection.getKey().toString());
		user.setPassword(null);

		user.getUserAuthorities().add(new UserAuthority(user, AuthorityType.APP_USER));

		final User createdUser;
		try {
			createdUser = this.userService.addUser(user);
		}
		catch (DuplicateUserException e) {
			LOGGER.error("User {} already exists. Exception: {}", user.getUsername(), e.getMessage());
			return null;
		}
		LOGGER.info("User {} with Id {} created.", createdUser.getUsername(), user.getId());
		return user.getUserId();
	}

}
