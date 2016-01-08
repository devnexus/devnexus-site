/*
 * Copyright 2015-2016 the original author or authors.
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

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.support.PeriodicTrigger;

import com.devnexus.ting.common.SpringProfile;
import com.devnexus.ting.config.support.AmazonSettings;
import com.devnexus.ting.config.support.MailSettings;
import com.devnexus.ting.core.service.integration.AmazonSesSender;
import com.devnexus.ting.core.service.integration.GenericEmailToMimeMessageTransformer;
import com.devnexus.ting.core.service.integration.PrepareMailToRegisterTransformer;
import com.devnexus.ting.core.service.integration.PrepareMailToSpeakerTransformer;
import com.devnexus.ting.core.service.integration.SendgridSender;


/**
 * Spring Configuration for the Call for Paper handling.
 *
 * @author Gunnar Hillert
 */
@Configuration
@ImportResource("classpath:spring/spring-integration-cfp-context.xml")
public class MailNotificationConfig {

	@Autowired
	private MailSettings mailSettings;

	@Autowired
	private AmazonSettings amazonSettings;

	@Bean
	public QueueChannel mailChannel() {
		return new QueueChannel();
	}

	@Bean
	public QueueChannel registrationMailChannel() {
		return new QueueChannel();
	}

	@Bean
	@Profile({SpringProfile.MAIL_ENABLED})
	public PrepareMailToSpeakerTransformer prepareMailToSpeakerTransformer() {
		PrepareMailToSpeakerTransformer transformer = new PrepareMailToSpeakerTransformer();
		transformer.setCcUser(mailSettings.getUser().getCc());
		transformer.setFromUser(mailSettings.getUser().getFrom());
		return transformer;
	}

	@Bean
	@Profile({SpringProfile.MAIL_ENABLED})
	public PrepareMailToRegisterTransformer prepareMailToRegisterTransformer() {
		PrepareMailToRegisterTransformer transformer = new PrepareMailToRegisterTransformer();
		transformer.setCcUser(mailSettings.getUser().getCc());
		transformer.setFromUser(mailSettings.getUser().getFrom());
		return transformer;
	}

	@Bean
	@Profile(SpringProfile.SMTP_ENABLED)
	public GenericEmailToMimeMessageTransformer genericEmailToMimeMessageTransformer() {
		GenericEmailToMimeMessageTransformer transformer = new GenericEmailToMimeMessageTransformer();
		transformer.setMailSender(mailSender());
		return transformer;
	}

	@Bean
	@Profile(SpringProfile.SENDGRID_ENABLED)
	public SendgridSender sendUsingSendgrid() {
		return new SendgridSender(mailSettings);
	}

	@Bean
	@Profile(SpringProfile.AMAZON_SES_ENABLED)
	public AmazonSesSender sendUsingAmazonSes() {
		return new AmazonSesSender(amazonSettings);
	}

	@Bean
	@Profile(SpringProfile.SMTP_ENABLED)
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(mailSettings.getSmtp().getHost());
		mailSender.setPort(mailSettings.getSmtp().getPort());
		mailSender.setUsername(mailSettings.getUser().getId());
		mailSender.setPassword(mailSettings.getUser().getPassword());

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.debug", String.valueOf(mailSettings.isDebugEnabled()));
		javaMailProperties.put("mail.smtp.auth", String.valueOf(mailSettings.isAuthenticationEnabled()));
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	@Bean
	@Profile({SpringProfile.MAIL_ENABLED})
	public MessageChannel sendMailChannel() {
		return new DirectChannel();
	}

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata defaultPoller() {
		PollerMetadata pollerMetadata = new PollerMetadata();
		pollerMetadata.setTrigger(new PeriodicTrigger(1, TimeUnit.SECONDS));
		return pollerMetadata;
	}
}
