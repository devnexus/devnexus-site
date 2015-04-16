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
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.model.Speaker;
import com.devnexus.ting.repository.SpeakerRepository;

/**
 * @author Gunnar Hillert
 *
 */
@Transactional
public class SpeakerDaoTest extends BaseDaoIntegrationTest {

	@Autowired private SpeakerRepository speakerDao;

	/**
	 * Test to verify that the seed data is correctly populated. Typically there
	 * should be 10 industries in the system:
	 *
	 */
	@Test
	public void testGenerateSchema() {
		List<Speaker> speakers = speakerDao.getSpeakersForCurrentEvent();
		System.out.println("Numbers speakers: " + speakers.size());

		for (Speaker speaker : speakers) {
			System.out.println(speaker.getLastName() + ";" + speaker.getPresentations().size());
		}
	}

	@Test
	public void testGetAllSpeakersNoSpeakersExist() {
		List<Speaker> speakers = speakerDao.getAllSpeakersOrderedByName();

		Assert.assertNotNull("List of speakers should not be null.", speakers);
		Assert.assertTrue("List of speakers should be empty.", speakers.isEmpty());

	}
}
