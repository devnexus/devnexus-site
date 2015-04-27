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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.devnexus.ting.common.IntegrationTestApplicationContextInitializer;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.CfpSubmissionSpeaker;
import com.devnexus.ting.model.PresentationType;
import com.devnexus.ting.model.SkillLevel;
import com.devnexus.ting.web.config.ServicesConfig;

/**
 *
 * @author Gunnar Hillert
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		initializers=IntegrationTestApplicationContextInitializer.class,
				classes=ServicesConfig.class)
public class MailSendingTest {

	@Autowired
	private MessageChannel mailChannel;

	@Test
	public void sendCfpEmail() throws InterruptedException {

		final CfpSubmissionSpeaker cfpSubmissionSpeaker = new CfpSubmissionSpeaker();
		final CfpSubmission cfpSubmission = new CfpSubmission();

		cfpSubmissionSpeaker.setCfpSubmission(cfpSubmission);

		cfpSubmissionSpeaker.setBio("This is my great **bio**.");
		cfpSubmissionSpeaker.setEmail("speaker@devnexus.com");
		cfpSubmissionSpeaker.setFirstName("firstName");
		cfpSubmissionSpeaker.setGooglePlusId("googlePlusId");
		cfpSubmissionSpeaker.setLastName("Cartman");
		cfpSubmissionSpeaker.setLinkedInId("linkedInId");
		cfpSubmissionSpeaker.setPhone("555-555-5555");
		cfpSubmissionSpeaker.setTshirtSize("XXXXL");
		cfpSubmissionSpeaker.setTwitterId("twitterId");
		cfpSubmissionSpeaker.setEmail("test@hillert.com");

		cfpSubmission.getSpeakers().add(cfpSubmissionSpeaker);
		cfpSubmission.setDescription("My *abstract* rocks!");
		cfpSubmission.setPresentationType(PresentationType.BREAKOUT);
		cfpSubmission.setSessionRecordingApproved(true);
		cfpSubmission.setSkillLevel(SkillLevel.INTERMEDIATE);
		cfpSubmission.setSlotPreference("Second day only");
		cfpSubmission.setTitle("My title");

		mailChannel.send(MessageBuilder.withPayload(cfpSubmission).build());

		Thread.sleep(2000);
	}
}
