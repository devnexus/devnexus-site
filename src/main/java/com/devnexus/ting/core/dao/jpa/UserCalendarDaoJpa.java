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
package com.devnexus.ting.core.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.UserCalendarDao;
import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.model.UserCalendar;

/**
 * @author Summers Pittman
 */
@Repository("userCalendarDao")
public class UserCalendarDaoJpa extends GenericDaoJpa< UserCalendar, Long> implements UserCalendarDao {

	public UserCalendarDaoJpa() {
		super(UserCalendar.class);
	}

	@Override
	public List<UserCalendar> getUserCalendar(User user, String eventKey) {
		return super.entityManager.createQuery("from UserCalendar where event_key = :eventKey and username = :username order by fromTime", UserCalendar.class).setParameter("username", user.getUsername()).setParameter("eventKey", eventKey).getResultList();
	}

	@Override
	public List<UserCalendar> getTemplateCalendar(String eventKey) {
		return super.entityManager.createQuery("from UserCalendar where event_key = :eventKey and template = true order by fromTime", UserCalendar.class).setParameter("eventKey", eventKey).getResultList();
	}

}
