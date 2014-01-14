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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.PresentationDao;
import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.support.PresentationSearchQuery;

@Repository("presentationDao")
public class PresentationDaoJpa extends GenericDaoJpa< Presentation, Long>
						   implements PresentationDao {

	/** Constructor. */
	public PresentationDaoJpa() {
		super(Presentation.class);
	}

	@Override
	public List<Presentation> getPresentationsForCurrentEvent() {
		return super.entityManager
			.createQuery("select p from Presentation p "
					   + "left join p.event e "
					   + "where e.current = :iscurrent "
					   + "order by p.title ASC", Presentation.class)
			.setParameter("iscurrent", true)
			.getResultList();
	}

	@Override
	public List<Presentation> getPresentationsForEventOrderedByName(Long eventId) {
		return super.entityManager
		.createQuery("select p from Presentation p "
				   + "    join p.event e "
				   + "where e.id = :eventId "
				   + "order by p.title ASC", Presentation.class)
		.setParameter("eventId", eventId)
		.getResultList();
	}

	@Override
	public List<Presentation> getPresentationsForEventOrderedByRoom(Long eventId) {
		return super.entityManager
		.createQuery("select p from Presentation p "
				   + "    join p.event e "
				   + "    right join p.scheduleItem si "
				   + "    right join si.room r "
				   + "where e.id = :eventId "
				   + "order by r.roomOrder ASC", Presentation.class)
		.setParameter("eventId", eventId)
		.getResultList();
	}

	@Override
	public List<Presentation> getPresentationsForEventOrderedByTrack(Long eventId) {
		return super.entityManager
		.createQuery("select p from Presentation p "
				   + "    join p.event e "
				   + "    left join p.track t "
				   + "where e.id = :eventId "
				   + "order by t.trackOrder ASC", Presentation.class)
		.setParameter("eventId", eventId)
		.getResultList();
	}

	@Override
	public List<Presentation> findPresentations(
			PresentationSearchQuery presentationSearchQuery) {

		Session session = (Session) entityManager.getDelegate();

		final Criteria rootCriteria = session.createCriteria(Presentation.class);
		final Criteria eventCriteria = rootCriteria.createCriteria("event");

		if (presentationSearchQuery.getEvent() != null && presentationSearchQuery.getEvent().getEventKey() != null) {
			eventCriteria.add(Restrictions.eq("eventKey", presentationSearchQuery.getEvent().getEventKey()).ignoreCase());
		}

		if (presentationSearchQuery.getTrack() != null) {
			if (presentationSearchQuery.getTrack().getId() != null) {
				rootCriteria.createAlias("track", "t", JoinType.INNER_JOIN, Restrictions.eq("t.id", presentationSearchQuery.getTrack().getId()));
			}
			else if (presentationSearchQuery.getTrack().getName() != null) {
				rootCriteria.createAlias("track", "t", JoinType.INNER_JOIN, Restrictions.eq("t.name", presentationSearchQuery.getTrack().getName()).ignoreCase());
			}
		}
		else {
			rootCriteria.createAlias("track", "t", JoinType.RIGHT_OUTER_JOIN);
		}

		if (!presentationSearchQuery.getPresentationTags().isEmpty()) {
			final Criteria tagsCriteria = rootCriteria.createCriteria("presentationTags");
			tagsCriteria.add(Restrictions.in("name", presentationSearchQuery.getPresentationTagNames()));
		}

		return rootCriteria.list();
	}

}
