/*
 * Copyright 2002-2013 the original author or authors.
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
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.model.CfpSubmission;
import com.devnexus.ting.core.model.PresentationType;
import com.devnexus.ting.core.model.SkillLevel;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@ActiveProfiles({"mail-enabled"})
public class CfpToMailTransformerTests {

	@Autowired
	private CfpToMailTransformer cfpToMailTransformer;

	@Test
	public void transform() throws InterruptedException {

		CfpSubmission cfpSubmission = new CfpSubmission();
		String template = SystemInformationUtils.getCfpHtmlEmailTemplate();
		cfpSubmission.setBio("This is my great **bio**.");
		cfpSubmission.setDescription("My *abstract* rocks!");
		cfpSubmission.setEmail("speaker@devnexus.com");
		cfpSubmission.setFirstName("firstName");
		cfpSubmission.setGooglePlusId("googlePlusId");
		cfpSubmission.setLastName("Cartman");
		cfpSubmission.setLinkedInId("linkedInId");
		cfpSubmission.setPhone("555-555-5555");
		cfpSubmission.setPresentationType(PresentationType.BREAKOUT);
		cfpSubmission.setSessionRecordingApproved(true);
		cfpSubmission.setSkillLevel(SkillLevel.INTERMEDIATE);
		cfpSubmission.setSlotPreference("Second day only");
		cfpSubmission.setTitle("My title");
		cfpSubmission.setTshirtSize("XXXXL");
		cfpSubmission.setTwitterId("twitterId");

		String renderedTemplate = cfpToMailTransformer.applyStringTemplate(cfpSubmission, template);
		System.out.println(renderedTemplate);

	}
}
