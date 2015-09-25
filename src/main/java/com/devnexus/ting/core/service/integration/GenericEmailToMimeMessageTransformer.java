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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.integration.annotation.Transformer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.devnexus.ting.core.service.impl.GenericEmail;

/**
 * Converts a {@link GenericEmail} and converts it to a {@link MimeMessage}.
 *
 * @author Gunnar Hillert
 *
 */
public class GenericEmailToMimeMessageTransformer {

	protected JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Transformer
	public MimeMessage prepareMailToSpeaker(GenericEmail email) {

		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setText(email.getText(), email.getHtml());
			messageHelper.setFrom(email.getFrom());

			for (String emailToAddress : email.getTo()) {
				messageHelper.addTo(emailToAddress);
			}

			if (!email.getCc().isEmpty()) {
				for (String emailCcAddress : email.getCc()) {
					messageHelper.addCc(emailCcAddress);
				}
			}

			messageHelper.setSubject(email.getSubject());

		} catch (MessagingException e) {
			throw new IllegalStateException("Error creating mail message for email: " + email, e);
		}

		return messageHelper.getMimeMessage();
	}

}
