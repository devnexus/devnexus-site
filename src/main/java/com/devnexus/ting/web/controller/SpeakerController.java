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

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.SpeakerList;

/**
 * Display speakers.
 *
 * @author Gunnar Hillert
 *
 */
@Controller
public class SpeakerController {

	@Autowired private BusinessService businessService;

	@RequestMapping(value="/s/speakers", method = RequestMethod.GET)
	public String getSpeakersForCurrentEvent(Model model, @RequestParam(value="image", defaultValue="false") boolean image) {
		Event currentEvent = businessService.getCurrentEvent();
		prepareSpeakers(currentEvent, model);
		return "speakers";
	}

	@RequestMapping("/s/{eventKey}/speakers")
	public String getSpeakersForEvent(@PathVariable("eventKey") String eventKey, Model model) {
		final Event event = businessService.getEventByEventKey(eventKey);
		model.addAttribute("contextEvent", event);
		prepareSpeakers(event, model);
		return "speakers";
	}

	private void prepareSpeakers(Event event, Model model) {
		model.addAttribute("event", event);
		SpeakerList speakers = new SpeakerList();
		speakers.setSpeakers(businessService.getSpeakersForEvent(event.getId()));
		model.addAttribute("speakerList",speakers);
		int columnLength = (int)(speakers.getSpeakers().size() / 4);

		model.addAttribute("columnLength", columnLength < 1 ? 1 : columnLength);
	}

	@RequestMapping(value="/s/speakers/{speakerId}.jpg", method=RequestMethod.GET)
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
