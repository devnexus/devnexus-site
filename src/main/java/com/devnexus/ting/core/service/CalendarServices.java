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
package com.devnexus.ting.core.service;

import java.util.List;

import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.ScheduleItem;
import com.devnexus.ting.model.User;
import com.devnexus.ting.model.UserScheduleItem;

/**
 *
 * This provides the custom schedule services.
 *
 * @author summers
 * @author Gunnar Hillert
 */
public interface CalendarServices {

	/**
	 * This method returns a {@link List} of {@link ScheduleItem}s favorited by the provided {@link User}.
	 *
	 * @param user Must Not be Null
	 * @param event Must Not be Null
	 * @return List of {@link ScheduleItem}s favorited by the provided {@link User}
	 */
	List<UserScheduleItem> getUserSchedule(User user, Event event);

	/**
	 * {@link User}s may favorite Sessions from the Schedule (defined by {@link ScheduleItem}s).
	 * This method allows {@link User}s to add those favorited {@link ScheduleItem}s.
	 *
	 * @param user Must Not be Null
	 * @param scheduleItem Must Not be Null
	 * @return Returns the added {@link UserScheduleItem} populated with the id.
	 */
	UserScheduleItem addScheduleItemToUserSchedule(User user, ScheduleItem scheduleItem);

	/**
	 * This method allows {@link User}s to "un-favorite" {@link ScheduleItem}s.
	 *
	 * @param user Must Not be Null
	 * @param scheduleItem Must Not be Null
	 */
	void removeScheduleItemFromUserSchedule(User user, ScheduleItem scheduleItem);
        
        /**
	 * This method replaces a schedule completely.  
	 *
	 * @param user Must Not be Null
	 * @param scheduleItems Must Not be Null
	 */
	void replaceScheduleItemsForUser(User user, List<UserScheduleItem> scheduleItems);

}
