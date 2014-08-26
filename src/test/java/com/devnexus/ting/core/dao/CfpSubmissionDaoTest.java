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
package com.devnexus.ting.core.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.devnexus.ting.core.model.CfpSubmission;
import com.devnexus.ting.core.model.CfpSubmissionStatusType;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.PresentationType;
import com.devnexus.ting.core.model.SkillLevel;

/**
 * @author Gunnar Hillert
 *
 */
public class CfpSubmissionDaoTest extends BaseDaoIntegrationTest {

	@Autowired private CfpSubmissionDao cfpSubmissionDao;
	@Autowired private EventDao eventDao;

	@Test
	public void testAllCfpSubmissions() {
		List<CfpSubmission> cfpSubmissions = cfpSubmissionDao.getAll();
		Assert.assertTrue(cfpSubmissions.size() == 0);
	}

	@Test
	public void testGetCpsForEvent() {
		Event event = eventDao.getCurrentEvent();
		List<CfpSubmission> cfpSubmissions = cfpSubmissionDao.getCfpSubmissions(event.getId());
		Assert.assertTrue(cfpSubmissions.size() == 0);
	}

	@Test
	public void testCreateCfpForEvent() {
		Event event = eventDao.getCurrentEvent();
		final CfpSubmission cfpSubmission = new CfpSubmission();
		cfpSubmission.setEvent(event);

		cfpSubmission.setBio("myBio");
		cfpSubmission.setDescription("myDescription");
		cfpSubmission.setEmail("test@test.com");
		cfpSubmission.setFirstName("Kenny");
		cfpSubmission.setGithubId("kenny");
		cfpSubmission.setGooglePlusId("123456789");
		cfpSubmission.setLanyrdId("kenny123");
		cfpSubmission.setLastName("cartman");
		cfpSubmission.setLinkedInId("kc");
		cfpSubmission.setLocation("Southpark");
		cfpSubmission.setMustReimburseTravelCost(true);
		cfpSubmission.setPhone("555-555-5555");
		//cfpSubmission.setPicture(null);
		cfpSubmission.setPresentationType(PresentationType.BREAKOUT);
		cfpSubmission.setSessionRecordingApproved(true);
		cfpSubmission.setSkillLevel(SkillLevel.BEGINNER);
		cfpSubmission.setSlotPreference("Morning");
		cfpSubmission.setStatus(CfpSubmissionStatusType.PENDING);
		cfpSubmission.setTitle("my session title");
		cfpSubmission.setTopic("java");
		cfpSubmission.setTshirtSize("L");
		cfpSubmission.setTwitterId("kctwitter");

		final CfpSubmission savedCfpSubmission = cfpSubmissionDao.save(cfpSubmission);
		Assert.assertNotNull(savedCfpSubmission);
		Assert.assertNotNull(savedCfpSubmission.getId());
	}
}
