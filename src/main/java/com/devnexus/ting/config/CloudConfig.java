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
package com.devnexus.ting.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devnexus.ting.common.SpringProfile;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Profile(SpringProfile.CLOUD)
@Configuration
public class CloudConfig {

	@Autowired
	private DataSourceProperties dataSourceProperties;

	@Bean
	public Cloud cloud() {
		final CloudFactory cloudFactory = new CloudFactory();
		return cloudFactory.getCloud();
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {

		final DataSource dataSource;

		try {
			dataSource = cloud().getSingletonServiceConnector(DataSource.class, null);
		}
		catch (CloudException e) {
			HikariConfig config = new HikariConfig();
			config.setDriverClassName(dataSourceProperties.getDriverClassName());
			config.setJdbcUrl(dataSourceProperties.getUrl());
			config.setUsername(dataSourceProperties.getUsername());
			config.setPassword(dataSourceProperties.getPassword());
			config.setConnectionTestQuery("select 1");
			config.setInitializationFailFast(true);

			final HikariDataSource hikariDataSource = new HikariDataSource(config);

			return hikariDataSource;
		}

		return dataSource;
	}

}
