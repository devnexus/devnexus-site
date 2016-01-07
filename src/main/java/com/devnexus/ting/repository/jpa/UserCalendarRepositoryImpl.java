/*
 * Copyright 2002-2016 the original author or authors.
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
package com.devnexus.ting.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.User;
import com.devnexus.ting.model.UserScheduleItem;
import com.devnexus.ting.repository.UserCalendarRepositoryCustom;

/**
 * @author Summers Pittman
 * @author Gunnar Hillert
 */
@Repository("userCalendarDao")
public class UserCalendarRepositoryImpl implements UserCalendarRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<UserScheduleItem> getUserScheduleItems(User user, Event event) {
		return this.entityManager
			.createQuery("select us from UserScheduleItem us "
					+ "left join fetch us.scheduleItem si "
					+ "where si.event.id = :eventId and us.user.id = :userId order by si.fromTime", UserScheduleItem.class)
			.setParameter("userId", user.getId())
			.setParameter("eventId", event.getId())
			.getResultList();
	}

}
