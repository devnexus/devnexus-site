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

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.PresentationTag;

/**
 * @author Gunnar Hillert
 */
@Controller
public class TagController {

	@Autowired private BusinessService businessService;

	@RequestMapping("/s/{eventKey}/tags")
	public String getTracksForEventKey(@PathVariable("eventKey") final String eventKey,
										final Model model) {

		final Event event = businessService.getEventByEventKey(eventKey);
		model.addAttribute("contextEvent", event);

		final Map<PresentationTag, Long> tagCloud = businessService.getTagCloud(event.getId());
		model.addAttribute("tagCloud", tagCloud);

		return "tags";
	}

	@RequestMapping("/s/tags")
	public String getTagCloudForCurrentEvent(final Model model) {

		final Event event = businessService.getCurrentEvent();

		final Map<PresentationTag, Long> tagCloud = businessService.getTagCloud(event.getId());
		model.addAttribute("tagCloud", tagCloud);

		return "tags";
	}

}
