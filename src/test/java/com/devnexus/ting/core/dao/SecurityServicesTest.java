/**
 *
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
