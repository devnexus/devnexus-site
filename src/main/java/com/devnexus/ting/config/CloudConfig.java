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
