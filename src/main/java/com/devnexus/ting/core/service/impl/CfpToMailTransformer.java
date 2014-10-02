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
package com.devnexus.ting.core.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.model.CfpSubmission;
import com.devnexus.ting.core.model.CfpSubmissionSpeaker;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

/**
 * Converts a {@link CfpSubmission} and converts it to a {@link MimeMessage}.
 *
 * @author Gunnar Hillert
 *
 */
public class CfpToMailTransformer {

	private JavaMailSender mailSender;
	private String fromUser;
	private String ccUser;

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public void setCcUser(String ccUser) {
		this.ccUser = ccUser;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public MimeMessage prepareMailToSpeaker(CfpSubmission cfpSubmission) {

		String templateHtml = SystemInformationUtils.getCfpHtmlEmailTemplate();
		String templateText = SystemInformationUtils.getCfpTextEmailTemplate();

		String renderedHtmlTemplate = applyMustacheTemplate(cfpSubmission, templateHtml);
		String renderedTextTemplate = applyMustacheTemplate(cfpSubmission, templateText);

		MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setText(renderedTextTemplate, renderedHtmlTemplate);

			messageHelper.setFrom(fromUser);

			for (CfpSubmissionSpeaker submissionSpeaker : cfpSubmission.getSpeakers()) {
				messageHelper.addTo(submissionSpeaker.getEmail());
			}

			if (StringUtils.hasText(this.ccUser)) {
				messageHelper.setCc(this.ccUser);
			}

			messageHelper.setSubject("DevNexus 2015 - CFP - " + cfpSubmission.getSpeakersAsString(false));

		} catch (MessagingException e) {
			throw new IllegalStateException("Error creating mail message for CFP: " + cfpSubmission, e);
		}

		return messageHelper.getMimeMessage();
	}

	public String applyMustacheTemplate(CfpSubmission cfpSubmission, String template) {
		Map<String, Object> context = new HashMap<String, Object>();

		context.put("salutation", cfpSubmission.getSpeakersAsString(true));
		context.put("description", cfpSubmission.getDescription());
		context.put("presentationType", cfpSubmission.getPresentationType().getName());
		context.put("skillLevel", cfpSubmission.getSkillLevel().getName());
		context.put("comments", cfpSubmission.getSlotPreference());
		context.put("topic", cfpSubmission.getTopic());
		context.put("title", cfpSubmission.getTitle());
		context.put("sessionRecordingApproved", cfpSubmission.isSessionRecordingApproved() ? "Yes" : "No");
		context.put("speakers", cfpSubmission.getSpeakers());

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
