/**
 *
 */
package com.devnexus.ting.core.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 * @version $Id: IndustryDaoTest.java 605 2010-08-31 05:31:30Z ghillert $
 */
public class SystemDaoTest extends BaseDaoIntegrationTest {

	@Autowired private SystemDao systemDao;

	/**
	 * Test to verify that the seed data is correctly populated. Typically there
	 * should be 10 industries in the system:
	 *
	 */
	@Test
	public void testGenerateSchema() {
		//systemDao.createDatabase(true, "org.hibernate.dialect.MySQLDialect");
		systemDao.createDatabase(true, "org.hibernate.dialect.PostgreSQLDialect");
		//systemDao.createDatabase(true, "org.hibernate.dialect.H2Dialect");

	}

}
