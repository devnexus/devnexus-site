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
