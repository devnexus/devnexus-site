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
package com.devnexus.ting.config;

import java.util.Map;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.devnexus.ting.common.SpringProfile;


/**
 * @author Gunnar Hillert
 *
 */
@Configuration
@ComponentScan({"com.devnexus.ting.core.service"})
@Import(PersistenceConfig.class)
@ImportResource({
	"classpath:spring/spring-integration-cfp-context.xml"
})
@EnableTransactionManagement
public class ServicesConfig {

	@Bean
	public StandardStringDigester stringDigester() {
		StandardStringDigester standardStringDigester = new StandardStringDigester();
		standardStringDigester.setAlgorithm("SHA-512");
		standardStringDigester.setIterations(100000);
		standardStringDigester.setSaltSizeBytes(16);
		return standardStringDigester;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		passwordEncoder.setStringDigester(stringDigester());
		return passwordEncoder;
	}

	@Bean
	@Profile(SpringProfile.WEBSOCKET_DISABLED)
	SimpMessageSendingOperations simpMessageSendingOperations() {
		return new SimpMessageSendingOperations() {

			@Override
			public void send(String destination, Message<?> message)
					throws MessagingException {
				// TODO Auto-generated method stub

			}

			@Override
			public void send(Message<?> message) throws MessagingException {
				// TODO Auto-generated method stub

			}

			@Override
			public void convertAndSend(String destination, Object payload,
					Map<String, Object> headers, MessagePostProcessor postProcessor)
					throws MessagingException {
				// TODO Auto-generated method stub

			}

			@Override
			public void convertAndSend(String destination, Object payload,
					MessagePostProcessor postProcessor) throws MessagingException {
				// TODO Auto-generated method stub

			}

			@Override
			public void convertAndSend(String destination, Object payload,
					Map<String, Object> headers) throws MessagingException {
				// TODO Auto-generated method stub

			}

			@Override
			public void convertAndSend(Object payload,
					MessagePostProcessor postProcessor) throws MessagingException {
				// TODO Auto-generated method stub

			}

			@Override
			public void convertAndSend(String destination, Object payload)
					throws MessagingException {
				// TODO Auto-generated method stub

			}

			@Override
			public void convertAndSend(Object payload) throws MessagingException {
				// TODO Auto-generated method stub

			}

			@Override
			public void convertAndSendToUser(String user, String destination,
					Object payload, Map<String, Object> headers,
					MessagePostProcessor postProcessor) throws MessagingException {
				// TODO Auto-generated method stub

			}

			@Override
			public void convertAndSendToUser(String user, String destination,
					Object payload, MessagePostProcessor postProcessor)
					throws MessagingException {
				// TODO Auto-generated method stub

			}

			@Override
			public void convertAndSendToUser(String user, String destination,
					Object payload, Map<String, Object> headers)
					throws MessagingException {
				// TODO Auto-generated method stub

			}

			@Override
			public void convertAndSendToUser(String user, String destination,
					Object payload) throws MessagingException {
				// TODO Auto-generated method stub

			}
		};
	}
}
