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
package com.devnexus.ting.web.controller.admin;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.CfpSubmissionSpeaker;
import com.devnexus.ting.model.CfpSubmissionStatusType;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.Presentation;
import com.devnexus.ting.model.PresentationTag;
import com.devnexus.ting.model.Speaker;

/**
 * Migrate a CFP into the system
 *
 * @author Gunnar Hillert
 */
@Controller
public class AcceptCfpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AcceptCfpController.class);

	@Autowired private BusinessService businessService;

	private void prepareReferenceData(ModelMap model) {

		final Event currentEvent = businessService.getCurrentEvent();
		model.addAttribute("currentEvent", currentEvent);

		final Set<CfpSubmissionStatusType> cfpSubmissionStatusTypes = EnumSet.allOf(CfpSubmissionStatusType.class);
		model.addAttribute("cfpSubmissionStatusTypes", cfpSubmissionStatusTypes);

		final List<Speaker> allSpeakers = businessService.getAllSpeakersOrderedByName();
		model.addAttribute("allSpeakers", allSpeakers);
	}

	@RequestMapping(value="/s/admin/{eventKey}/cfps/{cfpId}/accept", method=RequestMethod.GET)
	public String prepareAcceptCfp(@PathVariable("cfpId") Long cfpId, ModelMap model) {
		prepareReferenceData(model);
		final CfpSubmission cfpSubmission = businessService.getCfpSubmission(cfpId);
		model.addAttribute("cfpSubmission", cfpSubmission);
		return "/admin/accept-cfp";
	}

	@RequestMapping(value="/s/admin/{eventKey}/cfps/{cfpId}/accept", method=RequestMethod.POST)
	public String acceptCfp(@PathVariable("eventKey") String eventKey,
	                        @PathVariable("cfpId") Long cfpId,
	                        CfpSubmission cfpSubmission,
	                        BindingResult result, HttpServletRequest request) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/{eventKey}/cfps#cfp_{cfpId}";
		}

		if (result.hasErrors()) {
			return "/admin/accept-cfp";
		}

		final Long cfpSubmissionId = cfpSubmission.getId();
		final CfpSubmission cfpSubmissionFromDb = businessService.getCfpSubmission(cfpSubmissionId);
		final Event currentEvent = businessService.getCurrentEvent();

		LOGGER.info("Setting up CFP Submission " + cfpSubmissionId);

		//First we need to setup the speaker (s)

		final List<Speaker> updatedSpeakers = new ArrayList<Speaker>(1);

		for (CfpSubmissionSpeaker cfpSubmissionSpeaker : cfpSubmissionFromDb.getSpeakers()) {
			final Long existingSpeakerId = cfpSubmission.getSpeakerIdForCfpSpeakerId(cfpSubmissionSpeaker.getId());
			Assert.notNull(existingSpeakerId, "Speaker ID Must not be null.");

			final Speaker speaker;

			if (Long.valueOf(-1L).equals(existingSpeakerId)) {
				LOGGER.info(String.format(
					"Adding new speaker for CFP Submission ID '%s'", cfpSubmissionId));
				speaker = new Speaker();
				speaker.setFirstName(cfpSubmissionSpeaker.getFirstName());
				speaker.setLastName(cfpSubmissionSpeaker.getLastName());
			}
			else {
				LOGGER.info(String.format(
						"Reusing existing speaker id '%s' "
						+ "for CFP Submission ID '%s'", existingSpeakerId, cfpSubmissionId));

				speaker = businessService.getSpeaker(existingSpeakerId);

				LOGGER.info(String.format(
					"Updating existing speaker '%s'.", speaker.getFirstLastName()));
			}

			speaker.setBio(cfpSubmissionSpeaker.getBio());
			speaker.setGithubId(cfpSubmissionSpeaker.getGithubId());
			speaker.setGooglePlusId(cfpSubmissionSpeaker.getGooglePlusId());
			speaker.setLanyrdId(cfpSubmissionSpeaker.getLanyrdId());
			speaker.setLinkedInId(cfpSubmissionSpeaker.getLinkedInId());
			speaker.setTwitterId(cfpSubmissionSpeaker.getTwitterId());
			speaker.setCfpSpeakerId(cfpSubmissionSpeaker.getId());

			final Speaker updatedSpeaker = businessService.saveSpeaker(speaker);

			if (!currentEvent.hasSpeaker(updatedSpeaker.getId())) {
				LOGGER.info(String.format("Adding speaker '%s' to event '%s'",
						speaker.getFirstLastName(),
						currentEvent.getId()));
				currentEvent.getSpeakers().add(updatedSpeaker);
				businessService.saveEvent(currentEvent);
			}

			updatedSpeakers.add(updatedSpeaker);
		}

		LOGGER.info(String.format(
				"Adding Presentation '%s'.", cfpSubmissionFromDb.getTitle()));

		final Presentation presentation = new Presentation();

		presentation.setCfpId(cfpSubmissionFromDb.getId());
		presentation.setDescription(cfpSubmissionFromDb.getDescription());
		presentation.setEvent(currentEvent);

		final Set<PresentationTag> presentationTags = businessService.processPresentationTags(
				cfpSubmissionFromDb.getTopic());

		presentation.setPresentationTags(presentationTags);
		presentation.setPresentationType(cfpSubmissionFromDb.getPresentationType());
		presentation.setSkillLevel(cfpSubmissionFromDb.getSkillLevel());
		presentation.setTitle(cfpSubmissionFromDb.getTitle());
		presentation.setSpeakers(updatedSpeakers);

		final Presentation savedPresentation = businessService.savePresentation(presentation);

		LOGGER.info(String.format(
				"Presentation added with id '%s'.", savedPresentation.getId()));

		cfpSubmissionFromDb.setStatus(CfpSubmissionStatusType.ACCEPTED);
		businessService.saveCfpSubmission(cfpSubmissionFromDb);

		//FlashMap.setSuccessMessage("The speaker was edited successfully.");
		return "redirect:/s/admin/{eventKey}/cfps";
	}
}
