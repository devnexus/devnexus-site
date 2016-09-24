/*
 * Copyright 2002-2016 the original author or authors.
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.SizeLimitExceededException;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.MultipartProperties;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.core.service.TwitterService;
import com.devnexus.ting.model.ApplicationCache;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.FileData;
import com.devnexus.ting.model.Organizer;
import com.devnexus.ting.model.OrganizerList;
import com.devnexus.ting.model.SpeakerList;
import com.devnexus.ting.model.Sponsor;
import com.devnexus.ting.model.SponsorList;
import com.devnexus.ting.model.TwitterMessage;

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
	@Autowired private MultipartProperties multipartProperties;

	private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class);

	@RequestMapping({"/api/", "/api"})
	public ModelAndView apiRoot() {
		return new ModelAndView("redirect:/swagger-ui.html");
	}

	@RequestMapping({"/s/index", "/s/"})
	public String index(final Model model) {
		final Event event = businessService.getCurrentEvent();
		final SponsorList sponsorList = businessService.getSponsorListForEvent(event.getId(), false);
		model.addAttribute("sponsorList", sponsorList);
		return "index";
	}

	@RequestMapping("/s/handleGlobaleErrors")
	public String onUploadError(HttpServletRequest request, final Model model, RedirectAttributes redirectAttributes) {

		final Object errorExceptionObject = request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);

		if (errorExceptionObject instanceof MultipartException) {
			final MultipartException multipartException = (MultipartException) errorExceptionObject;
			final Throwable rootCause = multipartException.getRootCause();
			String errorMessage = "";
			if (rootCause instanceof SizeLimitExceededException ||
					rootCause instanceof FileSizeLimitExceededException) {
				errorMessage = String.format(
					  "The file that you are trying to upload is unfortunately too big. Uploaded files cannot be bigger than <strong>%s</strong>."
					+ " Please go back and upload a smaller image file.", multipartProperties.getMaxFileSize());
			}
			else {
				errorMessage = (String) request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE);
			}
			redirectAttributes.addFlashAttribute("error", errorMessage);
			return "redirect:/s/uploadError";
		}
		else {
			return "error/error";
		}
	}

	@RequestMapping("/s/uploadError")
	public String onUploadError2(HttpServletRequest request, final Model model) {
		return "uploadError";
	}

	@RequestMapping({"/s/manager"})
	public String manager(final Model model) {
		final Event event = businessService.getCurrentEvent();
		final SponsorList sponsorList = businessService.getSponsorListForEvent(event.getId(), false);
		model.addAttribute("sponsorList", sponsorList);
		return "manager";
	}

	@RequestMapping("/s/privacy-policy")
	public String privacyPolicy() {
		return "privacy-policy";
	}

        @RequestMapping("/s/conference-info")
	public String conferenceInfo() {
		return "conference-info";
	}
        
	@RequestMapping("/s/about")
	public String about() {
		return "about";
	}

	@RequestMapping("/s/cfp")
	public String cfpOveriew() {
		return "cfp-overview";
	}

	@RequestMapping("/s/map")
	public String map() {
		return "map";
	}

	@RequestMapping("/s/code-of-conduct")
	public String codeOfConduct() {
		return "code-of-conduct";
	}
        
        

	@RequestMapping("/s/workshop-instructions")
	public String workshopInstructions() {
		return "workshop-instructions";
	}

	@RequestMapping("/s/past-conferences")
	public String pastConferences(final Model model) {

		//TODO
		model.addAttribute("headerTitle", "Previous Conferences");
		return "past-conferences";

	}

	@RequestMapping("/s/travel")
	public String travel(final Model model) {
		LOGGER.warn("This is a log.");
		return "travel";
	}

	@RequestMapping("/s/appcache.manifest")
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

	@RequestMapping("/s/appcache-mobile.manifest")
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

	@RequestMapping("/s/organizers")
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

	@RequestMapping(value="/s/organizers/{organizerId}.jpg", method=RequestMethod.GET)
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

	@RequestMapping(value="/s/sponsors/{sponsorId}.jpg", method=RequestMethod.GET)
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

	@RequestMapping("/s/{eventKey}/sponsors")
	public String getSponsorsForEvent(@PathVariable("eventKey") String eventKey, final Model model) {

		final Event event = businessService.getEventByEventKey(eventKey);
		final SponsorList sponsorList = businessService.getSponsorListForEvent(event.getId(), true);
		model.addAttribute("sponsorList", sponsorList);
		model.addAttribute("contextEvent", event);

		return "sponsors";
	}

	@RequestMapping(value="/s/sponsors")
	public String getSponsors(Model model) {

		final Event event = businessService.getCurrentEvent();
		final SponsorList sponsorList = businessService.getSponsorListForEvent(event.getId(), true);
		model.addAttribute("sponsorList", sponsorList);

		return "sponsors";

	}

	@RequestMapping(value="/s/social", method=RequestMethod.GET)
	public String getTwitterFeed(Model model) {

		final Collection<TwitterMessage> tweets = twitterService.getTwitterMessages();
		model.addAttribute("tweets", tweets);

		return "social";

	}

	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}
}
