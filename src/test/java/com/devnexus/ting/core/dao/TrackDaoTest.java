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
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.model.Track;
import com.devnexus.ting.repository.TrackRepository;

/**
 * @author Gunnar Hillert
 *
 */
@Transactional
@Ignore
public class TrackDaoTest extends BaseDaoIntegrationTest {

	@Autowired private TrackRepository trackDao;

	@Test
	public void testGetAllTracks() {

		final List<Track> tracks = trackDao.findAll();

		Assert.assertNotNull("List of tracks should not be null.", tracks);
		Assert.assertEquals("There should be 3 tracks.", Integer.valueOf(3), Integer.valueOf(tracks.size()));
	}

	@Test
	public void testGetTracksForEvent() {

		final List<Track> tracks = trackDao.getTracksForEvent(1L);

		Assert.assertNotNull("List of tracks should not be null.", tracks);
		Assert.assertEquals("There should be 3 tracks.", Integer.valueOf(3), Integer.valueOf(tracks.size()));
	}

	@Test
	public void testGetTracksForNonExistingEvent() {

		final List<Track> tracks = trackDao.getTracksForEvent(989898L);

		Assert.assertTrue("List of tracks should be empty.", tracks.isEmpty());

	}

}
