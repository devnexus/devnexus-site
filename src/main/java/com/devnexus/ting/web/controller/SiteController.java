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
package com.devnexus.ting.web.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.model.ApplicationCache;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.Organizer;
import com.devnexus.ting.core.model.OrganizerList;
import com.devnexus.ting.core.model.ScheduleItemList;
import com.devnexus.ting.core.model.SpeakerList;
import com.devnexus.ting.core.model.TwitterMessage;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.core.service.TwitterService;

/**
 * Main DevNexus Site Controller - Contains various simple controllers for various
 * sections of the site.
 *
 * @author Gunnar Hillert
 *
 */
@Controller
public class SiteController {

	@Autowired private BusinessService businessService;

	@Autowired private TwitterService twitterService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class);
    private static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;


    @RequestMapping({"/index", "/"})
	public String execute(final Model model) {



        int daysUntil;
        final Calendar startDate = Calendar.getInstance();
        String countdowntext;

        startDate.set(Calendar.YEAR, 2014);
        startDate.set(Calendar.DATE, 24);
        startDate.set(Calendar.MONTH, Calendar.FEBRUARY);

        daysUntil = (int) ((startDate.getTime().getTime() - Calendar.getInstance().getTime().getTime()) / DAY_IN_MILLIS);
        countdowntext = "Too long...";
        if (daysUntil > 0) {
            countdowntext = String.format("%d days until DevNexus.", daysUntil);
        } else if (daysUntil < -1) {
            countdowntext = "DevNexus is right now!";
        }

        final Collection<TwitterMessage> tweets = twitterService.getTwitterMessages();
        model.addAttribute("tweets", tweets);
        model.addAttribute("countdowntext", countdowntext);
        return "index";


    }


	@RequestMapping({"/schedule", "/api/schedule"})
	public String scheduleForCurrentEvent(final Model model) {

		final Event event = businessService.getCurrentEvent();
		model.addAttribute("headerTitle", "Schedule222");
		model.addAttribute("tag", "600+ Developers, 57 Presentations, 48 Speakers, 2 Days");
		if (event != null) {
			final ScheduleItemList scheduleItemList = businessService.getScheduleForEvent(event.getId());
			model.addAttribute("scheduleItemList", scheduleItemList);
		}
		else {
			LOGGER.warn("No current event available.");
		}
		return "schedule";

	}


	@RequestMapping("/past-conferences")
	public String pastConferences(final Model model) {

		//TODO
		model.addAttribute("headerTitle", "Previous Conferences");
		return "past-conferences";

	}

	@RequestMapping("/register")
	public String register(final Model model) {
		//TODO
		model.addAttribute("headerTitle", "Registration Information");

		return "registration";
	}

	@RequestMapping({"/{eventKey}/schedule", "/api/{eventKey}/schedule"})
	public String scheduleV2(@PathVariable("eventKey") String eventKey, final Model model) {

		final Event event = businessService.getEventByEventKey(eventKey);

		final ScheduleItemList scheduleItemList = businessService.getScheduleForEvent(event.getId());

		model.addAttribute("scheduleItemList", scheduleItemList);

		return "schedule";
	}

	@RequestMapping("/travel")
	public String travel(final Model model) {
        LOGGER.warn("This is a log.");
		return "travel";
	}

	@RequestMapping("/appcache.manifest")
	public String appcache(final Model model) {

		final List<Organizer>organizers = businessService.getAllOrganizers();

		final OrganizerList organizerList = new OrganizerList(organizers);
		model.addAttribute("organizerList", organizerList);

		SpeakerList speakers = new SpeakerList();
		speakers.setSpeakers(businessService.getSpeakersForCurrentEvent());
		model.addAttribute("speakerList",speakers);

		ApplicationCache applicationCache = businessService.getApplicationCacheManifest();

		model.addAttribute("applicationCache", applicationCache);
		return "appcache";
	}

	@RequestMapping("/appcache-mobile.manifest")
	public String appcacheMobile(final Model model) {

		final List<Organizer>organizers = businessService.getAllOrganizers();

		final OrganizerList organizerList = new OrganizerList(organizers);
		model.addAttribute("organizerList", organizerList);

		SpeakerList speakers = new SpeakerList();
		speakers.setSpeakers(businessService.getSpeakersForCurrentEvent());
		model.addAttribute("speakerList",speakers);

		ApplicationCache applicationCache = businessService.getApplicationCacheManifest();

		model.addAttribute("applicationCache", applicationCache);
		return "appcache-mobile";
	}

	@RequestMapping("/organizers")
	public String getOrganizers(final Model model) {

		final List<Organizer>organizers = businessService.getAllOrganizers();

		final OrganizerList organizerList = new OrganizerList(organizers);
		model.addAttribute("organizerList", organizerList);

		int columnLength = (int)(organizers.size() / 4);

		model.addAttribute("columnLength", columnLength < 1 ? 1 : columnLength);
		model.addAttribute("organizers", organizers);


		return "organizers";

	}

	@RequestMapping(value="/organizers/{organizerId}.jpg", method=RequestMethod.GET)
	public void getOrganizerPicture(@PathVariable("organizerId") Long organizerId, HttpServletResponse response) {

		final Organizer organizer = businessService.getOrganizerWithPicture(organizerId);

		final byte[] organizerPicture;

		if (organizer==null || organizer.getPicture() == null) {
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

	@RequestMapping(value="/social", method=RequestMethod.GET)
	public String getTwitterFeed(Model model) {

		final Collection<TwitterMessage> tweets = twitterService.getTwitterMessages();
		model.addAttribute("tweets", tweets);

		return "social";

	}

	@Autowired private SimpMessageSendingOperations messagingTemplate;
	@Autowired
	ApplicationEventPublisher applicationEventPublisher;

	@SubscribeMapping("/positions")
	public void onApplicationEvent() {
		LOGGER.info("New user subscribing");
		//messagingTemplate.convertAndSend("/queue/tweets", event.getTwitterMessage());
	}

	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}
}
