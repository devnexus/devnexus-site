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

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import org.stringtemplate.v4.ST;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.model.CfpSubmission;
import com.devnexus.ting.core.model.CfpSubmissionSpeaker;

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

		String renderedHtmlTemplate = applyStringTemplate(cfpSubmission, templateHtml);
		String renderedTextTemplate = applyStringTemplate(cfpSubmission, templateText);

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
			List<String> speakers = new ArrayList<String>();
			for (CfpSubmissionSpeaker speaker : cfpSubmission.getSpeakers()) {
				speakers.add(speaker.getFirstLastName());
			}
			messageHelper.setSubject("DevNexus 2015 - CFP - " + StringUtils.collectionToCommaDelimitedString(speakers));

		} catch (MessagingException e) {
			throw new IllegalStateException("Error creating mail message for CFP: " + cfpSubmission, e);
		}

		return messageHelper.getMimeMessage();
	}

	public String applyStringTemplate(CfpSubmission cfpSubmission, String template) {
		ST stringTemplate = new ST(template, '~', '~');

		stringTemplate.add("description", cfpSubmission.getDescription());

		stringTemplate.add("presentationType", cfpSubmission.getPresentationType().getName());
		stringTemplate.add("skillLevel", cfpSubmission.getSkillLevel().getName());
		stringTemplate.add("comments", cfpSubmission.getSlotPreference());
		stringTemplate.add("topic", cfpSubmission.getTopic());
		stringTemplate.add("title", cfpSubmission.getTitle());
		stringTemplate.add("sessionRecordingApproved", cfpSubmission.isSessionRecordingApproved() ? "Yes" : "No");

		//TODO
		stringTemplate.add("firstName", cfpSubmission.getFirstName());
		stringTemplate.add("lastName", cfpSubmission.getLastName());
		stringTemplate.add("bio", cfpSubmission.getBio());
		stringTemplate.add("tshirtSize", cfpSubmission.getTshirtSize());
		stringTemplate.add("location", cfpSubmission.getLocation());
		stringTemplate.add("mustReimburseTravelCost", cfpSubmission.isMustReimburseTravelCost());
		stringTemplate.add("email", cfpSubmission.getEmail());
		stringTemplate.add("googlePlusId", cfpSubmission.getGooglePlusId());
		stringTemplate.add("linkedInId", cfpSubmission.getLinkedInId());
		stringTemplate.add("twitterId", cfpSubmission.getTwitterId());
		stringTemplate.add("phone", cfpSubmission.getPhone());

		String renderedTemplate = stringTemplate.render();

		return renderedTemplate;
	}
}
