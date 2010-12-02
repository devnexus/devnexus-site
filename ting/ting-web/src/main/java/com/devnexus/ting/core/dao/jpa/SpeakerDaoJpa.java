package com.devnexus.ting.core.dao.jpa;

import java.util.List;

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

	@Override
	public List<Speaker> getSpeakersForCurrentEvent() {
		return super.entityManager
		.createQuery("select s from Speaker s "
				   + "    join s.events e "
				   + "where e.current = :iscurrent "
				   + "order by s.lastName ASC, s.firstName ASC", Speaker.class)
		.setParameter("iscurrent", true)
		.getResultList();
	}

	@Override
	public List<Speaker> getSpeakersForEvent(Long eventId) {
		return super.entityManager
		.createQuery("select s from Speaker s "
				   + "    join s.events e "
				   + "where e.id = :eventId "
				   + "order by s.lastName ASC", Speaker.class)
		.setParameter("eventId", eventId)
		.getResultList();
	}

}
