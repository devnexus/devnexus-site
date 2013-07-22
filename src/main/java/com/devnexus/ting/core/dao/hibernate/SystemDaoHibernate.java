/*
 * Copyright 2002-2011 the original author or authors.
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
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.SystemDao;

@SuppressWarnings("deprecation")
@Repository("systemDao")
public class SystemDaoHibernate implements SystemDao {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private LocalContainerEntityManagerFactoryBean fb;

	@Override
	public void updateDatabase() {

		final Ejb3Configuration cfg = new Ejb3Configuration();
		final Ejb3Configuration configured = cfg.configure( fb.getPersistenceUnitInfo(), fb.getJpaPropertyMap() );
		final Configuration configuration = configured.getHibernateConfiguration();

		HibernateHack.dataSource = dataSource;

		Properties props = new Properties();
		props.put("hibernate.connection.provider_class",
		 "com.devnexus.ting.core.dao.hibernate.SystemDaoHibernate.HibernateHack");


		final org.hibernate.tool.hbm2ddl.SchemaUpdate schemaUpdate;

		try {
			schemaUpdate = new SchemaUpdate(configuration, props);
		} catch (HibernateException e) {
			throw new IllegalStateException(e);
		}

		schemaUpdate.execute(true, true);

	}

	@Override
	public void createDatabase(boolean outputOnly, String dialect) {

		final Ejb3Configuration cfg = new Ejb3Configuration();

		final Map<String, Object> properties = fb.getJpaPropertyMap();

		if (dialect != null) {
			properties.put("hibernate.dialect", dialect);
		}

		final Ejb3Configuration configured = cfg.configure( fb.getPersistenceUnitInfo(), fb.getJpaPropertyMap() );
		final Configuration configuration = configured.getHibernateConfiguration();

		final SchemaExport schemaExport;

		try {
			schemaExport = new SchemaExport(configuration, dataSource.getConnection());
		} catch (HibernateException e) {
			throw new IllegalStateException(e);
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}

		schemaExport.create(true, !outputOnly);

	}

	private static class HibernateHack {
		@SuppressWarnings("unused")
		public static DataSource dataSource;
	}


}
