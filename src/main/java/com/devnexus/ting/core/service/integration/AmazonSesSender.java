/*
 * Copyright 2016 the original author or authors.
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
package com.devnexus.ting.core.service.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.devnexus.ting.config.support.AmazonSettings;
import com.devnexus.ting.core.service.impl.GenericEmail;

/**
 *
 * @author Gunnar Hillert
 *
 */
public class AmazonSesSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(AmazonSesSender.class);

	final AmazonSimpleEmailServiceClient client;

	@Autowired
	public AmazonSesSender(AmazonSettings amazonSettings) {
		final AWSCredentials awsCredentials = new BasicAWSCredentials(amazonSettings.getAwsAccessKeyId(), amazonSettings.getAwsSecretKey());
		client = new AmazonSimpleEmailServiceClient(awsCredentials);
	}

	public void sendUsingSendgrid(GenericEmail email) {

		final Destination destination = new Destination(email.getTo());
		destination.setCcAddresses(email.getCc());

		final Content subject = new Content(email.getSubject());
		final Content htmlContent = new Content().withData(email.getHtml());
		final Content textContent = new Content().withData(email.getText());

		final Body body = new Body().withHtml(htmlContent).withText(textContent);

		final Message message = new Message().withSubject(subject).withBody(body);

		final SendEmailRequest request = new SendEmailRequest()
			.withSource(email.getFrom()).withDestination(destination).withMessage(message);

		final Region REGION = Region.getRegion(Regions.US_EAST_1);
		client.setRegion(REGION);

		// Send the email.
		client.sendEmail(request);
		LOGGER.info("Email sent to {}", email.getTo());

	}
}
