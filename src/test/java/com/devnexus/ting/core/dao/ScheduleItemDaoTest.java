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

import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.ScheduleItem;
import com.devnexus.ting.repository.EventRepository;
import com.devnexus.ting.repository.RoomRepository;
import com.devnexus.ting.repository.ScheduleItemRepository;
import com.devnexus.ting.repository.SpeakerRepository;

/**
 * @author Gunnar Hillert
 *
 */
public class ScheduleItemDaoTest extends BaseDaoIntegrationTest {

	@Autowired private SpeakerRepository speakerDao;
	@Autowired private ScheduleItemRepository scheduleItemDao;
	@Autowired private EventRepository eventDao;
	@Autowired private RoomRepository roomDao;

	/**
	 */
	@Test
	@Ignore
	public void testSetupSchedule() {

		final Event event = eventDao.getByEventKey("devnexus2013");
		Assert.assertNotNull(event);

		final List<ScheduleItem> schedule = scheduleItemDao.getScheduleForEvent(event.getId());

		Assert.assertNotNull(schedule);
		Assert.assertTrue(schedule.size() > 0);

	}

}
