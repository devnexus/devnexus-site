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

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devnexus.ting.core.model.CfpSubmission;
import com.devnexus.ting.core.model.CfpSubmissionList;
import com.devnexus.ting.core.model.CfpSubmissionSpeaker;
import com.devnexus.ting.core.model.CfpSubmissionStatusType;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.PresentationType;
import com.devnexus.ting.core.model.SkillLevel;
import com.devnexus.ting.core.service.BusinessService;

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

	}

	@RequestMapping(value="/admin/cfps", method=RequestMethod.GET)
	public String viewCfps(ModelMap model) {

		Event currentEvent = businessService.getCurrentEvent();
		List<CfpSubmission> cfpSubmissions = businessService.getCfpSubmissions(currentEvent.getId());

		migrateCfpSubmissions(cfpSubmissions);

		CfpSubmissionList cfpSubmissionList = new CfpSubmissionList(cfpSubmissions);
		model.addAttribute("cfpSubmissionList", cfpSubmissionList);
		return "admin/cfps";
	}

	void migrateCfpSubmissions(List<CfpSubmission> cfpSubmissions) {

		int numberOfCfpsUpdated = 0;
		for (CfpSubmission cfpSubmission : cfpSubmissions) {
			boolean changed = false;

			if (StringUtils.hasText(cfpSubmission.getFirstName())
				|| StringUtils.hasText(cfpSubmission.getEmail())
				&& !cfpSubmission.getSpeakers().isEmpty()) {
				CfpSubmissionSpeaker cfpSubmissionSpeaker = new CfpSubmissionSpeaker();
				cfpSubmissionSpeaker.setBio(cfpSubmission.getBio());
				cfpSubmission.setBio(null);

				cfpSubmissionSpeaker.setCfpSubmission(cfpSubmission);

				cfpSubmissionSpeaker.setEmail(cfpSubmission.getEmail());
				cfpSubmission.setEmail(null);

				cfpSubmissionSpeaker.setFirstName(cfpSubmission.getFirstName());
				cfpSubmission.setFirstName(null);

				cfpSubmissionSpeaker.setGithubId(cfpSubmission.getGithubId());
				cfpSubmission.setGithubId(null);

				cfpSubmissionSpeaker.setGooglePlusId(cfpSubmission.getGooglePlusId());
				cfpSubmission.setGooglePlusId(null);

				cfpSubmissionSpeaker.setLanyrdId(cfpSubmission.getLanyrdId());
				cfpSubmission.setLanyrdId(null);

				cfpSubmissionSpeaker.setLastName(cfpSubmission.getLastName());
				cfpSubmission.setLastName(null);

				cfpSubmissionSpeaker.setLinkedInId(cfpSubmission.getLinkedInId());
				cfpSubmission.setLinkedInId(null);

				if (cfpSubmission.getLocation() != null) {
					cfpSubmissionSpeaker.setLocation(cfpSubmission.getLocation());
				}
				else {
					cfpSubmissionSpeaker.setLocation("N/A");
				}

				cfpSubmission.setLocation(null);

				cfpSubmissionSpeaker.setMustReimburseTravelCost(cfpSubmission.isMustReimburseTravelCost());
				cfpSubmission.setMustReimburseTravelCost(false);

				cfpSubmissionSpeaker.setPhone(cfpSubmission.getPhone());
				cfpSubmission.setPhone(null);

				cfpSubmissionSpeaker.setPicture(cfpSubmission.getPicture());
				cfpSubmission.setPicture(null);

				cfpSubmissionSpeaker.setTshirtSize(cfpSubmission.getTshirtSize());
				cfpSubmission.setTshirtSize(null);

				cfpSubmissionSpeaker.setTwitterId(cfpSubmission.getTwitterId());
				cfpSubmission.setTwitterId(null);

				cfpSubmission.getSpeakers().add(cfpSubmissionSpeaker);
				changed = true;
				numberOfCfpsUpdated++;
			}

			if (changed) {
				businessService.saveCfpSubmission(cfpSubmission);
			}
		}
		LOGGER.info("Updated CFP Submissions: " + numberOfCfpsUpdated);
	}

	@RequestMapping(value="/admin/cfps/{cfpId}", method=RequestMethod.GET)
	public String prepareEditCfp(@PathVariable("cfpId") Long cfpId, ModelMap model) {

		prepareReferenceData(model);
		final CfpSubmission cfpSubmission = businessService.getCfpSubmission(cfpId);

		model.addAttribute("cfpSubmission", cfpSubmission);

		return "/admin/edit-cfp";
	}

	@RequestMapping(value="/admin/cfps/{cfpId}", method=RequestMethod.POST)
	public String editCfp(@PathVariable("cfpId") Long cfpId,
							  @Valid CfpSubmission cfpSubmission,
							  BindingResult result, HttpServletRequest request) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/index";
		}

		if (result.hasErrors()) {
			return "/admin/edit-cfp";
		}

		final CfpSubmission cfpSubmissionFromDb = businessService.getCfpSubmission(cfpId);
		cfpSubmissionFromDb.setStatus(cfpSubmission.getStatus());

		for (CfpSubmissionSpeaker speaker : cfpSubmission.getSpeakers()) {
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
		return "redirect:/s/admin/cfps";
	}
}
