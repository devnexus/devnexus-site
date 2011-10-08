package com.devnexus.ting.core.dao.jpa;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.EventDao;
import com.devnexus.ting.core.model.Event;

@Repository("eventDao")
public class EventDaoJpa extends GenericDaoJpa< Event, Long>
                           implements EventDao {

    /** Constructor. */
    public EventDaoJpa() {
        super(Event.class);
    };

	@Override
	public Event getByEventKey(String eventKey) {
		return super.entityManager.createQuery("select event from Event event where event.eventKey = :eventKey", Event.class)
		                          .setParameter("eventKey", eventKey)
		                          .getSingleResult();
	}

	@Override
	public List<Event> getAllNonCurrentEvents() {


//		System.out.println(this.entityManager.unwrap(Session.class).getSessionFactory().getStatistics());

	return super.entityManager.createQuery("select event from Event event "
			+ "where event.current = :current "
			+ "order by event.title ASC", Event.class)
			                  .setParameter("current", false)
			                  .setHint("org.hibernate.cacheable", true)
			                  .getResultList();
	}

}
