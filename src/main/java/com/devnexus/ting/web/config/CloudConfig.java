package com.devnexus.ting.web.config;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;

@Profile("cloud")
@Configuration
public class CloudConfig extends AbstractCloudConfig {
	@Bean
	public MailSender mailSender() {
		return connectionFactory().service(MailSender.class);
	}
}
