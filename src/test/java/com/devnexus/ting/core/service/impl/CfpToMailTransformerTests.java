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

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.devnexus.ting.common.IntegrationTestApplicationContextInitializer;
import com.devnexus.ting.common.SpringProfile;
import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.CfpSubmissionSpeaker;
import com.devnexus.ting.model.PresentationType;
import com.devnexus.ting.model.SkillLevel;

/**
 * Testing the rendering of Email templates.
 *
 * @author Gunnar Hillert
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = IntegrationTestApplicationContextInitializer.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@ActiveProfiles({SpringProfile.MAIL_ENABLED})
@ImportResource("classpath:spring/spring-integration-cfp-context.xml")
@Ignore
public class CfpToMailTransformerTests {

	@Autowired
	private CfpToMailTransformer cfpToMailTransformer;

	@Test
	public void transformText() throws InterruptedException {
		CfpSubmission cfpSubmission = getCfpSubmission();
		String template = SystemInformationUtils.getCfpTextEmailTemplate();
		String renderedTemplate = cfpToMailTransformer.applyMustacheTemplate(cfpSubmission, template);
		System.out.println(renderedTemplate);
	}

	@Test
	public void transformHtml() throws InterruptedException {
		CfpSubmission cfpSubmission = getCfpSubmission();
		String template = SystemInformationUtils.getCfpHtmlEmailTemplate();
		String renderedTemplate = cfpToMailTransformer.applyMustacheTemplate(cfpSubmission, template);
		System.out.println(renderedTemplate);
	}

	private CfpSubmission getCfpSubmission() {

		final CfpSubmission cfpSubmission = new CfpSubmission();
		cfpSubmission.setPresentationType(PresentationType.BREAKOUT);
		cfpSubmission.setSessionRecordingApproved(true);
		cfpSubmission.setSkillLevel(SkillLevel.INTERMEDIATE);
		cfpSubmission.setTopic("My Topic");
		cfpSubmission.setSlotPreference("Second day only");
		cfpSubmission.setTitle("My title");
		cfpSubmission.setDescription("My *abstract* rocks!");

		final CfpSubmissionSpeaker speaker1 = new CfpSubmissionSpeaker();
		speaker1.setBio("This is my great **bio**.");
		speaker1.setEmail("speaker@devnexus.com");
		speaker1.setFirstName("Speaker1");
		speaker1.setGooglePlusId("googlePlusId");
		speaker1.setLastName("Cartman");
		speaker1.setLinkedInId("linkedInId");
		speaker1.setPhone("555-555-5555");
		speaker1.setTshirtSize("XXXXL");
		speaker1.setTwitterId("twitterId");
		speaker1.setLocation("Kona");

		final CfpSubmissionSpeaker speaker2 = new CfpSubmissionSpeaker();
		speaker2.setBio("This is my great **bio**.");
		speaker2.setEmail("speaker@devnexus.com");
		speaker2.setFirstName("Speaker2");
		speaker2.setGooglePlusId("googlePlusId");
		speaker2.setLanyrdId("lanyrdId");
		speaker2.setLinkedInId("linkedInId");
		speaker2.setGooglePlusId("googlePlusId");
		speaker2.setLastName("Kyle");
		speaker2.setLinkedInId("linkedInId");
		speaker2.setPhone("222-222-2222");
		speaker2.setTshirtSize("XXXXL");
		speaker2.setTwitterId("twitterId");

		speaker2.setLocation("Berlin");
		speaker2.setMustReimburseTravelCost(true);

		cfpSubmission.getSpeakers().add(speaker1);
		cfpSubmission.getSpeakers().add(speaker2);

		return cfpSubmission;
	}

}
