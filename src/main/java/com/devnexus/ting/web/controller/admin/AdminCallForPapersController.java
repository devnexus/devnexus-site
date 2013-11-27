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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devnexus.ting.core.model.CfpSubmission;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.service.BusinessService;

/**
 * @author Gunnar Hillert
 */
@Controller
public class AdminCallForPapersController {

	@Autowired private BusinessService businessService;
	@Autowired private Validator validator;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminCallForPapersController.class);

//	@RequestMapping(value="/admin/evaluations", method=RequestMethod.GET)
//	public String showEvaluations(final SitePreference sitePreference, ModelMap model) {
//
//		final List<Evaluation> evaluations = businessService.getEvaluationsForCurrentEvent();
//
//		final EvaluationList evaluationList = new EvaluationList(evaluations);
//		model.addAttribute("evaluationList", evaluationList);
//
//		if (sitePreference.isMobile()) {
//			return "admin/evaluations-mobile";
//		}
//
//		return "admin/evaluations";
//	}

	@RequestMapping(value="/admin/cfps", method=RequestMethod.GET)
	public String viewCfps(final SitePreference sitePreference, ModelMap model) {

		Event currentEvent = businessService.getCurrentEvent();
		List<CfpSubmission> cfps = businessService.getCfpSubmissions(currentEvent.getId());
		model.addAttribute("cfps", cfps);
		return "admin/cfps";
	}

}
