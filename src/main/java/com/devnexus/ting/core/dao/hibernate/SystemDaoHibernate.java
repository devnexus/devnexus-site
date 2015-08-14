/*
 * Copyright 2002-2015 the original author or authors.
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
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import com.devnexus.ting.config.PersistenceConfig;
import com.devnexus.ting.core.dao.SystemDao;
import com.devnexus.ting.core.hibernate.ImprovedPluralizedNamingStrategy;

@Repository("systemDao")
public class SystemDaoHibernate implements SystemDao {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private LocalContainerEntityManagerFactoryBean fb;

	@Override
	public void createDatabase(boolean outputOnly, String dialect) {

		final Map<String, Object> propertyMap = fb.getJpaPropertyMap();

		Properties properties = new Properties();
		properties.putAll(propertyMap);

		if (dialect != null) {
			properties.put("hibernate.dialect", dialect);
		}

		Configuration configuration = new Configuration();

		for (Class<?> clazz : PersistenceConfig.getAnnotatedPersistenceClasses()) {
			configuration.addAnnotatedClass(clazz);
		}

		configuration.addProperties(properties);
		configuration.setNamingStrategy(new ImprovedPluralizedNamingStrategy());

		StringBuilder builder = new StringBuilder();

		for (String line : configuration.generateSchemaCreationScript(new PostgreSQL9Dialect())) {
			builder.append(line + "\n");
		}

		builder.toString();

		final SchemaExport schemaExport;

		try {
			schemaExport = new SchemaExport(configuration,
				dataSource.getConnection());
		}
		catch (HibernateException e) {
			throw new IllegalStateException(e);
		}
		catch (SQLException e) {
			throw new IllegalStateException(e);
		}

		schemaExport.create(true, !outputOnly);

	}

}
