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
import org.springframework.web.bind.annotation.RequestParam;

import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.SpeakerList;
import com.devnexus.ting.core.service.BusinessService;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Controller
public class SpeakerController {

	@Autowired private BusinessService businessService;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;

	private final static Logger LOGGER = LoggerFactory.getLogger(SpeakerController.class);


	@RequestMapping(value="/speakers", method = RequestMethod.GET)
	public String getSpeakersForCurrentEvent(Model model, final SitePreference sitePreference, @RequestParam(value="image", defaultValue="false") boolean image) {
		SpeakerList speakers = new SpeakerList();
		speakers.setSpeakers(businessService.getSpeakersForCurrentEvent());

		model.addAttribute("speakerList",speakers);

		if (sitePreference.isMobile()) {
			return "speakers-mobile";
		}

		return "speakers";
	}

	@RequestMapping("/{eventKey}/speakers")
	public String getSpeakersForEvent(@PathVariable("eventKey") String eventKey, Model model, final SitePreference sitePreference) {
		final Event event = businessService.getEventByEventKey(eventKey);
		model.addAttribute("event", event);

		SpeakerList speakers = new SpeakerList();
		speakers.setSpeakers(businessService.getSpeakersForEvent(event.getId()));
		model.addAttribute("speakerList",speakers);

		if (sitePreference.isMobile()) {
			return "speakers-mobile";
		}

		return "speakers";
	}

	@RequestMapping(value="/speakers/{speakerId}.jpg", method=RequestMethod.GET)
	public void getSpeakerPicture(@PathVariable("speakerId") Long speakerId, HttpServletResponse response) {

		final byte[] speakerImage = businessService.getSpeakerImage(speakerId);

		try {
			org.apache.commons.io.IOUtils.write(speakerImage, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.setContentType("image/jpg");

	}

}
