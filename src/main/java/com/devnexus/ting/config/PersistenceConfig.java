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
package com.devnexus.ting.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.sql.DataSource;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;

import com.devnexus.ting.common.SpringProfile;
import com.devnexus.ting.repository.jpa.BaseRepositoryImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


/**
 * @author Gunnar Hillert
 *
 */
@Configuration
@EnableJpaRepositories(repositoryBaseClass=BaseRepositoryImpl.class,
		entityManagerFactoryRef="entityManagerFactory", basePackages="com.devnexus.ting.repository")
public class PersistenceConfig {

	private static final String PERSISTENCE_BASE_PACKAGE = "com/devnexus/ting/model";

	@Autowired
	private DataSourceProperties dataSourceProperties;

	@Autowired
	private JpaProperties jpaSettings;

	@Bean
	public Jaxb2Marshaller jaxbMarshaller() {
		Jaxb2Marshaller jaxbMarshaller = new Jaxb2Marshaller();
		jaxbMarshaller.setClassesToBeBound(

			com.devnexus.ting.model.Evaluation.class,
			com.devnexus.ting.model.EvaluationList.class,
			com.devnexus.ting.model.Event.class,
			com.devnexus.ting.model.Presentation.class,
			com.devnexus.ting.model.PresentationList.class,
			com.devnexus.ting.model.Room.class,
			com.devnexus.ting.model.RoomList.class,
			com.devnexus.ting.model.ScheduleItem.class,
			com.devnexus.ting.model.ScheduleItemList.class,
			com.devnexus.ting.model.Sponsor.class,
			com.devnexus.ting.model.Speaker.class,
			com.devnexus.ting.model.SpeakerList.class,
			com.devnexus.ting.model.ScheduleItemType.class,
			com.devnexus.ting.model.CfpSubmission.class,
			com.devnexus.ting.model.CfpSubmissionList.class,
			com.devnexus.ting.model.TicketGroup.class
		);
		return jaxbMarshaller;
	}

	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	@Profile({SpringProfile.STANDALONE, SpringProfile.DEFAULT, SpringProfile.DEMO, SpringProfile.CLOUD})
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan(PERSISTENCE_BASE_PACKAGE);

		final Map<String, Object> jpaProperties = new HashMap<>();

		jpaProperties.put("hibernate.dialect", this.jpaSettings.getProperties().get("dialect"));
		jpaProperties.put("hibernate.generate_statistics", false);
		jpaProperties.put("hibernate.show_sql", this.jpaSettings.isShowSql());
		jpaProperties.put("hibernate.format_sql", true);
		jpaProperties.put("hibernate.ejb.naming_strategy", "com.devnexus.ting.core.hibernate.ImprovedPluralizedNamingStrategy");

		entityManagerFactory.setJpaPropertyMap(jpaProperties);

		return entityManagerFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}

	@Profile({SpringProfile.DEMO, SpringProfile.STANDALONE})
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {

		HikariConfig config = new HikariConfig();
		config.setDriverClassName(dataSourceProperties.getDriverClassName());
		config.setJdbcUrl(dataSourceProperties.getUrl());
		config.setUsername(dataSourceProperties.getUsername());
		config.setPassword(dataSourceProperties.getPassword());
		config.setConnectionTestQuery("select 1");
		config.setInitializationFailFast(true);

		HikariDataSource dataSource = new HikariDataSource(config);

		return dataSource;
	}

	public static Class<?>[] getAnnotatedPersistenceClasses() {
		Reflections reflections = new Reflections(PERSISTENCE_BASE_PACKAGE);
		Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);
		entityClasses.addAll(reflections.getTypesAnnotatedWith(MappedSuperclass.class));
		return entityClasses.toArray(new Class<?>[0]);
	}

	@Bean
	PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
