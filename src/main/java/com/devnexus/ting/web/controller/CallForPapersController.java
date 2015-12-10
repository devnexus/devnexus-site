/*
 * Copyright 2002-2015 the original author or authors.
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
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devnexus.ting.config.support.CfpSettings;
import com.devnexus.ting.config.support.RecaptchaSettings;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.CfpSpeakerImage;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.CfpSubmissionSpeaker;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.PresentationType;
import com.devnexus.ting.model.SkillLevel;
import com.devnexus.ting.web.form.CfpSubmissionForm;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

/**
 * @author Gunnar Hillert
 */
@Controller
public class CallForPapersController {

	@Autowired private BusinessService businessService;
	@Autowired private Validator validator;

	@Autowired
	private RecaptchaSettings recaptchaSettings;

	@Autowired
	private CfpSettings cfpSettings;

	private static final Logger LOGGER = LoggerFactory.getLogger(CallForPapersController.class);

	private void prepareReferenceData(ModelMap model, boolean isSecure) {
		final String recaptchaPublicKey = recaptchaSettings.getPublicKey();
		final String recaptchaPrivateKey = recaptchaSettings.getPrivateKey();

		if (recaptchaSettings.isEnabled()) {
			final ReCaptcha reCaptcha;
			reCaptcha = ReCaptchaFactory.newSecureReCaptcha(recaptchaPublicKey, recaptchaPrivateKey, true);

			if(isSecure) {
				((ReCaptchaImpl) reCaptcha).setRecaptchaServer("https://www.google.com/recaptcha/api");
			} else {
				((ReCaptchaImpl) reCaptcha).setRecaptchaServer("http://www.google.com/recaptcha/api");
			}
			model.addAttribute("reCaptchaHtml", reCaptcha.createRecaptchaHtml(null, null));
		}

		model.addAttribute("reCaptchaEnabled", recaptchaSettings.isEnabled());

		final Event currentEvent = businessService.getCurrentEvent();
		model.addAttribute("currentEvent", currentEvent);

		final Set<PresentationType> presentationTypes = EnumSet.allOf(PresentationType.class);
		model.addAttribute("presentationTypes", presentationTypes);

		final Set<SkillLevel> skillLevels = EnumSet.allOf(SkillLevel.class);
		model.addAttribute("skillLevels", skillLevels);

	}

	@RequestMapping(value="/s/cfp", method=RequestMethod.GET)
	public String openAddCfp(ModelMap model, WebRequest request) {

		if (CfpSettings.CfpState.CLOSED.equals(cfpSettings.getState())) {
			return "cfp-closed";
		}

		model.addAttribute("headerTitle", "Call for Papers");
		model.addAttribute("tag", "We would love to review your	session proposals!");

		Event event = businessService.getCurrentEvent();
		CfpSubmission cfpSubmission = new CfpSubmission();
		cfpSubmission.setEvent(event);
		CfpSubmissionSpeaker speaker = new CfpSubmissionSpeaker();
		speaker.setCfpSubmission(cfpSubmission);
		cfpSubmission.getSpeakers().add(speaker);

		model.addAttribute("cfpSubmission", cfpSubmission);
		prepareReferenceData(model, request.isSecure());

		return "cfp";

	}

	@RequestMapping(value="/s/cfp", method=RequestMethod.POST)
	public String addCfp(@Valid @ModelAttribute("cfpSubmission") CfpSubmissionForm cfpSubmission,
			BindingResult bindingResult,
			ModelMap model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		if (CfpSettings.CfpState.CLOSED.equals(cfpSettings.getState())) {
			return "redirect:/s/index";
		}

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/index";
		}

		if (request.getParameter("addSpeaker") != null) {
			prepareReferenceData(model, request.isSecure());
			CfpSubmissionSpeaker speaker = new CfpSubmissionSpeaker();
			speaker.setCfpSubmission(cfpSubmission);
			cfpSubmission.getSpeakers().add(speaker);
			return "/cfp";
		}

		if (request.getParameter("removeSpeaker") != null) {
			prepareReferenceData(model, request.isSecure());
			cfpSubmission.getSpeakers().remove(Integer.valueOf(request.getParameter("removeSpeaker")).intValue());
			return "/cfp";
		}

		if (recaptchaSettings.isEnabled()) {
			String remoteAddr = request.getRemoteAddr();
			ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
			reCaptcha.setPrivateKey(recaptchaSettings.getPrivateKey());

			String challenge = request.getParameter("recaptcha_challenge_field");
			String uresponse = request.getParameter("recaptcha_response_field");
			ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

			if (!reCaptchaResponse.isValid()) {
				ObjectError error = new ObjectError("error","Please insert the correct CAPTCHA.");
				bindingResult.addError(error);
				prepareReferenceData(model, request.isSecure());
				return "/cfp";
			}
		}

		if (bindingResult.hasErrors()) {
			prepareReferenceData(model, request.isSecure());
			return "/cfp";
		}

		final Event eventFromDb = businessService.getCurrentEvent();
		final CfpSubmission cfpSubmissionToSave = new CfpSubmission();

		if (cfpSubmission.getSpeakers().size() < 1) {
			ObjectError error = new ObjectError("error","Please submit at least 1 speaker.");
			bindingResult.addError(error);
			prepareReferenceData(model, request.isSecure());
			return "/cfp";
		}

		if (cfpSubmission.getPictureFiles().size() != cfpSubmission.getSpeakers().size()) {
			ObjectError error = new ObjectError("error","Please submit a picture for each speaker.");
			bindingResult.addError(error);
			prepareReferenceData(model, request.isSecure());
			return "/cfp";
		}

		cfpSubmissionToSave.setDescription(cfpSubmission.getDescription());
		cfpSubmissionToSave.setEvent(eventFromDb);

		cfpSubmissionToSave.setPresentationType(cfpSubmission.getPresentationType());
		cfpSubmissionToSave.setSessionRecordingApproved(cfpSubmission.isSessionRecordingApproved());
		cfpSubmissionToSave.setSkillLevel(cfpSubmission.getSkillLevel());
		cfpSubmissionToSave.setSlotPreference(cfpSubmission.getSlotPreference());
		cfpSubmissionToSave.setTitle(cfpSubmission.getTitle());
		cfpSubmissionToSave.setTopic(cfpSubmission.getTopic());

		int index = 0;
		for (CfpSubmissionSpeaker cfpSubmissionSpeaker : cfpSubmission.getSpeakers()) {

			final MultipartFile picture = cfpSubmission.getPictureFiles().get(index);
			byte[] pictureData = null;
			CfpSpeakerImage cfpSpeakerImage = null;

			try {
				pictureData = picture.getBytes();
			} catch (IOException e) {
				LOGGER.error("Error processing Image.", e);
				bindingResult.addError(new FieldError("cfpSubmission", "pictureFile", "Error processing Image."));
				prepareReferenceData(model, request.isSecure());
				return "/cfp";
			}

			if (pictureData != null && picture.getSize() > 0) {
				cfpSpeakerImage = new CfpSpeakerImage();

				cfpSpeakerImage.setFileData(pictureData);
				cfpSpeakerImage.setFileModified(new Date());
				cfpSpeakerImage.setFileSize(picture.getSize());
				cfpSpeakerImage.setName(picture.getOriginalFilename());
				cfpSpeakerImage.setType(picture.getContentType());
			}

			index++;

			final CfpSubmissionSpeaker cfpSubmissionSpeakerToSave = new CfpSubmissionSpeaker();

			cfpSubmissionSpeakerToSave.setEmail(cfpSubmissionSpeaker.getEmail());
			cfpSubmissionSpeakerToSave.setBio(cfpSubmissionSpeaker.getBio());
			cfpSubmissionSpeakerToSave.setFirstName(cfpSubmissionSpeaker.getFirstName());
			cfpSubmissionSpeakerToSave.setGithubId(cfpSubmissionSpeaker.getGithubId());
			cfpSubmissionSpeakerToSave.setGooglePlusId(cfpSubmissionSpeaker.getGooglePlusId());
			cfpSubmissionSpeakerToSave.setLanyrdId(cfpSubmissionSpeaker.getLanyrdId());
			cfpSubmissionSpeakerToSave.setLastName(cfpSubmissionSpeaker.getLastName());
			cfpSubmissionSpeakerToSave.setLinkedInId(cfpSubmissionSpeaker.getLinkedInId());
			cfpSubmissionSpeakerToSave.setTwitterId(cfpSubmissionSpeaker.getTwitterId());
			cfpSubmissionSpeakerToSave.setLocation(cfpSubmissionSpeaker.getLocation());
			cfpSubmissionSpeakerToSave.setMustReimburseTravelCost(cfpSubmissionSpeaker.isMustReimburseTravelCost());
			cfpSubmissionSpeakerToSave.setTshirtSize(cfpSubmissionSpeaker.getTshirtSize());
			cfpSubmissionSpeakerToSave.setPhone(cfpSubmissionSpeaker.getPhone());
			cfpSubmissionSpeakerToSave.setCfpSubmission(cfpSubmissionToSave);
			cfpSubmissionSpeakerToSave.setCfpSpeakerImage(cfpSpeakerImage);

			cfpSubmissionToSave.getSpeakers().add(cfpSubmissionSpeakerToSave);

		}

		LOGGER.info(cfpSubmissionToSave.toString());
		businessService.saveAndNotifyCfpSubmission(cfpSubmissionToSave);

		return "redirect:/s/add-cfp-success";
	}

	@RequestMapping(value="/s/add-cfp-success", method=RequestMethod.GET)
	public String addCfpSuccess(ModelMap model) {

		model.addAttribute("headerTitle", "Call for Papers");
		model.addAttribute("tag", "Thank you for your interest in presenting at DevNexus!");

		return "cfp-add-success";
	}

}
