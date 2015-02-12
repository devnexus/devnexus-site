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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.TrackList;
import com.devnexus.ting.core.service.BusinessService;

/**
 * @author Gunnar Hillert
 */
@Controller
public class TrackController {

	@Autowired private BusinessService businessService;

	@RequestMapping("/{eventKey}/tracks")
	public String getTracksForEventKey(@PathVariable("eventKey") final String eventKey,
										final Model model) {
		prepareData(businessService.getEventByEventKey(eventKey), model);
		return "tracks";
	}

	@RequestMapping("/tracks")
	public String getTracksForCurrentEvent(final Model model) {
		prepareData(businessService.getCurrentEvent(), model);
		return "tracks";
	}

	public void prepareData(Event event, final Model model) {
		final TrackList trackList = new TrackList();
		trackList.setTracks(businessService.getTracksForEvent(event.getId()));
		model.addAttribute("trackList", trackList);
		final List<Presentation> presentations = businessService.getPresentationsForEventOrderedByName(event.getId());
		int unassigned = 0;
		for (Presentation presentation : presentations) {
			if (presentation.getTrack() == null) {
				unassigned++;
			}
		}
		model.addAttribute("unassignedSessions", unassigned);
	}

}
