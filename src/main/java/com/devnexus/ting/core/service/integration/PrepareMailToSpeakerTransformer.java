/*
 * Copyright 2002-2016 the original author or authors.
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

import javax.mail.internet.MimeMessage;

import org.springframework.integration.annotation.Transformer;
import org.springframework.util.StringUtils;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.service.impl.GenericEmail;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.CfpSubmissionSpeaker;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

/**
 * Converts a {@link CfpSubmission} and converts it to a {@link MimeMessage}.
 *
 * @author Gunnar Hillert
 *
 */
public class PrepareMailToSpeakerTransformer extends BaseMailTransformer {

	@Transformer
	public GenericEmail prepareMailToSpeaker(CfpSubmission cfpSubmission) {

		String templateHtml = SystemInformationUtils.getCfpHtmlEmailTemplate();
		String templateText = SystemInformationUtils.getCfpTextEmailTemplate();

		String renderedHtmlTemplate = applyMustacheTemplate(cfpSubmission, templateHtml);
		String renderedTextTemplate = applyMustacheTemplate(cfpSubmission, templateText);

		final GenericEmail email = new GenericEmail();

		email.setText(renderedTextTemplate)
			.setHtml(renderedHtmlTemplate)
			.setFrom(fromUser)
			.setSubject("DevNexus 2017 - CFP - " + cfpSubmission.getSpeakersAsString(false));

		for (CfpSubmissionSpeaker submissionSpeaker : cfpSubmission.getCfpSubmissionSpeakers()) {
			email.addTo(submissionSpeaker.getEmail());
		}

		if (StringUtils.hasText(this.ccUser)) {
			email.setCc(this.ccUser);
		}

		return email;
	}

	public String applyMustacheTemplate(CfpSubmission cfpSubmission, String template) {
		Map<String, Object> context = new HashMap<String, Object>();

		context.put("salutation", cfpSubmission.getSpeakersAsString(true));
		context.put("description", cfpSubmission.getDescription());
		context.put("presenationType", cfpSubmission.getPresentationType().getName());
		context.put("skillLevel", cfpSubmission.getSkillLevel().getName());
		context.put("comments", cfpSubmission.getSlotPreference());
		context.put("topic", cfpSubmission.getTopic());
		context.put("title", cfpSubmission.getTitle());
		context.put("sessionRecordingApproved", cfpSubmission.isSessionRecordingApproved() ? "Yes" : "No");
		context.put("speakers", cfpSubmission.getCfpSubmissionSpeakers());

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
