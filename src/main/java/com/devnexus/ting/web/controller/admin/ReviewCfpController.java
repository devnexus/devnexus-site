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
package com.devnexus.ting.web.controller.admin;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.core.service.UserService;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.CfpSubmissionReview;
import com.devnexus.ting.model.CfpSubmissionStatusType;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.User;
import com.devnexus.ting.security.SecurityFacade;

/**
 * Handles reviews of CFPs.
 *
 * @author Gunnar Hillert
 */
@Controller
public class ReviewCfpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReviewCfpController.class);

	@Autowired private BusinessService businessService;
	@Autowired private SecurityFacade securityFacade;
	@Autowired private UserService userService;

	private void prepareReferenceData(ModelMap model) {

		final Event currentEvent = businessService.getCurrentEvent();
		model.addAttribute("currentEvent", currentEvent);

		final Set<CfpSubmissionStatusType> cfpSubmissionStatusTypes = EnumSet.allOf(CfpSubmissionStatusType.class);
		model.addAttribute("cfpSubmissionStatusTypes", cfpSubmissionStatusTypes);

	}

	@RequestMapping(value="/s/admin/{eventKey}/cfps/{cfpId}/review", method=RequestMethod.GET)
	public String prepareAcceptCfp(@PathVariable("cfpId") Long cfpId, ModelMap model) {
		prepareReferenceData(model);
		final CfpSubmission cfpSubmission = businessService.getCfpSubmission(cfpId);
		final User user = (User) userService.loadUserByUsername(securityFacade.getUsername());

		CfpSubmissionReview cfpSubmissionReview = null;
		boolean isUpdate = false;

		for (CfpSubmissionReview existingCfpSubmissionReview : cfpSubmission.getCfpSubmissionReviews()) {
			if (existingCfpSubmissionReview.getCreatedByUser().getId().equals(user.getId())) {
				cfpSubmissionReview = existingCfpSubmissionReview;
				isUpdate = true;
			}
		}

		if (cfpSubmissionReview == null) {
			cfpSubmissionReview = new CfpSubmissionReview();
			cfpSubmissionReview.setCreatedByUser(user);
		}

		cfpSubmissionReview.setCfpSubmission(cfpSubmission);
		model.addAttribute("cfpSubmissionReview", cfpSubmissionReview);
		model.addAttribute("isUpdate", isUpdate);
		return "/admin/review-cfp";
	}

	@RequestMapping(value="/s/admin/{eventKey}/cfps/{cfpId}/review", method=RequestMethod.POST)
	public String acceptCfp(@PathVariable("eventKey") String eventKey,
	                        @PathVariable("cfpId") Long cfpId,
	                        CfpSubmissionReview cfpSubmissionReview,
	                        RedirectAttributes redirectAttributes,
	                        BindingResult result, HttpServletRequest request) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/{eventKey}/cfps#cfp_{cfpId}";
		}

		if (result.hasErrors()) {
			return "/admin/review-cfp";
		}

		final Long cfpSubmissionId = cfpSubmissionReview.getCfpSubmission().getId();
		final CfpSubmission cfpSubmissionFromDb = businessService.getCfpSubmission(cfpSubmissionId);
		final User user = (User) userService.loadUserByUsername(securityFacade.getUsername());

		CfpSubmissionReview cfpSubmissionReviewToSave = null;

		boolean isUpdate = false;

		for (CfpSubmissionReview existingCfpSubmissionReview : cfpSubmissionFromDb.getCfpSubmissionReviews()) {
			if (existingCfpSubmissionReview.getCreatedByUser().getId().equals(user.getId())) {
				cfpSubmissionReviewToSave = existingCfpSubmissionReview;
				isUpdate = true;
			}
		}

		if (cfpSubmissionReviewToSave == null) {
			cfpSubmissionReviewToSave = new CfpSubmissionReview();
		}

		cfpSubmissionReviewToSave.setComment(cfpSubmissionReview.getComment());
		cfpSubmissionReviewToSave.setCreatedDate(new Date());
		cfpSubmissionReviewToSave.setCfpSubmission(cfpSubmissionFromDb);
		cfpSubmissionReviewToSave.setRating(cfpSubmissionReview.getRating());
		cfpSubmissionReviewToSave.setCreatedByUser(user);
		cfpSubmissionFromDb.getCfpSubmissionReviews().add(cfpSubmissionReviewToSave);
		businessService.saveCfpSubmission(cfpSubmissionFromDb);

		LOGGER.info("Setting up CFP Submission " + cfpSubmissionId);

		final String successMessage = String.format("Your review for '%s' was %s successfully.",
				cfpSubmissionFromDb.getTitle(), isUpdate ? "updated" : "added");

		redirectAttributes.addFlashAttribute("successMessage", successMessage);

		return "redirect:/s/admin/{eventKey}/cfps";
	}

}
