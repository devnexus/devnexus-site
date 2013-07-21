package com.devnexus.ting.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 * @version $Id: BaseTest.java 598 2010-08-22 20:18:58Z ghillert $
 */
@ContextConfiguration(
		locations={ "classpath:spring/mainApplicationContext.xml"})
//@ActiveProfiles("standalone")
public abstract class BaseDaoIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

	protected @PersistenceContext(unitName="base") EntityManager entityManager;
}
