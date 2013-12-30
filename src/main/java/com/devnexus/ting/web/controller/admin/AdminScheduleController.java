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
package com.devnexus.ting.web.controller.admin;

import java.util.List;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.PresentationList;
import com.devnexus.ting.core.model.ScheduleItemList;
import com.devnexus.ting.core.service.BusinessService;

/**
 * @author Gunnar Hillert
 */
@Controller
public class AdminScheduleController {

	@Autowired private BusinessService businessService;
	@Autowired private Validator validator;

	private void prepareReferenceData(ModelMap model) {

//TODO
//		final Event currentEvent = businessService.getCurrentEvent();
//		model.addAttribute("currentEvent", currentEvent);
//
//		final Set<PresentationType> presentationTypes = EnumSet.allOf(PresentationType.class);
//		model.addAttribute("presentationTypes", presentationTypes);
//
//		final Set<SkillLevel> skillLevels = EnumSet.allOf(SkillLevel.class);
//		model.addAttribute("skillLevels", skillLevels);
//
//		final Set<CfpSubmissionStatusType> cfpSubmissionStatusTypes = EnumSet.allOf(CfpSubmissionStatusType.class);
//		model.addAttribute("cfpSubmissionStatusTypes", cfpSubmissionStatusTypes);

	}

	@RequestMapping(value="/admin/manage-schedule", method=RequestMethod.GET)
	public String prepareEditCfp(@RequestParam("eventId") Long eventId, ModelMap model) {

		prepareReferenceData(model);
		final ScheduleItemList scheduleItemList = businessService.getScheduleForEvent(eventId);

		final List<Presentation> presentations = businessService.getPresentationsForEvent(eventId);
		final PresentationList presentationList = new PresentationList();
		presentationList.setPresentations(presentations);

		model.addAttribute("scheduleItemList", scheduleItemList);
		model.addAttribute("presentationList", presentationList);

		return "/admin/manage-schedule";
	}

}
