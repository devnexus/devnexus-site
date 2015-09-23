/*
 * Copyright 2002-2015 the original author or authors.
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
package com.devnexus.ting.core.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.integration.annotation.Transformer;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.RegistrationDetails;
import com.devnexus.ting.model.TicketOrderDetail;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

/**
 * Converts a {@link CfpSubmission} and converts it to a {@link MimeMessage}.
 *
 * @author Gunnar Hillert
 *
 */
public class PrepareMailToRegisterTransformer extends BaseMailTransformer {

	@Transformer
	public MimeMessage prepareMailToRegister(RegistrationDetails registrationDetails) {

		String htmlMessage = SystemInformationUtils.getRegisterHtmlEmailTemplate();
		String textMessage = SystemInformationUtils.getRegisterTextEmailTemplate();


		String renderedHtmlTemplate = applyMustacheTemplate(registrationDetails, htmlMessage);
		String renderedTextTemplate = applyMustacheTemplate(registrationDetails, textMessage);

		MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;

		try {
			messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setText(renderedTextTemplate, renderedHtmlTemplate);

			messageHelper.setFrom(fromUser);
						messageHelper.addTo(registrationDetails.getContactEmailAddress());

						for (TicketOrderDetail order : registrationDetails.getOrderDetails()) {
							if (!order.getEmailAddress().equals(registrationDetails.getContactEmailAddress())) {
								messageHelper.addTo(order.getEmailAddress());
							}
						}

			if (StringUtils.hasText(this.ccUser)) {
				messageHelper.setCc(this.ccUser);
			}

			messageHelper.setSubject("DevNexus 2016 - Registration Confirmed");

		} catch (MessagingException e) {
			throw new IllegalStateException("Error creating mail message for Registration: " + registrationDetails, e);
		}

		return messageHelper.getMimeMessage();
	}

	public String applyMustacheTemplate(RegistrationDetails registrationDetails, String template) {
		Map<String, Object> context = new HashMap<String, Object>();

		context.put("orderId", registrationDetails.getRegistrationFormKey());
		context.put("orderDetails", registrationDetails.getOrderDetails());
		context.put("finalPrice", registrationDetails.getFinalCost().setScale(2).toString());

		Writer writer = new StringWriter();
		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache mustache = mf.compile(new StringReader(template), "email-notification");

		try {
			mustache.execute(writer, context).flush();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return writer.toString();
	}

}
