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

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devnexus.ting.core.model.Evaluation;
import com.devnexus.ting.core.model.EvaluationList;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.service.BusinessService;

/**
 * @author Gunnar Hillert
 */
@Controller
public class EvaluationController {

	@Autowired private BusinessService businessService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EvaluationController.class);

	@RequestMapping(value="/admin/evaluations", method=RequestMethod.GET)
	public String showEvaluations(final SitePreference sitePreference, ModelMap model) {

		final List<Evaluation> evaluations = businessService.getEvaluationsForCurrentEvent();

		final EvaluationList evaluationList = new EvaluationList(evaluations);
		model.addAttribute("evaluationList", evaluationList);


		return "admin/evaluations";
	}

	@RequestMapping(value="/evaluations/add", method=RequestMethod.GET)
	public String openAddEvaluations(final SitePreference sitePreference, ModelMap model) {

		model.addAttribute("evaluation", new Evaluation());

		return "add-evaluation";
	}

	@RequestMapping(value="/evaluations/add", method=RequestMethod.POST)
	public String editEvent(Evaluation evaluation,
			BindingResult bindingResult,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		String baseSiteUrl = (String) request.getAttribute("baseSiteUrl");
		if (request.getParameter("cancel") != null) {
			return "redirect:/s/index";
		}

		if (bindingResult.hasErrors()) {
			return "/evaluations/add";
		}

		final Event eventFromDb = businessService.getCurrentEvent();

		final Evaluation evaluationToSave = new Evaluation();
		evaluationToSave.setComment(evaluation.getComment());
		evaluationToSave.setEvent(eventFromDb);
		evaluationToSave.setCreatedDate(new Date());
		evaluationToSave.setRating(evaluation.getRating());

		businessService.saveEvaluation(evaluationToSave);

		return "redirect:" + baseSiteUrl + "/add-evaluation-success";
	}

	@RequestMapping(value="/add-evaluation-success", method=RequestMethod.GET)
	public String addEvaluationSuccess(final SitePreference sitePreference, ModelMap model) {

		return "add-evaluation-success";
	}
}
