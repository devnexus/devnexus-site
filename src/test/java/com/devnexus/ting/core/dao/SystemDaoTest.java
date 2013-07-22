/**
 *
 */
package com.devnexus.ting.core.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 *
 */
public class SystemDaoTest extends BaseDaoIntegrationTest {

	@Autowired private SystemDao systemDao;

	/**
	 *
	 */
	@Test
	@Ignore
	public void testGenerateSchema() {
		//systemDao.createDatabase(true, "org.hibernate.dialect.MySQLDialect");
		//systemDao.createDatabase(true, "org.hibernate.dialect.PostgreSQLDialect");
		//systemDao.createDatabase(true, "org.hibernate.dialect.H2Dialect");
	}

}
