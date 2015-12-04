/*
 * Copyright 2015 the original author or authors.
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
package com.devnexus.ting.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.Organizer;
import com.devnexus.ting.model.Presentation;
import com.devnexus.ting.model.PresentationList;
import com.devnexus.ting.model.PresentationTag;
import com.devnexus.ting.model.RoomList;
import com.devnexus.ting.model.ScheduleItemList;
import com.devnexus.ting.model.Sponsor;
import com.devnexus.ting.model.SponsorList;
import com.devnexus.ting.model.TrackList;

/**
 * Main DevNexus REST Controller.
 *
 * @author Gunnar Hillert
 *
 */
@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    private BusinessService businessService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

    @RequestMapping(path = "/presentations", method = RequestMethod.GET)
    public PresentationList presentationsForCurrentEvent() {

        PresentationList list = new PresentationList();

        List<Presentation> presentations = businessService.getPresentationsForCurrentEvent();
        list.setPresentations(presentations);
        return list;

    }

    @RequestMapping(path = "/{eventKey}/presentations", method = RequestMethod.GET)
    public PresentationList presentations(@PathVariable("eventKey") String eventKey) {

        final Event event = businessService.getEventByEventKey(eventKey);
        if (event != null) {
            PresentationList list = new PresentationList();

            List<Presentation> presentations = businessService.getPresentationsForEventOrderedByName(event.getId());
            list.setPresentations(presentations);
            return list;
        } else {
            LOGGER.warn("No current event available.");
            return null;
        }
    }

    @RequestMapping(path = "/schedule", method = RequestMethod.GET)
    public ScheduleItemList scheduleForCurrentEvent() {

        final Event event = businessService.getCurrentEvent();

        if (event != null) {
            final ScheduleItemList scheduleItemList = businessService.getScheduleForEvent(event.getId());
            return scheduleItemList;
        } else {
            LOGGER.warn("No current event available.");
            return null;
        }
    }

    @RequestMapping(path = "/{eventKey}/schedule", method = RequestMethod.GET)
    public ScheduleItemList schedule(@PathVariable("eventKey") String eventKey) {

        final Event event = businessService.getEventByEventKey(eventKey);
        if (event != null) {
            final ScheduleItemList scheduleItemList = businessService.getScheduleForEvent(event.getId());
            return scheduleItemList;
        } else {
            LOGGER.warn("No current event available.");
            return null;
        }
    }

    @RequestMapping(path = "/organizers", method = RequestMethod.GET)
    public List<Organizer> getOrganizers() {

        return businessService.getAllOrganizersWithPicture();

    }

    @RequestMapping(value = "/organizers/{organizerId}.jpg", method = RequestMethod.GET)
    public void getOrganizerPicture(@PathVariable("organizerId") Long organizerId, HttpServletResponse response) {

        final Organizer organizer = businessService.getOrganizerWithPicture(organizerId);

        final byte[] organizerPicture;

        if (organizer == null || organizer.getPicture() == null) {
            organizerPicture = SystemInformationUtils.getOrganizerImage(null);
            response.setContentType("image/jpg");
        } else {
            organizerPicture = organizer.getPicture().getFileData();
            response.setContentType(organizer.getPicture().getType());
        }

        try {
            org.apache.commons.io.IOUtils.write(organizerPicture, response.getOutputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    @RequestMapping(value = "/sponsors/{sponsorId}.jpg", method = RequestMethod.GET)
    public void getSponsorPicture(@PathVariable("sponsorId") Long sponsorId, HttpServletResponse response) {

        final Sponsor sponsor = businessService.getSponsorWithPicture(sponsorId);

        final byte[] sponsorPicture;

        if (sponsor == null || sponsor.getLogo() == null) {
            sponsorPicture = SystemInformationUtils.getOrganizerImage(null);
            response.setContentType("image/jpg");
        } else {
            sponsorPicture = sponsor.getLogo().getFileData();
            response.setContentType(sponsor.getLogo().getType());
        }

        try {
            org.apache.commons.io.IOUtils.write(sponsorPicture, response.getOutputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @RequestMapping(path = "/{eventKey}/sponsors", method = RequestMethod.GET)
    public SponsorList getSponsorsForEvent(@PathVariable("eventKey") String eventKey) {
        final Event event = businessService.getEventByEventKey(eventKey);
        return businessService.getSponsorListForEvent(event.getId(), true);
    }

    @RequestMapping(path = "/sponsors", method = RequestMethod.GET)
    public SponsorList getSponsors() {
        final Event event = businessService.getCurrentEvent();
        return businessService.getSponsorListForEvent(event.getId(), true);
    }

    @RequestMapping(path = "/{eventKey}/tracks", method = RequestMethod.GET)
    public TrackList getTracksForEventKey(@PathVariable("eventKey") final String eventKey) {
        return prepareTrackData(businessService.getEventByEventKey(eventKey));
    }

    @RequestMapping(path = "/tracks", method = RequestMethod.GET)
    public TrackList getTracksForCurrentEvent() {
        return prepareTrackData(businessService.getCurrentEvent());
    }

    public TrackList prepareTrackData(Event event) {
        final TrackList trackList = new TrackList();
        trackList.setTracks(businessService.getTracksForEvent(event.getId()));

        final List<Presentation> presentations = businessService.getPresentationsForEventOrderedByName(event.getId());
        int unassigned = 0;
        for (Presentation presentation : presentations) {
            if (presentation.getTrack() == null) {
                unassigned++;
            }
        }
        trackList.setUnassignedSessions(unassigned);
        return trackList;
    }

    @RequestMapping(path = "/{eventKey}/tags", method = RequestMethod.GET)
    public Map<PresentationTag, Long> getTracksForEventKey(@PathVariable("eventKey") final String eventKey,
            final Model model) {

        final Event event = businessService.getEventByEventKey(eventKey);

        final Map<PresentationTag, Long> tagCloud = businessService.getTagCloud(event.getId());

        return tagCloud;
    }

    @RequestMapping(path = "/tags", method = RequestMethod.GET)
    public Map<PresentationTag, Long> getTagCloudForCurrentEvent() {

        final Event event = businessService.getCurrentEvent();

        final Map<PresentationTag, Long> tagCloud = businessService.getTagCloud(event.getId());

        return tagCloud;
    }

    @RequestMapping(path = "/{eventKey}/rooms", method = RequestMethod.GET)
    public RoomList getRoomsForEventKey(@PathVariable("eventKey") final String eventKey) {

        final Event event = businessService.getEventByEventKey(eventKey);

        final RoomList roomList = new RoomList();
        roomList.setRooms(businessService.getRoomsForEvent(event.getId()));

        return roomList;
    }

    @RequestMapping(path = "/rooms", method = RequestMethod.GET)
    public RoomList getRoomsForCurrentEvent() {

        final Event event = businessService.getCurrentEvent();

        final RoomList roomList = new RoomList();
        roomList.setRooms(businessService.getRoomsForEvent(event.getId()));

        return roomList;
    }
}
