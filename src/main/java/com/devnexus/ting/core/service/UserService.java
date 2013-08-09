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
package com.devnexus.ting.core.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.service.exception.DuplicateUserException;

/**
 * Provides user related methods.
 *
 * @author Gunnar Hillert
 *
 */
public interface UserService {

	/**
	 * Adds a brand new user to the system. If a user
	 * with the username already exists a {@link DuplicateUserException}
	 * is thrown.
	 *
	 * @param user The user to add
	 * @throws DuplicateUserException
	 */
	User addUser(User user) throws DuplicateUserException;
//
//    /**
//     * Adds a brand new user to the system. If a user
//     * with the username already exists a duplicate user exception
//     * is thrown.
//     *
//     * @param user The user to add
//     * @param validationRequired Must this user be verified?
//     * @throws DuplicateUserException
//     */
//    User addUser(User user, Boolean verificationRequired) throws DuplicateUserException;
//
//    /**
//     * Load a user by the provided username
//     * @param username
//     * @return a single user
//     */
//    User getUser(String username);
//
//    /**
//     * Load a user by the provided user id.
//     * @param userId
//     * @return a single user
//     */
//    User getUser(Long userId);
//
//    /**
//     * Load a user by the provided user id.
//     * @param userId
//     * @return a single user
//     */
//    void updateUser(User user);
//
//    /**
//     * Get a list of all users.
//     * @return List of users
//     */
//    List<User> getAllUsers();
//
//    /**
//     * Delete a user from the system.
//     * @param user
//     */
//    void deleteUser(User user);
//
//    /**
//     * Reset the password for the provided user.
//     * @param user
//     */
//    void resetPassword(User user);

	/**
	 * This method is used by ACEGI security to load user details for authentication.
	 * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 *
	 * @param username Username
	 * @return Details of the user
	 * @throws DataAccessException
	 * @throws UsernameNotFoundException Thrown if no user was found in persistence store.
	 */
	UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException;

//    /**
//     * Get a user by its verification key. This method is used to verify user
//     * account creation.
//     *
//     * @param key Key for which the corresponding user supposedly exists
//     * @return Return a user for the existing key
//     */
//    User getUserByVerificationKey(String key);
}
