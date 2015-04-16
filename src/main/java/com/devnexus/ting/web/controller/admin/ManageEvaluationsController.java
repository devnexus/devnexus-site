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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.Evaluation;
import com.devnexus.ting.model.EvaluationList;
import com.devnexus.ting.model.Event;

/**
 * @author Gunnar Hillert
 */
@Controller
@RequestMapping("/admin/{eventKey}/evaluations")
public class ManageEvaluationsController {

	@Autowired private BusinessService businessService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ManageEvaluationsController.class);

	@RequestMapping(method=RequestMethod.GET)
	public String showEvaluationsForEvent(ModelMap model, @PathVariable("eventKey") String eventKey) {
		final Event event = businessService.getEventByEventKey(eventKey);
		final List<Evaluation> evaluations = businessService.getEvaluationsForEvent(event.getId());
		final EvaluationList evaluationList = new EvaluationList(evaluations);
		model.addAttribute("evaluationList", evaluationList);
		return "admin/manage-evaluations";
	}

	@RequestMapping(value="/admin/evaluations/{evaluationId}", method=RequestMethod.POST)
	@ResponseBody
	public void removeEvaluation(@PathVariable("evaluationId") Long evaluationId, ModelMap model) {
		businessService.removeEvaluation(evaluationId);
		LOGGER.info("Evaluation {} was removed.", evaluationId);
	}

}
