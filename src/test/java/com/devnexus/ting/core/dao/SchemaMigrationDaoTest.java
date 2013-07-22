/**
 *
 */
package com.devnexus.ting.core.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.devnexus.ting.core.model.SchemaMigration;

/**
 * @author Gunnar Hillert
 *
 */
public class SchemaMigrationDaoTest extends BaseDaoIntegrationTest {

	@Autowired private SchemaMigrationDao schemaMigrationDao;

	/**
	 * Test to verify that the seed data is correctly populated. Typically there
	 * should be 10 industries in the system:
	 *
	 */
	@Test
	public void testCreateSchemaMigrationEntry() {

		SchemaMigration schemaMigration = new SchemaMigration("2.0-Beta");

		schemaMigrationDao.save(schemaMigration);
		super.entityManager.flush();

		List<SchemaMigration> schemaMigrations = schemaMigrationDao.getAll();

		Assert.assertTrue(schemaMigrations.size() == 1);
		Assert.assertEquals("2.0-Beta", schemaMigrations.get(0).getVersion());
	}

}
