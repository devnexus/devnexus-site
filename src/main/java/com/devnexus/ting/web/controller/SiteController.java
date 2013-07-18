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
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
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
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Controller
public class SiteController {

	@Autowired private BusinessService businessService;

	@Autowired private TwitterService twitterService;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;

	private final static Logger LOGGER = LoggerFactory.getLogger(SiteController.class);

	@RequestMapping({"/index", "/"})
	public String execute(final Model model, final SitePreference sitePreference) {

		if (sitePreference.isMobile()) {
			return "index-mobile";
		}
		else {
			final Collection<TwitterMessage> tweets = twitterService.getTwitterMessages();
			model.addAttribute("tweets", tweets);
			return "index";
		}
	}

	@RequestMapping("/schedule")
	public String scheduleForCurrentEvent(final Model model, final SitePreference sitePreference) {

		final Event event = businessService.getCurrentEvent();

		final ScheduleItemList scheduleItemList = businessService.getScheduleForEvent(event.getId());

		model.addAttribute("scheduleItemList", scheduleItemList);

		if (sitePreference.isMobile()) {
			return "schedule-mobile";
		}

		return "schedule";

	}

	@RequestMapping("/{eventKey}/schedule")
	public String scheduleV2(@PathVariable("eventKey") String eventKey, final Model model, final SitePreference sitePreference) {

		final Event event = businessService.getEventByEventKey(eventKey);

		final ScheduleItemList scheduleItemList = businessService.getScheduleForEvent(event.getId());

		model.addAttribute("scheduleItemList", scheduleItemList);

		if (sitePreference.isMobile()) {
			return "schedule-mobile";
		}

		return "schedule";
	}

	@RequestMapping("/travel")
	public String travel(final Model model, final SitePreference sitePreference) {

		if (sitePreference.isMobile()) {
			return "travel-mobile";
		}

		return "travel";
	}

	@RequestMapping("/appcache.manifest")
	public String appcache(final Model model, final SitePreference sitePreference) {

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
	public String appcacheMobile(final Model model, final SitePreference sitePreference) {

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
	public String getOrganizers(final Model model, final SitePreference sitePreference) {

		final List<Organizer>organizers = businessService.getAllOrganizers();

		final OrganizerList organizerList = new OrganizerList(organizers);
		model.addAttribute("organizerList", organizerList);


		model.addAttribute("organizers", organizers);

		if (sitePreference.isMobile()) {
			return "organizers-mobile";
		}

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

	@RequestMapping(value="/twitter", method=RequestMethod.GET)
	public String getTwitterFeed(Model model) {

		final Collection<TwitterMessage> tweets = twitterService.getTwitterMessages();
		model.addAttribute("tweets", tweets);

		return "twitter-feed-mobile";

	}

}
