/*
 * Copyright 2014-2016 the original author or authors.
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.core.service.CalendarServices;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.ScheduleItem;
import com.devnexus.ting.model.User;
import com.devnexus.ting.model.UserScheduleItem;
import com.devnexus.ting.repository.ScheduleItemRepository;
import com.devnexus.ting.repository.UserCalendarRepository;

/**
 *
 * @author Summers
 * @author Gunnar Hillert
 *
 */
@Service
public class CalendarServicesImpl implements CalendarServices{

	@Autowired
	UserCalendarRepository calendarRepository;

	@Autowired
	ScheduleItemRepository scheduleItemRepository;

	@Override
	@Transactional
	public List<UserScheduleItem> getUserSchedule(User user, Event event) {
		return calendarRepository.getUserScheduleItems(user, event);
	}

	@Override
	@Transactional
	public UserScheduleItem addScheduleItemToUserSchedule(User user, ScheduleItem scheduleItem) {
		final UserScheduleItem userScheduleItem = new UserScheduleItem();
		userScheduleItem.setScheduleItem(scheduleItem);
		userScheduleItem.setUser(user);
		return calendarRepository.save(userScheduleItem);
	}

	@Override
	@Transactional
	public void removeScheduleItemFromUserSchedule(User user, ScheduleItem scheduleItem) {
		UserScheduleItem userScheduleItem = null;
		for (UserScheduleItem userScheduleItemFromDb : calendarRepository.getUserScheduleItems(user, scheduleItem.getEvent())) {
			if (userScheduleItemFromDb.getScheduleItem().getId().equals(scheduleItem.getId())) {
				userScheduleItem = userScheduleItemFromDb;
			}
		}
		if (userScheduleItem != null) {
			calendarRepository.delete(userScheduleItem.getId());
		}
	}

}
