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
package com.devnexus.ting.core.service.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.devnexus.ting.config.support.MailSettings;
import com.devnexus.ting.core.service.impl.GenericEmail;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

/**
 *
 * @author Gunnar Hillert
 *
 */
public class SendgridSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendgridSender.class);

	final SendGrid sendgrid;

	@Autowired
	public SendgridSender(MailSettings mailSettings) {
		this.sendgrid = new SendGrid(mailSettings.getSendgridApiKey());
	}

	public void sendUsingSendgrid(GenericEmail email) {

		final SendGrid.Email sendgridEmail = new SendGrid.Email();

		for (String to : email.getTo()) {
			sendgridEmail.addTo(to);
		}
		for (String cc : email.getCc()) {
			sendgridEmail.addCc(cc);
		}

		sendgridEmail.setFrom(email.getFrom());
		sendgridEmail.setSubject(email.getSubject());

		sendgridEmail.setText(email.getText());
		sendgridEmail.setHtml(email.getHtml());

		try {
			SendGrid.Response response = sendgrid.send(sendgridEmail);
			LOGGER.info("SendGrid.Response: " + response.getMessage());
		}
		catch (SendGridException e) {
			throw new IllegalStateException(e);
		}

	}
}
