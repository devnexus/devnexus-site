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
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.FileData;
import com.devnexus.ting.model.Presentation;
import com.devnexus.ting.model.PresentationList;
import com.devnexus.ting.model.support.PresentationSearchQuery;

/**
 *
 * @author Gunnar Hillert
 *
 */
@Controller
public class PresentationController {

	@Autowired private BusinessService businessService;

	private PresentationList preparePresentationsForEvent(Event event, Model model, String order, PresentationSearchQuery presentationSearchQuery) {

		Assert.hasText(order);

		model.addAttribute("event", event);
		final PresentationList presentationList = new PresentationList();
		List<Presentation> presentations;

		if (presentationSearchQuery == null) {

			if ("track".equalsIgnoreCase(order)) {
				presentations = businessService.getPresentationsForEventOrderedByTrack(event.getId());
			}
			else if ("room".equalsIgnoreCase(order)) {
				presentations = businessService.getPresentationsForEventOrderedByRoom(event.getId());
			}
			else if ("name".equalsIgnoreCase(order)) {
				presentations = businessService.getPresentationsForEventOrderedByName(event.getId());
			}
			else {
				throw new IllegalArgumentException("Order " + order + " not supported.");
			}
			presentationList.setPresentations(presentations);
			model.addAttribute("presentationList", presentationList);
		}
		else {
			presentations = businessService.findPresentations(presentationSearchQuery);
			presentationList.setPresentations(presentations);
			model.addAttribute("presentationList", presentationList);
		}

		return presentationList;
	}

	@RequestMapping(value="/s/{eventKey}/presentations", method=RequestMethod.GET)
	public String getPresentationsForEvent(@PathVariable("eventKey") final String eventKey,
											@RequestParam(value="order", defaultValue="track") String order,
											@RequestParam(value="trackId", required=false) Long trackId,
											@RequestParam(value="trackName", required=false) String trackName,
											@RequestParam(value="type", required=false) String type,
											@RequestParam(value="experience", required=false) String experience,
											@RequestParam(value="tags", required=false)  String tags,
											final Model model) {
		final Event event = businessService.getEventByEventKey(eventKey);
		model.addAttribute("contextEvent", event);
		final PresentationList presentationList = this.preparePresentationsForEvent(event, model, order, PresentationSearchQuery.create(event, trackId, trackName, type, experience, tags));

		if ("room".equalsIgnoreCase(order) && presentationList.getPresentations().isEmpty()) {
			return "presentations-empty";
		}

		return "presentations-by-" + order;
	}

	@RequestMapping(value="/s/presentations/{presentationId}/slides", method=RequestMethod.GET)
	public void getPresentationSlides(@PathVariable("presentationId") Long presentationId, HttpServletResponse response) {

		final FileData presentationFileData = businessService.getPresentationFileData(presentationId);

		if (presentationFileData != null) {

			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition",
					"attachment; filename=\"" + presentationFileData.getName() + "\"");
			response.setContentLength(presentationFileData.getFileSize().intValue());
			try {
				IOUtils.write(presentationFileData.getFileData(),response.getOutputStream());
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}

		} else {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}

	}

	@RequestMapping("/s/presentations")
	public String getPresentationsForCurrentEvent(
			@RequestParam(value="order", defaultValue="track") String order,
			@RequestParam(value="trackId", required=false) Long trackId,
			@RequestParam(value="trackName", required=false) String trackName,
			@RequestParam(value="type", required=false)  String type,
			@RequestParam(value="experience", required=false) String experience,
			@RequestParam(value="tags", required=false)  String tags,
			final Model model) {

		final Event event = businessService.getCurrentEvent();
		PresentationSearchQuery presentationSearchQuery = PresentationSearchQuery.create(event, trackId, trackName, type, experience, tags);

		final PresentationList presentationList = this.preparePresentationsForEvent(event, model, order, presentationSearchQuery);

		if ("room".equalsIgnoreCase(order) && presentationList.getPresentations().isEmpty()) {
			return "presentations-empty";
		}

		return "presentations-by-" + order;
	}

	@RequestMapping("/s/old-presentations")
	public String getOldPresentationsForCurrentEvent(
			@RequestParam(value="order", defaultValue="track") String order,
			@RequestParam(value="trackId", required=false) Long trackId,
			@RequestParam(value="trackName", required=false) String trackName,
			@RequestParam(value="type", required=false)  String type,
			@RequestParam(value="experience", required=false) String experience,
			@RequestParam(value="tags", required=false)  String tags,
			final Model model) {

		final Event event = businessService.getCurrentEvent();
		PresentationSearchQuery presentationSearchQuery = PresentationSearchQuery.create(event, trackId, trackName, type, experience, tags);

		final PresentationList presentationList = this.preparePresentationsForEvent(event, model, order, presentationSearchQuery);

		if ("room".equalsIgnoreCase(order) && presentationList.getPresentations().isEmpty()) {
			return "presentations-empty";
		}

		return "presentations-by-" + order;
	}

}
