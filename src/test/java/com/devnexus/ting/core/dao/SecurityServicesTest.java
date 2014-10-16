/*
 * Copyright 2002-2014 the original author or authors.
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
package com.devnexus.ting.core.dao;

import org.jasypt.digest.StringDigester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.common.IntegrationTestApplicationContextInitializer;
import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.service.SystemSetupService;
import com.devnexus.ting.core.service.UserService;
import com.devnexus.ting.core.service.exception.DuplicateUserException;
import com.devnexus.ting.web.config.ServicesConfig;

/**
 * @author Gunnar Hillert
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers=IntegrationTestApplicationContextInitializer.class,
classes=ServicesConfig.class)
@Transactional(readOnly=false)
public class SecurityServicesTest {

	@Autowired private SystemSetupService systemSetupService;

	@Before
	public void setup() {
		systemSetupService.setupDatabase();
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServicesTest.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private StringDigester stringDigester;

	@Autowired
	UserService userService;

	@Autowired
	UserDao userDao;

	@Test
	public void testPasswordEncoder() {
		final String password = passwordEncoder.encodePassword("testing", null);
		Assert.assertNotNull(password);
		LOGGER.info("Password: " + password);
	}

	@Test
	public void testPasswordDigester() {
		Assert.assertTrue(stringDigester.matches("testing", "JW/oXfxGmHFlzJeiLMwwUOsC3cQhSpSNAcFHVZkgRdXpoXleWGENSyX+Hpv9NFXO3q/0kI/4r+csayLAH4hU/De1tQRSFkBsnyOhMc8OCxA="));
	}

	@Test
	public void testUserLogin() throws DuplicateUserException {
		final User user = new User();
		user.setEmail("abc@testing.de");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setPassword("testing");
		user.setUsername("abc");
		User savedUser = userService.addUser(user);
		Assert.assertTrue(stringDigester.matches("testing", userService.loadUserByUsername(savedUser.getUsername()).getPassword()));
	}

}
