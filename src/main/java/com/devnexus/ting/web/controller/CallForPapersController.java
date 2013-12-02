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
import java.io.InputStream;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.model.CfpSubmission;
import com.devnexus.ting.core.model.CfpSubmissionList;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.PresentationType;
import com.devnexus.ting.core.model.SkillLevel;
import com.devnexus.ting.core.service.BusinessService;

/**
 * @author Gunnar Hillert
 */
@Controller
public class CallForPapersController {

	@Autowired private BusinessService businessService;
	@Autowired private Validator validator;

	@Autowired private ConfigurableEnvironment environment;

	private static final Logger LOGGER = LoggerFactory.getLogger(CallForPapersController.class);

	private MessageSourceAccessor messages;

	@Autowired
	public void setMessages(MessageSource messageSource) {
		messages = new MessageSourceAccessor(messageSource);
	}

	private void prepareReferenceData(ModelMap model) {
		final String reCaptchaEnabled = environment.getProperty("recaptcha.enabled");
		final String recaptchaPublicKey = environment.getProperty("recaptcha.publicKey");
		final String recaptchaPrivateKey = environment.getProperty("recaptcha.privateKey");

		if (Boolean.valueOf(reCaptchaEnabled)) {
			ReCaptcha reCaptcha = ReCaptchaFactory.newReCaptcha(recaptchaPublicKey, recaptchaPrivateKey, false);
			model.addAttribute("reCaptchaHtml", reCaptcha.createRecaptchaHtml(null, null));
		}

		model.addAttribute("reCaptchaEnabled", reCaptchaEnabled);

		final Event currentEvent = businessService.getCurrentEvent();
		model.addAttribute("currentEvent", currentEvent);

		final Set<PresentationType> presentationTypes = EnumSet.allOf(PresentationType.class);
		model.addAttribute("presentationTypes", presentationTypes);

		final Set<SkillLevel> skillLevels = EnumSet.allOf(SkillLevel.class);
		model.addAttribute("skillLevels", skillLevels);

	}

	@RequestMapping(value="/cfp", method=RequestMethod.GET)
	public String openAddCfp(final SitePreference sitePreference, ModelMap model) {

		model.addAttribute("headerTitle", "Call for Papers");
		model.addAttribute("tag", "We would love to review your	session proposals!");

		Event event = businessService.getCurrentEvent();
		CfpSubmission cfpSubmission = new CfpSubmission();
		cfpSubmission.setEvent(event);

		model.addAttribute(cfpSubmission);
		prepareReferenceData(model);

		return "cfp";
	}

	@RequestMapping(value="/cfp", method=RequestMethod.POST)
	public String addCfp(@Valid CfpSubmission cfpSubmission,
			BindingResult bindingResult,
			ModelMap model,
			@RequestParam MultipartFile pictureFile,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		String baseSiteUrl = (String) request.getAttribute("baseSiteUrl");

		String s = messages.getMessage("errors.required");

		if (request.getParameter("cancel") != null) {
			return "redirect:" + baseSiteUrl + "/index";
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
				return "/cfp";
			}
		}

		InputStream pictureInputStream = null;

		try {
			pictureInputStream = pictureFile.getInputStream();
		} catch (IOException e) {
			LOGGER.error("Error processing Image.", e);
			bindingResult.addError(new FieldError("cfpSubmission", "pictureFile", "Error processing Image."));
		}

		if (bindingResult.hasErrors()) {
			prepareReferenceData(model);
			return "/cfp";
		}

		if (pictureInputStream != null && pictureFile.getSize() > 0) {
			SystemInformationUtils.setSpeakerImage("CFP_" + cfpSubmission.getFirstName()
					+ "_" + cfpSubmission.getLastName() + "_" + pictureFile.getOriginalFilename(), pictureInputStream);
		}

		final Event eventFromDb = businessService.getCurrentEvent();

		final CfpSubmission cfpSubmissionToSave = new CfpSubmission();
		cfpSubmissionToSave.setBio(cfpSubmission.getBio());
		cfpSubmissionToSave.setCreatedDate(new Date());
		cfpSubmissionToSave.setDescription(cfpSubmission.getDescription());
		cfpSubmissionToSave.setEmail(cfpSubmission.getEmail());
		cfpSubmissionToSave.setEvent(eventFromDb);
		cfpSubmissionToSave.setFirstName(cfpSubmission.getFirstName());
		cfpSubmissionToSave.setGooglePlusId(cfpSubmission.getGooglePlusId());
		cfpSubmissionToSave.setLastName(cfpSubmission.getLastName());
		cfpSubmissionToSave.setLinkedInId(cfpSubmission.getLinkedInId());
		cfpSubmissionToSave.setPhone(cfpSubmission.getPhone());
		cfpSubmissionToSave.setPresentationType(cfpSubmission.getPresentationType());
		cfpSubmissionToSave.setSessionRecordingApproved(cfpSubmission.isSessionRecordingApproved());
		cfpSubmissionToSave.setSkillLevel(cfpSubmission.getSkillLevel());
		cfpSubmissionToSave.setSlotPreference(cfpSubmission.getSlotPreference());
		cfpSubmissionToSave.setTitle(cfpSubmission.getTitle());
		cfpSubmissionToSave.setTopic(cfpSubmission.getTopic());
		cfpSubmissionToSave.setTshirtSize(cfpSubmission.getTshirtSize());
		cfpSubmissionToSave.setTwitterId(cfpSubmission.getTwitterId());

		LOGGER.info(cfpSubmission.toString());
		businessService.saveAndNotifyCfpSubmission(cfpSubmissionToSave);

		return "redirect:" + baseSiteUrl + "/add-cfp-success";
	}

	@RequestMapping(value="/add-cfp-success", method=RequestMethod.GET)
	public String addCfpSuccess(final SitePreference sitePreference, ModelMap model) {

		model.addAttribute("headerTitle", "Call for Papers");
		model.addAttribute("tag", "Thank you for your interest in presenting at DevNexus!");

		if (sitePreference.isMobile()) {
			return "add-cfp-success-mobile";
		}

		return "cfp-add-success";
	}

}
