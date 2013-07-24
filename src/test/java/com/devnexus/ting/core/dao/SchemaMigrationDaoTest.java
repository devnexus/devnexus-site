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
