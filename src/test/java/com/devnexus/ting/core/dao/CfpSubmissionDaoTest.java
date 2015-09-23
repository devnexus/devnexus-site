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
package com.devnexus.ting.core.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.CfpSubmissionSpeaker;
import com.devnexus.ting.model.CfpSubmissionStatusType;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.PresentationType;
import com.devnexus.ting.model.SkillLevel;
import com.devnexus.ting.repository.CfpSubmissionRepository;
import com.devnexus.ting.repository.EventRepository;

/**
 * @author Gunnar Hillert
 *
 */
@Transactional
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class CfpSubmissionDaoTest extends BaseDaoIntegrationTest {

	@Autowired private CfpSubmissionRepository cfpSubmissionDao;
	@Autowired private EventRepository eventDao;

	@Test
	public void testAllCfpSubmissions() {
		List<CfpSubmission> cfpSubmissions = cfpSubmissionDao.findAll();
		Assert.assertTrue(cfpSubmissions.size() == 0);
	}

	@Test
	public void testGetCfsForEvent() {
		Event event = eventDao.getCurrentEvent();
		List<CfpSubmission> cfpSubmissions = cfpSubmissionDao.getCfpSubmissions(event.getId());
		Assert.assertTrue(cfpSubmissions.size() == 0);
	}

	@Test
	public void testCreateCfpForEvent() {

		final Event event = eventDao.getCurrentEvent();
		final CfpSubmissionSpeaker cfpSubmissionSpeaker = new CfpSubmissionSpeaker();
		final CfpSubmission cfpSubmission = new CfpSubmission();

		cfpSubmissionSpeaker.setBio("myBio");
		cfpSubmissionSpeaker.setEmail("test@test.com");
		cfpSubmissionSpeaker.setFirstName("Kenny");
		cfpSubmissionSpeaker.setGithubId("kenny");
		cfpSubmissionSpeaker.setGooglePlusId("123456789");
		cfpSubmissionSpeaker.setLanyrdId("kenny123");
		cfpSubmissionSpeaker.setLastName("cartman");
		cfpSubmissionSpeaker.setLinkedInId("kc");
		cfpSubmissionSpeaker.setLocation("Southpark");
		cfpSubmissionSpeaker.setMustReimburseTravelCost(true);
		cfpSubmissionSpeaker.setPhone("555-555-5555");
		cfpSubmissionSpeaker.setTshirtSize("L");
		cfpSubmissionSpeaker.setTwitterId("kctwitter");
		cfpSubmissionSpeaker.setCfpSubmission(cfpSubmission);

		cfpSubmission.setEvent(event);
		cfpSubmission.setDescription("myDescription");

		cfpSubmission.setPresentationType(PresentationType.BREAKOUT);
		cfpSubmission.setSessionRecordingApproved(true);
		cfpSubmission.setSkillLevel(SkillLevel.BEGINNER);
		cfpSubmission.setSlotPreference("Morning");
		cfpSubmission.setStatus(CfpSubmissionStatusType.PENDING);
		cfpSubmission.setTitle("my session title");
		cfpSubmission.setTopic("java");
		cfpSubmission.getSpeakers().add(cfpSubmissionSpeaker);
		final CfpSubmission savedCfpSubmission = cfpSubmissionDao.save(cfpSubmission);
		Assert.assertNotNull(savedCfpSubmission);
		Assert.assertNotNull(savedCfpSubmission.getId());
		Assert.assertTrue(savedCfpSubmission.getSpeakers().size() == 1);
	}

	@Test
	public void testCreateCfpWithMultipleSpeakersForEvent() {
		Event event = eventDao.getCurrentEvent();
		final CfpSubmission cfpSubmission = new CfpSubmission();
		cfpSubmission.setEvent(event);

		final CfpSubmissionSpeaker speaker = new CfpSubmissionSpeaker();
		speaker.setBio("myBio");
		speaker.setFirstName("Kenny");
		speaker.setGithubId("kenny");
		speaker.setGooglePlusId("123456789");
		speaker.setLanyrdId("kenny123");
		speaker.setLastName("cartman");
		speaker.setLinkedInId("kc");
		speaker.setEmail("test@test.com");
		speaker.setLocation("Southpark");
		speaker.setMustReimburseTravelCost(true);
		speaker.setPhone("555-555-5555");
		speaker.setTshirtSize("L");
		speaker.setTwitterId("kctwitter");
		speaker.setCfpSubmission(cfpSubmission);

		final CfpSubmissionSpeaker speaker2 = new CfpSubmissionSpeaker();
		speaker2.setBio("myBio");
		speaker2.setFirstName("Kyle");
		speaker2.setGithubId("kyle");
		speaker2.setGooglePlusId("77777777");
		speaker2.setLanyrdId("kyle123");
		speaker2.setLastName("timmy");
		speaker2.setLinkedInId("ky");
		speaker2.setEmail("test@test.com");
		speaker2.setLocation("Southpark");
		speaker2.setMustReimburseTravelCost(true);
		speaker2.setPhone("555-555-5555");
		speaker2.setTshirtSize("L");
		speaker2.setTwitterId("ktwitter");
		speaker2.setCfpSubmission(cfpSubmission);

		cfpSubmission.getSpeakers().add(speaker);
		cfpSubmission.getSpeakers().add(speaker2);

		cfpSubmission.setDescription("myDescription");
		cfpSubmission.setPresentationType(PresentationType.BREAKOUT);
		cfpSubmission.setSessionRecordingApproved(true);
		cfpSubmission.setSkillLevel(SkillLevel.BEGINNER);
		cfpSubmission.setSlotPreference("Morning");
		cfpSubmission.setStatus(CfpSubmissionStatusType.PENDING);
		cfpSubmission.setTitle("my session title");
		cfpSubmission.setTopic("java");

		final CfpSubmission savedCfpSubmission = cfpSubmissionDao.save(cfpSubmission);
		Assert.assertNotNull(savedCfpSubmission);
		Assert.assertNotNull(savedCfpSubmission.getId());
		Assert.assertEquals(Integer.valueOf(2), Integer.valueOf(savedCfpSubmission.getSpeakers().size()));

		for (CfpSubmissionSpeaker submissionSpeaker : savedCfpSubmission.getSpeakers()) {
			Assert.assertNotNull(submissionSpeaker.getId());
		}
	}
}
