/*
 * Copyright 2002-2011 the original author or authors.
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

import com.devnexus.ting.model.ScheduleItem;
import com.devnexus.ting.repository.ScheduleItemRepositoryCustom;

/**
 *
 * @author Gunnar Hillert
 * @since  1.0
 *
 */
@Repository("scheduleItemDao")
public class ScheduleItemRepositoryImpl implements ScheduleItemRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	/** {@inheritDoc} */
	@Override
	public List<ScheduleItem> getScheduleForEvent(Long eventId) {
		return this.entityManager.createQuery("select si from ScheduleItem si "
					+ "where si.event.id = :eventId "
					+ "order by si.fromTime ASC, si.room.roomOrder ASC", ScheduleItem.class)
					.setParameter("eventId", eventId)
					.getResultList();
	}

}
