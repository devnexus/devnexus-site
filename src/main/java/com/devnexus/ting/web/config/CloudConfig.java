package com.devnexus.ting.web.config;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;

@Profile("cloud")
@Configuration
public class CloudConfig {
	@Bean
	public MailSender mailSender() {
		CloudFactory cloudFactory = new CloudFactory();
        Cloud cloud = cloudFactory.getCloud();
        ServiceInfo serviceInfo = cloud.getServiceInfo("devnexus-mail");
        String serviceID = serviceInfo.getId();
        return cloud.getServiceConnector(serviceID, MailSender.class, null);
	}

//	@Bean
//	public DataSource dataSource() {
//		CloudFactory cloudFactory = new CloudFactory();
//        Cloud cloud = cloudFactory.getCloud();
//        ServiceInfo serviceInfo = cloud.getServiceInfo("devnexus-pg");
//        String serviceID = serviceInfo.getId();
//        return cloud.getServiceConnector(serviceID, DataSource.class, null);
//	}
}
