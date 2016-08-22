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

import com.devnexus.ting.DevNexusApplication;
import com.devnexus.ting.common.SpringProfile;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.CfpSubmissionSpeaker;
import com.devnexus.ting.model.PresentationType;
import com.devnexus.ting.model.SkillLevel;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Gunnar Hillert
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevNexusApplication.class)
@ActiveProfiles({SpringProfile.MAIL_ENABLED,SpringProfile.SMTP_ENABLED})
@Ignore
public class MailSendingTest {

	@Autowired
	private MessageChannel mailChannel;

	@Autowired
	private Environment environment;

	@Test
	public void sendCfpEmail() throws InterruptedException {

		Assert.assertTrue(environment.acceptsProfiles(SpringProfile.MAIL_ENABLED));

		final CfpSubmissionSpeaker cfpSubmissionSpeaker = new CfpSubmissionSpeaker();
		final CfpSubmission cfpSubmission = new CfpSubmission();

		cfpSubmissionSpeaker.getCfpSubmissions().add(cfpSubmission);

		cfpSubmissionSpeaker.setBio("This is my great **bio**.");
		cfpSubmissionSpeaker.setEmail("speaker@devnexus.com");
		cfpSubmissionSpeaker.setFirstName("firstName");
		cfpSubmissionSpeaker.setGooglePlusId("googlePlusId");
		cfpSubmissionSpeaker.setLastName("Cartman");
		cfpSubmissionSpeaker.setLinkedInId("linkedInId");
		cfpSubmissionSpeaker.setPhone("555-555-5555");
		cfpSubmissionSpeaker.setTshirtSize("XXXXL");
		cfpSubmissionSpeaker.setTwitterId("twitterId");
		cfpSubmissionSpeaker.setEmail("xaymaca@gmail.com");

		cfpSubmission.getCfpSubmissionSpeakers().add(cfpSubmissionSpeaker);
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
