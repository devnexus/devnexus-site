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
package com.hillert.apptools.spring;

import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;

public class CloudApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		CloudEnvironment env = new CloudEnvironment();
		if (env.getInstanceInfo() != null) {
			System.out.println("cloud API: " + env.getCloudApiUri());
			applicationContext.getEnvironment().setActiveProfiles("cloud");
		}
		else {

			final String profile = System.getProperty("ting-spring-profile");

			Assert.hasText(profile, "No Profile retrieved from system property 'ting-spring-profile'");

			applicationContext.getEnvironment().setActiveProfiles(profile);
		}
	}

}
