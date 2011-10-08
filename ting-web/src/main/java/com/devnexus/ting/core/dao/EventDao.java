package com.devnexus.ting.core.dao;

import java.util.List;

import com.devnexus.ting.core.model.Event;

public interface EventDao  extends GenericDao < Event, Long > {

	Event getByEventKey(String eventKey);

	List<Event> getAllNonCurrentEvents();

}
