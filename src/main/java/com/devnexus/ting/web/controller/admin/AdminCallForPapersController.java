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

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.CfpSubmissionList;
import com.devnexus.ting.model.CfpSubmissionSpeaker;
import com.devnexus.ting.model.CfpSubmissionStatusType;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.PresentationType;
import com.devnexus.ting.model.SkillLevel;
import com.devnexus.ting.model.Speaker;
import com.devnexus.ting.web.form.ManageCfpsForm;

/**
 * @author Gunnar Hillert
 */
@Controller
public class AdminCallForPapersController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminCallForPapersController.class);

	@Autowired private BusinessService businessService;
	@Autowired private Validator validator;

	private void prepareReferenceData(ModelMap model) {

		final Event currentEvent = businessService.getCurrentEvent();
		model.addAttribute("currentEvent", currentEvent);

		final Set<PresentationType> presentationTypes = EnumSet.allOf(PresentationType.class);
		model.addAttribute("presentationTypes", presentationTypes);

		final Set<SkillLevel> skillLevels = EnumSet.allOf(SkillLevel.class);
		model.addAttribute("skillLevels", skillLevels);

		final Set<CfpSubmissionStatusType> cfpSubmissionStatusTypes = EnumSet.allOf(CfpSubmissionStatusType.class);
		model.addAttribute("cfpSubmissionStatusTypes", cfpSubmissionStatusTypes);

		final List<Speaker> allSpeakers = businessService.getAllSpeakersOrderedByName();
		model.addAttribute("allSpeakers", allSpeakers);
	}

	@RequestMapping(value="/s/admin/{eventKey}/cfps", method=RequestMethod.GET)
	public String viewCfps(@PathVariable("eventKey") String eventKey, ModelMap model) {

		Event event = businessService.getEventByEventKey(eventKey);
		List<CfpSubmission> cfpSubmissions = businessService.getCfpSubmissions(event.getId());

		CfpSubmissionList cfpSubmissionList = new CfpSubmissionList(cfpSubmissions);
		model.addAttribute("cfpSubmissionList", cfpSubmissionList);
		model.addAttribute("event", event);
		model.addAttribute("manageCfpsForm", new ManageCfpsForm());
		return "admin/manage-cfps";
	}

	@RequestMapping(value="/s/admin/{eventKey}/cfps", method=RequestMethod.POST)
	public String bulkProcessCfps(@PathVariable("eventKey") String eventKey, ManageCfpsForm manageCfpsForm) {

		for (Long cfpId : manageCfpsForm.getCfpIds()) {
			CfpSubmission cfpSubmission = businessService.getCfpSubmission(cfpId);
			cfpSubmission.setStatus(CfpSubmissionStatusType.REJECTED);
			businessService.saveCfpSubmission(cfpSubmission);
		}

		return "redirect:cfps";
	}

	@RequestMapping(value="/s/admin/{eventKey}/cfps/{cfpId}", method=RequestMethod.GET)
	public String prepareEditCfp(@PathVariable("eventKey") String eventKey,
	                             @PathVariable("cfpId") Long cfpId, ModelMap model) {
		Event event = businessService.getEventByEventKey(eventKey);
		prepareReferenceData(model);
		final CfpSubmission cfpSubmission = businessService.getCfpSubmission(cfpId);

		model.addAttribute("cfpSubmission", cfpSubmission);
		model.addAttribute("event", event);

		return "/admin/edit-cfp";
	}

	@RequestMapping(value="/s/admin/{eventKey}/cfps/speaker-image/{speakerId}", method=RequestMethod.GET)
	public void getOrganizerPicture(@PathVariable("speakerId") Long speakerId, HttpServletResponse response) {

		final CfpSubmissionSpeaker cfpSubmissionSpeaker = businessService.getCfpSubmissionSpeakerWithPicture(speakerId);

		final byte[] organizerPicture;

		if (cfpSubmissionSpeaker==null || cfpSubmissionSpeaker.getCfpSpeakerImage() == null) {
			organizerPicture = SystemInformationUtils.getOrganizerImage(null);
			response.setContentType("image/jpg");
		} else {
			organizerPicture = cfpSubmissionSpeaker.getCfpSpeakerImage().getFileData();
			response.setContentType(cfpSubmissionSpeaker.getCfpSpeakerImage().getType());
		}

		try {
			org.apache.commons.io.IOUtils.write(organizerPicture, response.getOutputStream());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@RequestMapping(value="/s/admin/{eventKey}/cfps/{cfpId}", method=RequestMethod.POST)
	public String editCfp(@PathVariable("eventKey") String eventKey,
	                      @PathVariable("cfpId") Long cfpId,
	                      CfpSubmission cfpSubmission,
	                      BindingResult result, HttpServletRequest request) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/{eventKey}/cfps#cfp_{cfpId}";
		}
		if (request.getParameter("delete") != null) {
			businessService.deleteCfpSubmission(cfpSubmission.getId());
			return "redirect:/s/admin/{eventKey}/cfps";
		}

		if (result.hasErrors()) {
			return "/s/admin/edit-cfp";
		}

		final CfpSubmission cfpSubmissionFromDb = businessService.getCfpSubmission(cfpId);
		cfpSubmissionFromDb.setDescription(cfpSubmission.getDescription());
		cfpSubmissionFromDb.setStatus(cfpSubmission.getStatus());
		cfpSubmissionFromDb.setPresentationType(cfpSubmission.getPresentationType());
		cfpSubmissionFromDb.setSessionRecordingApproved(cfpSubmission.isSessionRecordingApproved());
		cfpSubmissionFromDb.setSkillLevel(cfpSubmission.getSkillLevel());
		cfpSubmissionFromDb.setSlotPreference(cfpSubmission.getSlotPreference());
		cfpSubmissionFromDb.setTitle(cfpSubmission.getTitle());
		cfpSubmissionFromDb.setTopic(cfpSubmission.getTopic());


		for (CfpSubmissionSpeaker speaker : cfpSubmission.getCfpSubmissionSpeakers()) {
			CfpSubmissionSpeaker speakerFromDb = cfpSubmissionFromDb.getSpeakerById(speaker.getId());
			if (speakerFromDb != null) {
				speakerFromDb.setBio(speaker.getBio());
				speakerFromDb.setEmail(speaker.getEmail());
				speakerFromDb.setFirstName(speaker.getFirstName());
				speakerFromDb.setGithubId(speaker.getGithubId());
				speakerFromDb.setGooglePlusId(speaker.getGooglePlusId());
				speakerFromDb.setLanyrdId(speaker.getLanyrdId());
				speakerFromDb.setLastName(speaker.getLastName());
				speakerFromDb.setLinkedInId(speaker.getLinkedInId());
				speakerFromDb.setLocation(speaker.getLocation());
				speakerFromDb.setMustReimburseTravelCost(speaker.isMustReimburseTravelCost());
				speakerFromDb.setPhone(speaker.getPhone());
				speakerFromDb.setTshirtSize(speaker.getTshirtSize());
				speakerFromDb.setTwitterId(speaker.getTwitterId());
			}
		}

		businessService.saveCfpSubmission(cfpSubmissionFromDb);

		//FlashMap.setSuccessMessage("The speaker was edited successfully.");
		return "redirect:/s/admin/{eventKey}/cfps";
	}
}
