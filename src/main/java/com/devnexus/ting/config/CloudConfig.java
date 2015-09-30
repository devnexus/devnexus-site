package com.devnexus.ting.config;

import javax.sql.DataSource;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.UriBasedServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devnexus.ting.common.SpringProfile;
import com.sendgrid.SendGrid;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Profile(SpringProfile.CLOUD)
@Configuration
public class CloudConfig {

	@Bean
	public Cloud cloud() {
		final CloudFactory cloudFactory = new CloudFactory();
		return cloudFactory.getCloud();
	}

	@Bean
	public SendGrid sendGrid() {
		UriBasedServiceInfo serviceInfo = (UriBasedServiceInfo) cloud().getServiceInfo("devnexus-mail");
		return new SendGrid(serviceInfo.getUserName(), serviceInfo.getPassword());
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {

		final DataSource dataSource = cloud().getSingletonServiceConnector(DataSource.class, null);

		final HikariConfig config = new HikariConfig();
		config.setDataSource(dataSource);
		config.setConnectionTestQuery("select 1");
		config.setInitializationFailFast(true);
		return new HikariDataSource(config);
	}

}
