/*
 * Copyright 2015 the original author or authors.
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
package com.devnexus.ting;

import org.jboss.aerogear.unifiedpush.SenderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.devnexus.ting.core.applicationlistener.ContextRefreshedEventListener;
import com.google.common.cache.CacheBuilder;

/**
 * Main entry point for the DevNexus application.
 *
 * @author Gunnar Hillert
 *
 */
@EnableCaching
@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class })
public class DevNexusApplication {

	@Autowired
	private Environment environment;

	@Autowired
	MessageSource messageSource;

	public static void main(String[] args) throws Exception {
		final SpringApplication application = new SpringApplication(DevNexusApplication.class);
		application.addInitializers(new DefaultApplicationContextInitializer());
		application.run(args);
	}

	@Bean
	public GuavaCacheManager cacheManager() {
		final CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
		.recordStats();
		final GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(cacheBuilder);
		return cacheManager;
	}

	@Bean
	ContextRefreshedEventListener seedDataEventListener() {
		return new ContextRefreshedEventListener();
	}

	@Bean
	public SenderClient javaSender() {
		return new SenderClient(environment.getRequiredProperty("TING_PUSH_URL"));
	}

	@Bean
	LocalValidatorFactoryBean validator() {
		final LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(this.messageSource);
		return localValidatorFactoryBean;
	}
}
