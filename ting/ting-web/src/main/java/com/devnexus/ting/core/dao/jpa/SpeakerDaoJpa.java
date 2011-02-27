package com.devnexus.ting.core.dao.jpa;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.SpeakerDao;
import com.devnexus.ting.core.model.Speaker;

@Repository("speakerDao")
public class SpeakerDaoJpa extends GenericDaoJpa< Speaker, Long>
                           implements SpeakerDao {

    /** Constructor. */
    private SpeakerDaoJpa() {
        super(Speaker.class);
    }

	@Override
	public List<Speaker> getAllSpeakersOrderedByName() {
		return super.entityManager
		.createQuery("select s from Speaker s "
				   + "order by s.lastName ASC, s.firstName ASC", Speaker.class)
		.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Speaker> getSpeakersForCurrentEvent() {

		final Session session = (Session) super.entityManager.getDelegate();

		Filter filter = session.enableFilter("presentationFilter");

		final List<Speaker> speakers = (List<Speaker>) session.createQuery("select s from Speaker s "
				   + "    join fetch s.events e "
				   + "    where e.current = :iscurrent "
				   + "    order by s.lastName ASC, s.firstName ASC")
		.setParameter("iscurrent", Boolean.TRUE)
		.setCacheable(true)
		.list();
		//session.disableFilter("presentationFilter");
		return speakers;
	}

	@Override
	public List<Speaker> getSpeakersForEvent(Long eventId) {

		final Session session = (Session) super.entityManager.getDelegate();
		Filter filter = session.enableFilter("presentationFilterEventId");
		filter.getFilterDefinition().getParameterNames();
		filter.setParameter("eventId", eventId);

		return super.entityManager
		.createQuery("select s from Speaker s "
				   + "    join s.events e "
				   + "where e.id = :eventId "
				   + "order by s.lastName ASC", Speaker.class)
		.setParameter("eventId", eventId)
		.getResultList();
	}

}
