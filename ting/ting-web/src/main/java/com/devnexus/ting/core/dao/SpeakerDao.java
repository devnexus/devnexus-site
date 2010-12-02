package com.devnexus.ting.core.dao;

import java.util.List;

import com.devnexus.ting.core.model.Speaker;

public interface SpeakerDao  extends GenericDao < Speaker, Long > {

	List<Speaker> getSpeakersForCurrentEvent();

	List<Speaker> getSpeakersForEvent(Long eventId);

	List<Speaker> getAllSpeakersOrderedByName();

}
