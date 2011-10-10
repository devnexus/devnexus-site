package com.devnexus.ting.core.service;

import java.util.List;

import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.Organizer;
import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.Speaker;

/**
 * Provides user related methods.
 *
 * @author Gunnar Hillert
 *
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public interface BusinessService {

	/**
	 *
	 * @return
	 */
	public List<Speaker> getAllSpeakers();

	/**
	 *
	 * @return
	 */
	public List<Presentation> getAllPresentations();

	public void saveEvent(Event event);

	public List<Event> getAllEvents();

	public Speaker saveSpeaker(Speaker speaker);

	public void savePresentation(Presentation presentation);

	public Event getEvent(Long id);
	public Speaker getSpeaker(Long id);
	public Presentation getPresentation(Long id);

	public void deleteEvent(Event event);
	public void deleteSpeaker(Speaker speaker);
	public void deletePresentation(Presentation presentation);

	public List<Speaker> getSpeakersForCurrentEvent();
	public List<Presentation> getPresentationsForCurrentEvent();

	public List<Speaker> getSpeakersForEvent(Long eventId);
	public List<Presentation> getPresentationsForEvent(Long eventId);

	public Event getEventByEventKey(String eventKey);

	public List<Event> getAllNonCurrentEvents();

	public List<Organizer> getAllOrganizers();

	Organizer getOrganizer(Long organizerId);

}
