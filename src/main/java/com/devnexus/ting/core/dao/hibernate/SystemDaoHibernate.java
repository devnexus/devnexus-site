/*
 * Copyright 2002-2016 the original author or authors.
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
package com.devnexus.ting.core.dao.hibernate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import com.devnexus.ting.config.PersistenceConfig;
import com.devnexus.ting.core.dao.SystemDao;
import com.devnexus.ting.core.hibernate.DevNexusSpringImplicitNamingStrategy;

/**
 * Keep this in mind for the next upgrade: http://stackoverflow.com/questions/32165694/spring-hibernate-5-naming-strategy-configuration
 *
 * @author Gunnar Hillert
 */
@Repository("systemDao")
public class SystemDaoHibernate implements SystemDao {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private LocalContainerEntityManagerFactoryBean fb;

	@Override
	public void createDatabase(boolean outputOnly, String dialect) {

		final Map<String, Object> propertyMap = fb.getJpaPropertyMap();

		final Map<String, Object> localPropertyMap = new HashMap<>(propertyMap);

		if (dialect != null) {
			localPropertyMap.put("hibernate.dialect", dialect);
		}

		final MetadataSources metadata = new MetadataSources(
			new StandardServiceRegistryBuilder()
				.applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect")
				.applySetting("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName())
				.applySetting("hibernate.implicit_naming_strategy", DevNexusSpringImplicitNamingStrategy.class.getName())
				.applySettings(localPropertyMap)
				.build());

		for (Class<?> clazz : PersistenceConfig.getAnnotatedPersistenceClasses()) {
			metadata.addAnnotatedClass(clazz);
		}
		final SchemaExport export;
		try {
			export = new SchemaExport((MetadataImplementor) metadata.buildMetadata(),
					dataSource.getConnection());
		} catch (HibernateException | SQLException e) {
			throw new IllegalStateException(e);
		}
		export.create(true, !outputOnly);

	}

}
