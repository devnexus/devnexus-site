/*
 * Copyright 2002-2014 the original author or authors.
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
package com.devnexus.ting.web.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.devnexus.ting.common.SpringProfile;
import com.jolbox.bonecp.BoneCPDataSource;


/**
 * @author Gunnar Hillert
 *
 */
@Configuration
@ComponentScan("com.devnexus.ting.core.dao")
public class PersistenceConfig {

	@Autowired
	Environment environment;

	@Bean
	public Jaxb2Marshaller jaxbMarshaller() {
		Jaxb2Marshaller jaxbMarshaller = new Jaxb2Marshaller();
		jaxbMarshaller.setClassesToBeBound(
			com.devnexus.ting.core.model.Evaluation.class,
			com.devnexus.ting.core.model.EvaluationList.class,
			com.devnexus.ting.core.model.Event.class,
			com.devnexus.ting.core.model.Presentation.class,
			com.devnexus.ting.core.model.PresentationList.class,
			com.devnexus.ting.core.model.Room.class,
			com.devnexus.ting.core.model.RoomList.class,
			com.devnexus.ting.core.model.ScheduleItem.class,
			com.devnexus.ting.core.model.ScheduleItemList.class,
			com.devnexus.ting.core.model.Sponsor.class,
			com.devnexus.ting.core.model.Speaker.class,
			com.devnexus.ting.core.model.SpeakerList.class,
			com.devnexus.ting.core.model.ScheduleItemType.class,
			com.devnexus.ting.core.model.CfpSubmission.class,
			com.devnexus.ting.core.model.CfpSubmissionList.class
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
	@Profile({SpringProfile.STANDALONE, SpringProfile.DEFAULT, SpringProfile.DEMO})
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan("com/devnexus/ting/core/model");

		final Map<String, Object> jpaProperties = new HashMap<>();

		jpaProperties.put("hibernate.dialect", getHibernateDialect());
		jpaProperties.put("hibernate.generate_statistics", true);
		jpaProperties.put("hibernate.cache.use_second_level_cache", true);
		jpaProperties.put("hibernate.cache.use_query_cache", true);
		jpaProperties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		jpaProperties.put("hibernate.show_sql", isShowHibernateSql());
		jpaProperties.put("hibernate.format_sql", true);
		jpaProperties.put("hibernate.ejb.naming_strategy", "com.devnexus.ting.core.hibernate.ImprovedPluralizedNamingStrategy");

		entityManagerFactory.setJpaPropertyMap(jpaProperties);

		return entityManagerFactory;
	}

	private boolean isShowHibernateSql() {
		return environment.getRequiredProperty("database.hibernate.show_sql", Boolean.class);
	}

	private String getHibernateDialect() {
		return environment.getRequiredProperty("database.hibernate.dialect");
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();

		dataSource.setDriverClass(getDriverClassName());
		dataSource.setJdbcUrl(getUrl());
		dataSource.setUsername(getUser());
		dataSource.setPassword(getPassword());

		//dataSource.setValidationQuery(getDatabaseValidationQuery());

		dataSource.setIdleConnectionTestPeriodInMinutes(60);
		dataSource.setIdleMaxAgeInMinutes(240);
		dataSource.setMaxConnectionsPerPartition(10);
		dataSource.setMinConnectionsPerPartition(1);
		dataSource.setPartitionCount(3);
		dataSource.setAcquireIncrement(5);
		dataSource.setStatementsCacheSize(100);
		dataSource.setDisableJMX(true);

		return dataSource;
	}

	private String getPassword() {
		return environment.getRequiredProperty("database.jdbc.password");
	}

	private String getUser() {
		return environment.getRequiredProperty("database.jdbc.username");
	}

	private String getUrl() {
		return environment.getRequiredProperty("database.jdbc.url");
	}

	private String getDriverClassName() {
		return environment.getRequiredProperty("database.jdbc.driverClassName");
	}

	@Bean
	PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

//	@Bean
//	LocalValidatorFactoryBean validator() {
//		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
//		localValidatorFactoryBean.setValidationMessageSource(messageSource());
//		return localValidatorFactoryBean;
//	}

	@Bean
	ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("utf-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	/*
	<beans profile="cloud">
		<cloud:data-source id="devnexus-db" />
		<bean id="entityManagerFactory"
			class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
			<property name="dataSource" ref="devnexus-db" />
			<property name="jpaVendorAdapter" ref="hibernateJpaVendorAdapter" />
			<property name="persistenceUnitName" value="base" />
			<property name="jpaProperties">
				<props>
					<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
					<prop key="hibernate.query.substitutions">true '1', false '0'</prop>
					<prop key="hibernate.generate_statistics">true</prop>
					<prop key="hibernate.cache.use_second_level_cache">true</prop>
					<prop key="hibernate.cache.use_query_cache">true</prop>
					<prop key="hibernate.cache.region.factory_class">net.sf.ehcache.hibernate.EhCacheRegionFactory</prop>
					<prop key="hibernate.show_sql">false</prop>
					<prop key="hibernate.format_sql">true</prop>
					<prop key="hibernate.hbm2ddl.auto">create-drop</prop>
				</props>
			</property>
		</bean>
	</beans>
	 */
}
