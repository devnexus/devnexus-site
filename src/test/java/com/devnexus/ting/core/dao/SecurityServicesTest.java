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
package com.devnexus.ting.core.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * @author Gunnar Hillert
 */
@SuppressWarnings("deprecation")
public class SecurityServicesTest extends BaseDaoIntegrationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServicesTest.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void testPasswordEncoder() {
		final String password = passwordEncoder.encodePassword("testing", null);
		LOGGER.info("Password: " + password);
	}

}
