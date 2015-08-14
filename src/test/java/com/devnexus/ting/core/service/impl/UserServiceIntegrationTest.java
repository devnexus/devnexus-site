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
package com.devnexus.ting.core.service.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.common.IntegrationTestApplicationContextInitializer;
import com.devnexus.ting.common.SpringProfile;
import com.devnexus.ting.config.ServicesConfig;
import com.devnexus.ting.core.service.SystemSetupService;
import com.devnexus.ting.core.service.UserService;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers=IntegrationTestApplicationContextInitializer.class,
					classes=ServicesConfig.class)
@Transactional
@ActiveProfiles({SpringProfile.DEFAULT})
public class UserServiceIntegrationTest {

	protected @PersistenceContext EntityManager entityManager;

	@Autowired private UserService userService;
	
	@Autowired
	private SystemSetupService systemSetupService;

	@Before
	public void setup() {
		if (!systemSetupService.isDatabaseSetup()) {
			systemSetupService.setupDatabase();
		}
	}

	@Test
	@Ignore
	public void checkRoles() {

		entityManager.flush();
		final UserDetails userDetails = userService.loadUserByUsername("admin");

		Assert.assertNotNull(userDetails);

		final Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

		Assert.assertTrue("Was expecting 1 authority but got: " + authorities.size(), authorities.size() == 1);

	}
}
