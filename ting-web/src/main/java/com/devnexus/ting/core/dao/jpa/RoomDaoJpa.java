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
package com.devnexus.ting.core.dao.jpa;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.RoomDao;
import com.devnexus.ting.core.model.Room;

@Repository("roomDao")
public class RoomDaoJpa extends GenericDaoJpa< Room, Long>
						implements RoomDao {

	/** Constructor. */
	private RoomDaoJpa() {
		super(Room.class);
	}

	/**
	 * https://hibernate.onjira.com/browse/HHH-6902
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getRoomsForEvent(Long eventId) {

		final Session session = (Session) super.entityManager.getDelegate();
		//Filter filter = session.enableFilter("presentationFilterEventId");
		//filter.setParameter("eventId", eventId);

		final List<Room> rooms = (List<Room>) super.entityManager
				.createQuery("select r from Room r "
						+ "where r.event.id = :eventId "
						+ "order by r.roomOrder ASC")
			.setParameter("eventId", eventId)
			.getResultList();

		return rooms;
	}

}
