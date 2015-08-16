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

import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.Evaluation;
import com.devnexus.ting.model.Event;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

/**
 * @author Gunnar Hillert
 */
@Controller
public class EvaluationController {

	@Autowired private BusinessService businessService;
	@Autowired private ConfigurableEnvironment environment;

	private void prepareReferenceData(ModelMap model) {
		final String reCaptchaEnabled = environment.getProperty("recaptcha.enabled");
		final String recaptchaPublicKey = environment.getProperty("recaptcha.publicKey");
		final String recaptchaPrivateKey = environment.getProperty("recaptcha.privateKey");

		if (Boolean.valueOf(reCaptchaEnabled)) {
			ReCaptcha reCaptcha = ReCaptchaFactory.newReCaptcha(recaptchaPublicKey, recaptchaPrivateKey, false);
			Properties recaptchaProperties = new Properties();
			//recaptchaProperties.put("theme", "clean");
			model.addAttribute("reCaptchaHtml", reCaptcha.createRecaptchaHtml(null, recaptchaProperties));
		}

		model.addAttribute("reCaptchaEnabled", reCaptchaEnabled);

		final Event currentEvent = businessService.getCurrentEvent();
		model.addAttribute("currentEvent", currentEvent);

	}

	@RequestMapping(value="/evaluations/add", method=RequestMethod.GET)
	public String openAddEvaluations(ModelMap model) {
		model.addAttribute("evaluation", new Evaluation());
		prepareReferenceData(model);
		return "add-evaluation";
	}

	@RequestMapping(value="/evaluations/add", method=RequestMethod.POST)
	public String editEvent(@Valid Evaluation evaluation,
			BindingResult bindingResult,
			ModelMap model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/index";
		}

		final String reCaptchaEnabled = environment.getProperty("recaptcha.enabled");
		final String recaptchaPrivateKey = environment.getProperty("recaptcha.privateKey");

		if (Boolean.valueOf(reCaptchaEnabled)) {
			String remoteAddr = request.getRemoteAddr();
			ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
			reCaptcha.setPrivateKey(recaptchaPrivateKey);

			String challenge = request.getParameter("recaptcha_challenge_field");
			String uresponse = request.getParameter("recaptcha_response_field");
			ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

			if (!reCaptchaResponse.isValid()) {
				ObjectError error = new ObjectError("error","Please insert the correct CAPTCHA.");
				bindingResult.addError(error);
				prepareReferenceData(model);
				return "add-evaluation";
			}
		}

		if (bindingResult.hasErrors()) {
			prepareReferenceData(model);
			return "add-evaluation";
		}

		final Event eventFromDb = businessService.getCurrentEvent();

		final Evaluation evaluationToSave = new Evaluation();
		evaluationToSave.setComment(evaluation.getComment());
		evaluationToSave.setEvent(eventFromDb);
		evaluationToSave.setCreatedDate(new Date());
		evaluationToSave.setRating(evaluation.getRating());

		businessService.saveEvaluation(evaluationToSave);

		return "redirect:/s/add-evaluation-success";
	}

	@RequestMapping(value="/add-evaluation-success", method=RequestMethod.GET)
	public String addEvaluationSuccess(ModelMap model) {
		return "evaluation-add-success";
	}
}
