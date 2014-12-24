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
package com.devnexus.ting.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.imgscalr.Scalr;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.model.ApplicationCache;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.FileData;
import com.devnexus.ting.core.model.Organizer;
import com.devnexus.ting.core.model.OrganizerList;
import com.devnexus.ting.core.model.ScheduleItemList;
import com.devnexus.ting.core.model.SpeakerList;
import com.devnexus.ting.core.model.Sponsor;
import com.devnexus.ting.core.model.SponsorLevel;
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

	@RequestMapping({"/index", "/"})
	public String index(final Model model) {
		final Event event = businessService.getCurrentEvent();
		final List<Sponsor> sponsors = businessService.getSponsorsForEvent(event.getId());

		final Map<Long, String> logos = new HashMap<>();

		Map<SponsorLevel, Integer> sponsorLevelCount = new HashMap<SponsorLevel, Integer>();

		for (SponsorLevel level : SponsorLevel.values()) {
			sponsorLevelCount.put(level, Integer.valueOf(0));
		}

		for (Sponsor sponsor : sponsors) {

			sponsorLevelCount.put(sponsor.getSponsorLevel(), Integer.valueOf(sponsorLevelCount.get(sponsor.getSponsorLevel()).intValue() + 1));

			FileData imageData = businessService.getSponsorWithPicture(sponsor.getId()).getLogo();

			final int size;

                        switch (sponsor.getSponsorLevel()) {
                            case COCKTAIL_HOUR:
                            case PLATINUM:
                            case MEDIA_PARTNER:
                                size = 180;
                                break;
                            case GOLD:
                                size = 140;
                                break;
                            case SILVER:
                                size = 110;
                                break;
                            default:
                                    throw new IllegalStateException("Unsupported SponsorLevel " + sponsor.getSponsorLevel());
                        }
                        
			if (imageData != null) {
				ByteArrayInputStream bais = new ByteArrayInputStream(imageData.getFileData());
				BufferedImage image;
				try {
					image = ImageIO.read(bais);
					final BufferedImage scaled = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, size);
					final ByteArrayOutputStream out = new ByteArrayOutputStream();
					ImageIO.write(scaled, "PNG", out);

					byte[] bytes = out.toByteArray();

					final String base64bytes = Base64.encodeBase64String(bytes);
					final String src = "data:image/png;base64," + base64bytes;
					logos.put(sponsor.getId(), src);
				} catch (IOException e) {
					LOGGER.error("Error while processing logo for sponsor " + sponsor.getName(), e);
				}
			}
		}

		model.addAttribute("sponsors", sponsors);
		model.addAttribute("logos", logos);
		model.addAttribute("sponsorLevelCount", sponsorLevelCount);
		return "index";
	}

	@RequestMapping({"/schedule", "/api/schedule"})
	public String scheduleForCurrentEvent(final Model model,
			@RequestParam(required=false, value="old") boolean old) {

		final Event event = businessService.getCurrentEvent();

		if (event != null) {
			final ScheduleItemList scheduleItemList = businessService.getScheduleForEvent(event.getId());
			model.addAttribute("scheduleItemList", scheduleItemList);
		}
		else {
			LOGGER.warn("No current event available.");
		}

		if (old) {
			return "schedule-old";
		}
		return "schedule";
	}

	@RequestMapping({"/new-schedule", "/api/schedule"})
	public String newscheduleForCurrentEvent(final Model model) {

		final Event event = businessService.getCurrentEvent();

		if (event != null) {
			final ScheduleItemList scheduleItemList = businessService.getScheduleForEvent(event.getId());
			model.addAttribute("scheduleItemList", scheduleItemList);
		}
		else {
			LOGGER.warn("No current event available.");
		}

		return "new-schedule";
	}

	@RequestMapping("/privacy-policy")
	public String privacyPolicy() {
		return "privacy-policy";
	}

	@RequestMapping("/code-of-conduct")
	public String codeOfConduct() {
		return "code-of-conduct";
	}

	@RequestMapping("/past-conferences")
	public String pastConferences(final Model model) {

		//TODO
		model.addAttribute("headerTitle", "Previous Conferences");
		return "past-conferences";

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

		final List<Organizer>organizers = businessService.getAllOrganizersWithPicture();

		final OrganizerList organizerList = new OrganizerList(organizers);
		model.addAttribute("organizerList", organizerList);

		int columnLength = (int)(organizers.size() / 4);

		model.addAttribute("columnLength", columnLength < 1 ? 1 : columnLength);
		model.addAttribute("organizers", organizers);

		final Map<Long, String> organizerPictures = new HashMap<>();

		for (Organizer organizer : organizers) {
			final FileData imageData = organizer.getPicture();
			if (imageData != null) {
				final ByteArrayInputStream bais = new ByteArrayInputStream(imageData.getFileData());
				try {
					final BufferedImage image;
					final BufferedImage imageToReturn;
					image = ImageIO.read(bais);

					if (image.getWidth() != 140 && image.getHeight() != 140) {
						imageToReturn =  Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT, 140, 140);
					}
					else {
						imageToReturn = image;
					}

					final ByteArrayOutputStream out = new ByteArrayOutputStream();
					ImageIO.write(imageToReturn, "JPG", out);

					byte[] bytes = out.toByteArray();

					final String base64bytes = Base64.encodeBase64String(bytes);
					final String src = "data:image/jpg;base64," + base64bytes;
					organizerPictures.put(organizer.getId(), src);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		model.addAttribute("organizerPictures", organizerPictures);
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

	@RequestMapping(value="/sponsors/{sponsorId}.jpg", method=RequestMethod.GET)
	public void getSponsorPicture(@PathVariable("sponsorId") Long sponsorId, HttpServletResponse response) {

		final Sponsor sponsor = businessService.getSponsorWithPicture(sponsorId);

		final byte[] sponsorPicture;

		if (sponsor==null || sponsor.getLogo() == null) {
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
