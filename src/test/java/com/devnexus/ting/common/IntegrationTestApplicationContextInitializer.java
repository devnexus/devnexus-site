/*
 * Copyright 2014 the original author or authors.
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
package com.devnexus.ting.common;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 *
 * @author Gunnar Hillert
 *
 */
public class IntegrationTestApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationTestApplicationContextInitializer.class);
	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {

		LOGGER.info("System property 'ting-spring-profile' not set. Setting active profile to '{}'.", SpringContextMode.DemoContextConfiguration.getCode());
		applicationContext.getEnvironment().setActiveProfiles(SpringContextMode.DemoContextConfiguration.getCode());

		final ConfigurableEnvironment environment = applicationContext.getEnvironment();

		final String demoPropertySourceLocation = "classpath:ting-demo.properties";
		final ResourcePropertySource propertySource;
		try {
			propertySource = new ResourcePropertySource(demoPropertySourceLocation);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to get ResourcePropertySource '" + demoPropertySourceLocation + "'", e);
		}
		environment.getPropertySources().addFirst(propertySource);
		LOGGER.info("Properties for demo mode loaded [" + demoPropertySourceLocation + "]");

	}

}
