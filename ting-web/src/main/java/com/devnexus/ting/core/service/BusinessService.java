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
package com.devnexus.ting.core.service;

import java.util.List;

import com.devnexus.ting.core.model.ApplicationCache;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.FileData;
import com.devnexus.ting.core.model.Organizer;
import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.Speaker;

/**
 * The central service layer of Ting.
 *
 * @author Gunnar Hillert
 * @since 1.0
 *
 */
public interface BusinessService {

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

    /**
     *
     * @param eventId
     * @return
     */
    List<Presentation> getPresentationsForEvent(Long eventId);

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
     */
    void savePresentation(Presentation presentation);

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
}
