package com.devnexus.ting.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.core.dao.EventDao;
import com.devnexus.ting.core.dao.OrganizerDao;
import com.devnexus.ting.core.dao.PresentationDao;
import com.devnexus.ting.core.dao.SpeakerDao;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.Organizer;
import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.Speaker;
import com.devnexus.ting.core.service.BusinessService;

/**
 *
 * @author Gunnar Hillert
 *
 * @version $Id: UserServiceImpl.java 564 2010-06-08 04:36:23Z ghillert $
 */
@Service("businessService")
@Transactional
public class BusinessServiceImpl implements BusinessService {

	@Autowired private PresentationDao presentationDao;
	@Autowired private SpeakerDao      speakerDao;
	@Autowired private EventDao        eventDao;
	@Autowired private OrganizerDao    organizerDao;

    /**
     *   Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(BusinessServiceImpl.class);

	@Override
	public List<Presentation> getAllPresentations() {
		return presentationDao.getAll();
	}

	@Override
	public List<Speaker> getAllSpeakers() {
		return speakerDao.getAllSpeakersOrderedByName();
	}

	@Override
	public void saveEvent(Event event) {
		eventDao.save(event);
	}

	@Override
	public List<Event> getAllEvents() {
		return eventDao.getAll();
	}

	@Override
	public void savePresentation(Presentation presentation) {
		presentationDao.save(presentation);
	}

	@Override
	public Speaker saveSpeaker(Speaker speaker) {
		return speakerDao.save(speaker);
	}

	@Override
	public Speaker getSpeaker(Long speakerId) {
		return speakerDao.get(speakerId);
	}

	@Override
	public void deleteEvent(Event event) {
		eventDao.remove(event);
	}

	@Override
	public Event getEvent(Long id) {
		return eventDao.get(id);
	}

	@Override
	public void deletePresentation(Presentation presentation) {
		presentationDao.remove(presentation);
	}

	@Override
	public void deleteSpeaker(Speaker speaker) {
		speakerDao.remove(speaker);
	}

	@Override
	public Presentation getPresentation(Long id) {
		return presentationDao.get(id);
	}

	@Override
	public List<Presentation> getPresentationsForCurrentEvent() {
		return presentationDao.getPresentationsForCurrentEvent();
	}

	@Override
	public List<Presentation> getPresentationsForEvent(Long eventId) {
		return presentationDao.getPresentationsForEvent(eventId);
	}

	@Override
	public List<Speaker> getSpeakersForCurrentEvent() {
		return speakerDao.getSpeakersForCurrentEvent();
	}

	@Override
	public List<Speaker> getSpeakersForEvent(Long eventId) {
		return speakerDao.getSpeakersForEvent(eventId);
	}

	@Override
	public Event getEventByEventKey(String eventKey) {
		return eventDao.getByEventKey(eventKey);
	}

	@Override
	public List<Event> getAllNonCurrentEvents() {
		return eventDao.getAllNonCurrentEvents();
	}

	@Override
	public List<Organizer> getAllOrganizers() {
		return organizerDao.getAllOrganizers();
	}

	@Override
	public Organizer getOrganizer(final Long organizerId) {
		return organizerDao.get(organizerId);
	}

}
