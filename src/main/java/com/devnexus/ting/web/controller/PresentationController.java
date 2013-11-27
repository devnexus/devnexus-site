/*
 * Copyright 2002-2013 the original author or authors.
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
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.FileData;
import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.PresentationList;
import com.devnexus.ting.core.service.BusinessService;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 *
 */
@Controller
public class PresentationController {

	@Autowired private BusinessService businessService;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PresentationController.class);

	private void preparePresentationsForEvent(Event event, Model model) {
		model.addAttribute("event", event);
		final PresentationList presentationList = new PresentationList();
        List<Presentation> presentations;
        Collections.sort(presentations = businessService.getPresentationsForEvent(event.getId()));
		presentationList.setPresentations(presentations);
		model.addAttribute("presentationList", presentationList);
	}

	@RequestMapping("/{eventKey}/presentations")
	public String getPresentationsForEvent(@PathVariable("eventKey") final String eventKey,
										   final Model model,
										   final SitePreference sitePreference) {
		final Event event = businessService.getEventByEventKey(eventKey);
		this.preparePresentationsForEvent(event, model);
		return "presentations";
	}

	@RequestMapping(value="/presentations/{presentationId}/slides", method=RequestMethod.GET)
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

	@RequestMapping("/presentations")
	public String getPresentationsForCurrentEvent(final Model model,
												  final SitePreference sitePreference) {
		final Event event = businessService.getCurrentEvent();
		this.preparePresentationsForEvent(event, model);
		return "presentations";
	}

}
