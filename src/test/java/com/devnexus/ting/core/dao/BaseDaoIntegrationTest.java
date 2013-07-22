package com.devnexus.ting.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.core.service.SystemSetupService;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations={ "classpath:spring/mainApplicationContext.xml"})
@Transactional
public abstract class BaseDaoIntegrationTest {

	protected @PersistenceContext EntityManager entityManager;

	@Autowired private SystemSetupService systemSetupService;

	@Before
	public void setup() {
		systemSetupService.setupDatabase();
	}
}
