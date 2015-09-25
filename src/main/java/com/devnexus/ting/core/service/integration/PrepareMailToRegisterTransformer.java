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
package com.devnexus.ting.core.service.integration;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.integration.annotation.Transformer;
import org.springframework.util.StringUtils;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.service.impl.GenericEmail;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.RegistrationDetails;
import com.devnexus.ting.model.TicketOrderDetail;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

/**
 * Converts a {@link CfpSubmission} and converts it to a {@link GenericEmail}.
 *
 * @author Gunnar Hillert
 *
 */
public class PrepareMailToRegisterTransformer extends BaseMailTransformer {

	@Transformer
	public GenericEmail prepareMailToRegister(RegistrationDetails registrationDetails) {

		final String htmlMessage = SystemInformationUtils.getRegisterHtmlEmailTemplate();
		final String textMessage = SystemInformationUtils.getRegisterTextEmailTemplate();

		final String renderedHtmlTemplate = applyMustacheTemplate(registrationDetails, htmlMessage);
		final String renderedTextTemplate = applyMustacheTemplate(registrationDetails, textMessage);

		final GenericEmail email = new GenericEmail();

		email.setText(renderedTextTemplate)
			.setHtml(renderedHtmlTemplate)
			.setFrom(fromUser)
			.addTo(registrationDetails.getContactEmailAddress())
			.setSubject("DevNexus 2016 - Registration Confirmed");

		for (TicketOrderDetail order : registrationDetails.getOrderDetails()) {
			if (!order.getEmailAddress().equals(registrationDetails.getContactEmailAddress())) {
				email.addTo(order.getEmailAddress());
			}
		}

		if (StringUtils.hasText(this.ccUser)) {
			email.setCc(this.ccUser);
		}

		return email;
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
