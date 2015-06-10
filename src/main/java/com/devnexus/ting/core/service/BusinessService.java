/*
 * Copyright 2002-2014 the original author or authors.
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
package com.devnexus.ting.core.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.devnexus.ting.model.ApplicationCache;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.Evaluation;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.EventSignup;
import com.devnexus.ting.model.FileData;
import com.devnexus.ting.model.Organizer;
import com.devnexus.ting.model.Presentation;
import com.devnexus.ting.model.PresentationTag;
import com.devnexus.ting.model.Room;
import com.devnexus.ting.model.ScheduleItemList;
import com.devnexus.ting.model.Speaker;
import com.devnexus.ting.model.Sponsor;
import com.devnexus.ting.model.SponsorList;
import com.devnexus.ting.model.Track;
import com.devnexus.ting.model.support.PresentationSearchQuery;

/**
 * The central service layer of Ting.
 *
 * @author Gunnar Hillert
 * @since 1.0
 *
 */
public interface BusinessService {

	Evaluation saveEvaluation(Evaluation evaluation);

	/**
	 * Delete the provided {@link Event}. The Id of the {@link Event} must be set.
	 * @param event The {@link Event} to delete. Must not be null.
	 */
	void deleteEvent(Event event);

	/**
	 * Delete the provided {@link Organizer}. The Id of the {@link Organizer} must be set.
	 * @param organizerFromDb The {@link Organizer} to delete. Must not be null.
	 */
	void deleteOrganizer(Organizer organizerFromDb);

	/**
	 * Delete the provided {@link Presentation}. The Id of the {@link Presentation} must be set.
	 * @param presentation The {@link Presentation} to delete. Must not be null.
	 */
	void deletePresentation(Presentation presentation);

	/**
	 * Delete the provided {@link Speaker}. The Id of the {@link Speaker} must be set.
	 * @param presentation The {@link Speaker} to delete. Must not be null.
	 */
	void deleteSpeaker(Speaker speaker);

	/**
	 *
	 * @return
	 */
	List<Event> getAllEventsOrderedByName();

	/**
	 *
	 * @return
	 */
	List<Event> getAllNonCurrentEvents();

	/**
	 *
	 * @return
	 */
	List<Organizer> getAllOrganizers();

	/**
	 *
	 * @return
	 */
	List<Presentation> getAllPresentations();
	/**
	 * Returns a list of all Speakers ordered by name.
	 *
	 * @return List of Speakers. Never returns null.
	 */
	List<Speaker> getAllSpeakersOrderedByName();

	/**
	 *
	 * @param id
	 * @return
	 */
	Event getEvent(Long id);

	/**
	 *
	 * @param eventKey
	 * @return
	 */
	Event getEventByEventKey(String eventKey);

	/**
	 *
	 * @param organizerId
	 * @return
	 */
	Organizer getOrganizer(Long organizerId);

	/**
	 *
	 * @param id
	 * @return
	 */
	Presentation getPresentation(Long id);

	/**
	 *
	 * @return
	 */
	List<Presentation> getPresentationsForCurrentEvent();

	List<Room> getRoomsForEvent(Long eventId);

	/**
	 *
	 * @param id
	 * @return
	 */
	Speaker getSpeaker(Long id);

	/**
	 *
	 * @param id
	 * @return
	 */
	byte[] getSpeakerImage(Long id);

	/**
	 *
	 * @return
	 */
	List<Speaker> getSpeakersForCurrentEvent();

	/**
	 *
	 * @param eventId
	 * @return
	 */
	List<Speaker> getSpeakersForEvent(Long eventId);

	/**
	 *
	 * @param event
	 */
	void saveEvent(Event event);

	/**
	 *
	 * @param organizer
	 * @return
	 */
	Organizer saveOrganizer(Organizer organizer);

	/**
	 *
	 * @param presentation
	 * @return
	 */
	Presentation savePresentation(Presentation presentation);

	/**
	 *
	 * @param speaker
	 * @return
	 */
	Speaker saveSpeaker(Speaker speaker);

	/**
	 * Retrieves an {@link Organizer} with pictures (eagerly) if the picture
	 * is available.
	 *
	 * @param organizerId
	 * @return
	 */
	Organizer getOrganizerWithPicture(Long organizerId);

	/**
	 * Updates the application cache table which is used to set the version
	 * information for the HTML5 Application Cache Manifest
	 *
	 * @return The updated values of the {@link ApplicationCache}
	 */
	ApplicationCache updateApplicationCacheManifest();

	ApplicationCache getApplicationCacheManifest();

	FileData getPresentationFileData(Long presentationId);

	Event getCurrentEvent();

	Room getRoom(Long id);

	ScheduleItemList getScheduleForEvent(Long eventId);

	List<Evaluation> getEvaluationsForCurrentEvent();

	List<Evaluation> getEvaluationsForEvent(Long eventId);

	List<CfpSubmission> getCfpSubmissions(Long eventId);

	CfpSubmission saveCfpSubmission(CfpSubmission cfpSubmission);

	CfpSubmission saveAndNotifyCfpSubmission(CfpSubmission cfpSubmission);

	CfpSubmission getCfpSubmission(Long cfpId);

	void removeEvaluation(Long evaluationId);

	List<Track> getTracksForEvent(Long id);

	Track getTrack(Long id);

	PresentationTag getPresentationTag(String tagName);

	PresentationTag savePresentationTag(PresentationTag presentationTag);

	Map<PresentationTag, Long> getTagCloud(Long eventId);

	List<Presentation> findPresentations(
			PresentationSearchQuery presentationSearchQuery);

	List<Presentation> getPresentationsForEventOrderedByName(Long eventId);

	List<Presentation> getPresentationsForEventOrderedByTrack(Long eventId);

	List<Presentation> getPresentationsForEventOrderedByRoom(Long eventId);

	void deleteCfpSubmission(Long id);

	Set<PresentationTag> processPresentationTags(String tagsAsText);

	List<Sponsor> getSponsorsForEvent(Long id);

	Sponsor saveSponsor(Sponsor sponsorForm);

	Sponsor getSponsor(Long sponsorId);

	void deleteSponsor(Sponsor sponsorFromDb);

	Sponsor getSponsorWithPicture(Long sponsorId);

	List<Organizer> getAllOrganizersWithPicture();

	SponsorList getSponsorListForEvent(Long id, boolean large);
        
        EventSignup getEventSignup();
        
}
