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
package com.devnexus.ting.web.controller.cfp;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.config.support.CfpSettings;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.core.service.UserService;
import com.devnexus.ting.model.CfpSpeakerImage;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.CfpSubmissionSpeaker;
import com.devnexus.ting.model.CfpSubmissionStatusType;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.PresentationType;
import com.devnexus.ting.model.SkillLevel;
import com.devnexus.ting.model.User;
import com.devnexus.ting.security.SecurityFacade;
import com.devnexus.ting.web.form.CfpSubmissionForm;
import com.devnexus.ting.web.form.CfpSubmissionSpeakerForm;

/**
 * @author Gunnar Hillert
 */
@Controller
@RequestMapping(value="/s/cfp")
public class CallForPapersController {

	@Autowired private BusinessService businessService;
	@Autowired private UserService userService;
	@Autowired private SecurityFacade securityFacade;

	@Autowired private Validator validator;

	@Autowired
	private CfpSettings cfpSettings;

	private static final Logger LOGGER = LoggerFactory.getLogger(CallForPapersController.class);

	private void prepareReferenceData(ModelMap model) {

		final Event currentEvent = businessService.getCurrentEvent();
		model.addAttribute("currentEvent", currentEvent);

		final Set<PresentationType> presentationTypes = EnumSet.allOf(PresentationType.class);
		model.addAttribute("presentationTypes", presentationTypes);

		final Set<SkillLevel> skillLevels = EnumSet.allOf(SkillLevel.class);
		model.addAttribute("skillLevels", skillLevels);

	}

	/**
	 * The main CFP dashboard page.
	 *
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String manageCfpDashboard(ModelMap model, WebRequest request) {

		if (CfpSettings.CfpState.CLOSED.equals(cfpSettings.getState())) {
			return "cfp-closed";
		}

		final Event event = businessService.getCurrentEvent();

		final User user = (User) userService.loadUserByUsername(securityFacade.getUsername());
		final List<CfpSubmissionSpeaker> cfpSubmissionSpeakers = businessService.getCfpSubmissionSpeakersForUserAndEvent(user, event);
		model.addAttribute("cfpSubmissionSpeakers", cfpSubmissionSpeakers);

		final List<CfpSubmission> cfpSubmissions = businessService.getCfpSubmissionsForUserAndEvent(user.getId(), event.getId());
		model.addAttribute("cfpSubmissions", cfpSubmissions);

		return "cfp/index";

	}

	/**
	 * Open the Add Speaker Page.
	 *
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/speaker", method=RequestMethod.GET)
	public String openAddCfpSpeaker(ModelMap model, WebRequest request) {

		if (CfpSettings.CfpState.CLOSED.equals(cfpSettings.getState())) {
			return "cfp-closed";
		}

		final CfpSubmissionSpeaker speaker = new CfpSubmissionSpeaker();

		model.addAttribute("cfpSubmissionSpeaker", speaker);
		prepareReferenceData(model);

		return "cfp/cfp-speaker";

	}

	/**
	 * Add a new speaker.
	 *
	 * @param cfpSubmissionSpeaker
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/speaker", method=RequestMethod.POST)
	public String addCfpSpeaker(@Valid @ModelAttribute("cfpSubmissionSpeaker") CfpSubmissionSpeakerForm cfpSubmissionSpeaker,
			BindingResult bindingResult,
			ModelMap model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		if (CfpSettings.CfpState.CLOSED.equals(cfpSettings.getState())) {
			return "redirect:/s/index";
		}

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/cfp/index";
		}

		if (bindingResult.hasErrors()) {
			prepareReferenceData(model);
			return "cfp/cfp-speaker";
		}

		final Event eventFromDb = businessService.getCurrentEvent();
		final User user = (User) userService.loadUserByUsername(securityFacade.getUsername());

		final MultipartFile picture = cfpSubmissionSpeaker.getPictureFile();

		final CfpSpeakerImage cfpSpeakerImage = processPicture(picture, bindingResult);

		if (bindingResult.hasErrors()) {
			prepareReferenceData(model);
			return "cfp/cfp-speaker";
		}

		final CfpSubmissionSpeaker cfpSubmissionSpeakerToSave = new CfpSubmissionSpeaker();

		cfpSubmissionSpeakerToSave.setEmail(cfpSubmissionSpeaker.getEmail());
		cfpSubmissionSpeakerToSave.setBio(cfpSubmissionSpeaker.getBio());
		cfpSubmissionSpeakerToSave.setFirstName(cfpSubmissionSpeaker.getFirstName());
		cfpSubmissionSpeakerToSave.setGithubId(cfpSubmissionSpeaker.getGithubId());
		cfpSubmissionSpeakerToSave.setGooglePlusId(cfpSubmissionSpeaker.getGooglePlusId());
		cfpSubmissionSpeakerToSave.setLanyrdId(cfpSubmissionSpeaker.getLanyrdId());
		cfpSubmissionSpeakerToSave.setLastName(cfpSubmissionSpeaker.getLastName());
		cfpSubmissionSpeakerToSave.setPhone(cfpSubmissionSpeaker.getPhone());
		cfpSubmissionSpeakerToSave.setLinkedInId(cfpSubmissionSpeaker.getLinkedInId());
		cfpSubmissionSpeakerToSave.setTwitterId(cfpSubmissionSpeaker.getTwitterId());
		cfpSubmissionSpeakerToSave.setLocation(cfpSubmissionSpeaker.getLocation());
		cfpSubmissionSpeakerToSave.setMustReimburseTravelCost(cfpSubmissionSpeaker.isMustReimburseTravelCost());
		cfpSubmissionSpeakerToSave.setTshirtSize(cfpSubmissionSpeaker.getTshirtSize());
		cfpSubmissionSpeakerToSave.setCfpSpeakerImage(cfpSpeakerImage);
		cfpSubmissionSpeakerToSave.setEvent(eventFromDb);
		cfpSubmissionSpeakerToSave.setCreatedByUser(user);

		LOGGER.info(cfpSubmissionSpeakerToSave.toString());
		businessService.saveCfpSubmissionSpeaker(cfpSubmissionSpeakerToSave);

		redirectAttributes.addFlashAttribute("successMessage",
				String.format("The speaker '%s' was added successfully.", cfpSubmissionSpeakerToSave.getFirstLastName()));
		return "redirect:/s/cfp/index";
	}

	private CfpSpeakerImage processPicture(MultipartFile picture, BindingResult bindingResult) {
		CfpSpeakerImage cfpSpeakerImage = null;
		byte[] pictureData = null;

		try {
			pictureData = picture.getBytes();
		} catch (IOException e) {
			LOGGER.error("Error processing Image.", e);
			bindingResult.addError(new FieldError("cfpSubmissionSpeaker", "pictureFile", "Error processing Image."));
			return null;
		}

		if (pictureData != null && picture.getSize() > 0) {

			ByteArrayInputStream bais = new ByteArrayInputStream(pictureData);
			BufferedImage image;
			try {
				image = ImageIO.read(bais);
			} catch (IOException e) {
				LOGGER.error("Error processing Image.", e);
				bindingResult.addError(new FieldError("cfpSubmissionSpeaker", "pictureFile", "Error processing Image."));
				return null;
			}

			if (image == null) {
				//FIXME better way?
				final String fileSizeFormatted = FileUtils.byteCountToDisplaySize(picture.getSize());
				LOGGER.warn(String.format("Cannot parse file '%s' (Content Type: %s; Size: %s) as image.", picture.getOriginalFilename(), picture.getContentType(), fileSizeFormatted));
				bindingResult.addError(new FieldError("cfpSubmissionSpeaker", "pictureFile",
					String.format("Did you upload a valid image? We cannot parse file '%s' (Size: %s) as image file.",
						picture.getOriginalFilename(), fileSizeFormatted)));
				return null;
			}

			int imageHeight = image.getHeight();
			int imageWidth  = image.getWidth();

			if (imageHeight < 360 && imageWidth < 360) {
				bindingResult.addError(new FieldError("cfpSubmissionSpeaker", "pictureFile",
					String.format("Your image '%s' (%sx%spx) is too small. Please provide an image of at least 360x360px.", picture.getOriginalFilename(), imageWidth, imageHeight)));
			}

			cfpSpeakerImage = new CfpSpeakerImage();

			cfpSpeakerImage.setFileData(pictureData);
			cfpSpeakerImage.setFileModified(new Date());
			cfpSpeakerImage.setFileSize(picture.getSize());
			cfpSpeakerImage.setName(picture.getOriginalFilename());
			cfpSpeakerImage.setType(picture.getContentType());

			return cfpSpeakerImage;
		}

		return null;
	}

	/**
	 * Open the Add Abstract Page.
	 *
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/abstract", method=RequestMethod.GET)
	public String openAddCfp(ModelMap model, WebRequest request) {

		if (CfpSettings.CfpState.CLOSED.equals(cfpSettings.getState())) {
			return "cfp-closed";
		}

		Event event = businessService.getCurrentEvent();
		CfpSubmission cfpSubmission = new CfpSubmission();
		cfpSubmission.setEvent(event);

		model.addAttribute("cfpSubmission", cfpSubmission);
		prepareReferenceData(model);

		final User user = (User) userService.loadUserByUsername(securityFacade.getUsername());
		final List<CfpSubmissionSpeaker> cfpSubmissionSpeakers = businessService.getCfpSubmissionSpeakersForUserAndEvent(user, event);
		model.addAttribute("speakers", cfpSubmissionSpeakers);

		return "cfp/cfp";

	}

	/**
	 * Add a new Abstract
	 *
	 * @param cfpSubmission
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/abstract", method=RequestMethod.POST)
	public String addCfp(@Valid @ModelAttribute("cfpSubmission") CfpSubmissionForm cfpSubmission,
			BindingResult bindingResult,
			ModelMap model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		if (CfpSettings.CfpState.CLOSED.equals(cfpSettings.getState())) {
			return "redirect:/s/index";
		}

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/cfp/index";
		}

		final Event eventFromDb = businessService.getCurrentEvent();

		if (bindingResult.hasErrors()) {
			prepareReferenceData(model);
			final User user = (User) userService.loadUserByUsername(securityFacade.getUsername());
			final List<CfpSubmissionSpeaker> cfpSubmissionSpeakers = businessService.getCfpSubmissionSpeakersForUserAndEvent(user, eventFromDb);
			model.addAttribute("speakers", cfpSubmissionSpeakers);
			return "cfp/cfp";
		}

		final CfpSubmission cfpSubmissionToSave = new CfpSubmission();

		if (cfpSubmission.getCfpSubmissionSpeakers() == null || cfpSubmission.getCfpSubmissionSpeakers().size() < 1) {
			ObjectError error = new ObjectError("error","Please submit at least 1 speaker.");
			bindingResult.addError(error);
			prepareReferenceData(model);

			final User user = (User) userService.loadUserByUsername(securityFacade.getUsername());
			final List<CfpSubmissionSpeaker> cfpSubmissionSpeakers = businessService.getCfpSubmissionSpeakersForUserAndEvent(user, eventFromDb);
			model.addAttribute("speakers", cfpSubmissionSpeakers);
			return "cfp/cfp";
		}

		cfpSubmissionToSave.setDescription(cfpSubmission.getDescription());
		cfpSubmissionToSave.setEvent(eventFromDb);

		cfpSubmissionToSave.setPresentationType(cfpSubmission.getPresentationType());
		cfpSubmissionToSave.setSessionRecordingApproved(cfpSubmission.isSessionRecordingApproved());
		cfpSubmissionToSave.setSkillLevel(cfpSubmission.getSkillLevel());
		cfpSubmissionToSave.setSlotPreference(cfpSubmission.getSlotPreference());
		cfpSubmissionToSave.setTitle(cfpSubmission.getTitle());
		cfpSubmissionToSave.setTopic(cfpSubmission.getTopic());
		cfpSubmissionToSave.setStatus(CfpSubmissionStatusType.PENDING);

		final User user = (User) userService.loadUserByUsername(securityFacade.getUsername());
		final List<CfpSubmissionSpeaker> cfpSubmissionSpeakersFromDb = businessService.getCfpSubmissionSpeakersForUserAndEvent(user, eventFromDb);

		for (CfpSubmissionSpeaker cfpSubmissionSpeaker : cfpSubmission.getCfpSubmissionSpeakers()) {
			for (CfpSubmissionSpeaker cfpSubmissionSpeakerFromDb : cfpSubmissionSpeakersFromDb) {
				if (cfpSubmissionSpeakerFromDb.getId().equals(cfpSubmissionSpeaker.getId())) {
					cfpSubmissionToSave.getCfpSubmissionSpeakers().add(cfpSubmissionSpeakerFromDb);
				}
			}
		}

		cfpSubmissionToSave.setCreatedByUser(user);

		LOGGER.info(cfpSubmissionToSave.toString());
		businessService.saveAndNotifyCfpSubmission(cfpSubmissionToSave);

		redirectAttributes.addFlashAttribute("successMessage",
				String.format("Your abstract '%s' was added successfully.", cfpSubmissionToSave.getTitle()));
		return "redirect:/s/cfp/index";
	}

	/**
	 * Executed once a new abstract has been submitted.
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/s/cfp/add-cfp-success", method=RequestMethod.GET)
	public String addCfpSuccess(ModelMap model) {
		return "cfp/cfp-add-success";
	}

	@RequestMapping(value="/abstract/{cfpId}", method=RequestMethod.GET)
	public String prepareEditCfp(@PathVariable("cfpId") Long cfpId, ModelMap model) {
		final Event event = businessService.getCurrentEvent();
		final User user = (User) userService.loadUserByUsername(securityFacade.getUsername());

		prepareReferenceData(model);
		final CfpSubmission cfpSubmission = businessService.getCfpSubmission(cfpId);
		final List<CfpSubmissionSpeaker> cfpSubmissionSpeakersFromDb = businessService.getCfpSubmissionSpeakersForUserAndEvent(user, event);

		model.addAttribute("cfpSubmission", cfpSubmission);
		model.addAttribute("event", event);
		model.addAttribute("speakers", cfpSubmissionSpeakersFromDb);

		return "/cfp/edit-cfp";
	}

	@RequestMapping(value="/speaker/{cfpSubmissionSpeakerId}", method=RequestMethod.GET)
	public String prepareEditCfpSpeaker(@PathVariable("cfpSubmissionSpeakerId") Long cfpSubmissionSpeakerId, ModelMap model) {

		final Event event = businessService.getCurrentEvent();
		final User user = (User) userService.loadUserByUsername(securityFacade.getUsername());
		final List<CfpSubmissionSpeaker> cfpSubmissionSpeakersFromDb = businessService.getCfpSubmissionSpeakersForUserAndEvent(user, event);

		CfpSubmissionSpeaker cfpSubmissionSpeaker = null;

		for (CfpSubmissionSpeaker cfpSubmissionSpeakerFromDb : cfpSubmissionSpeakersFromDb) {
			if (cfpSubmissionSpeakerFromDb.getId().equals(cfpSubmissionSpeakerId)) {
				cfpSubmissionSpeaker = cfpSubmissionSpeakerFromDb;
			}
		}

		if (cfpSubmissionSpeaker == null) {
			throw new IllegalArgumentException(String.format("CfpSubmissionSpeaker with id %s is not a valid speaker for the associated user.", cfpSubmissionSpeakerId));
		}

		prepareReferenceData(model);
		model.addAttribute("cfpSubmissionSpeaker", cfpSubmissionSpeaker);
		model.addAttribute("event", event);

		return "/cfp/edit-cfp-speaker";
	}

	@RequestMapping(value="/speaker-image/{speakerId}", method=RequestMethod.GET)
	public void getOrganizerPicture(@PathVariable("speakerId") Long speakerId, HttpServletResponse response) {

		//FIXME - query for user
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

	@RequestMapping(value="/speaker/{cfpSubmissionSpeakerId}", method=RequestMethod.POST)
	public String editCfpSpeaker(@PathVariable("cfpSubmissionSpeakerId") Long cfpSubmissionSpeakerId,
			@Valid @ModelAttribute("cfpSubmissionSpeaker") CfpSubmissionSpeakerForm cfpSubmissionSpeaker,
	                      BindingResult bindingResult,
	                      ModelMap model,
	                      RedirectAttributes redirectAttributes,
	                      HttpServletRequest request) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/cfp/index";
		}

		final User user = (User) userService.loadUserByUsername(securityFacade.getUsername());
		final Event event = businessService.getCurrentEvent();
		if (request.getParameter("delete") != null) {

			final List<CfpSubmission> cfpSubmissions = businessService.getCfpSubmissionsForUserAndEvent(user.getId(), event.getId());

			boolean canDelete = false;

			for (CfpSubmission cfpSubmission : cfpSubmissions) {
				for (CfpSubmissionSpeaker speaker : cfpSubmission.getCfpSubmissionSpeakers()) {
					if (speaker.getId().equals(cfpSubmissionSpeakerId)) {
						canDelete = false;
						break;
					}
				}
			}

			if (canDelete) {
				businessService.deleteCfpSubmissionSpeakerForUser(cfpSubmissionSpeakerId, event.getId(), user.getId());
			}
			else {
				bindingResult.addError(new ObjectError("cfpSubmissionSpeaker", "Cannot delete speaker. The speaker is still associated with an abstract."));
				prepareReferenceData(model);
				return "/cfp/edit-cfp-speaker";
			}
			redirectAttributes.addFlashAttribute("successMessage",
					String.format("The speaker '%s' was deleted successfully.", cfpSubmissionSpeaker.getFirstLastName()));
			return "redirect:/s/cfp/index";
		}

		if (bindingResult.hasErrors()) {
			prepareReferenceData(model);
			return "/cfp/edit-cfp-speaker";
		}


		final MultipartFile picture = cfpSubmissionSpeaker.getPictureFile();

		final CfpSpeakerImage cfpSpeakerImage = processPicture(picture, bindingResult);

		if (bindingResult.hasErrors()) {
			prepareReferenceData(model);
			return "/cfp/speaker";
		}
//FIXME
		final CfpSubmissionSpeaker cfpSubmissionSpeakerFromDb = businessService.getCfpSubmissionSpeakerWithPicture(cfpSubmissionSpeakerId);

		final CfpSubmissionSpeaker cfpSubmissionSpeakerToSave = new CfpSubmissionSpeaker();

		cfpSubmissionSpeakerFromDb.setEmail(cfpSubmissionSpeaker.getEmail());
		cfpSubmissionSpeakerFromDb.setBio(cfpSubmissionSpeaker.getBio());
		cfpSubmissionSpeakerFromDb.setFirstName(cfpSubmissionSpeaker.getFirstName());
		cfpSubmissionSpeakerFromDb.setGithubId(cfpSubmissionSpeaker.getGithubId());
		cfpSubmissionSpeakerFromDb.setGooglePlusId(cfpSubmissionSpeaker.getGooglePlusId());
		cfpSubmissionSpeakerFromDb.setLanyrdId(cfpSubmissionSpeaker.getLanyrdId());
		cfpSubmissionSpeakerFromDb.setLastName(cfpSubmissionSpeaker.getLastName());
		cfpSubmissionSpeakerFromDb.setPhone(cfpSubmissionSpeaker.getPhone());
		cfpSubmissionSpeakerFromDb.setLinkedInId(cfpSubmissionSpeaker.getLinkedInId());
		cfpSubmissionSpeakerFromDb.setTwitterId(cfpSubmissionSpeaker.getTwitterId());
		cfpSubmissionSpeakerFromDb.setLocation(cfpSubmissionSpeaker.getLocation());
		cfpSubmissionSpeakerFromDb.setMustReimburseTravelCost(cfpSubmissionSpeaker.isMustReimburseTravelCost());
		cfpSubmissionSpeakerFromDb.setTshirtSize(cfpSubmissionSpeaker.getTshirtSize());

		if (cfpSpeakerImage != null) {
			cfpSubmissionSpeakerFromDb.setCfpSpeakerImage(cfpSpeakerImage);
		}

		LOGGER.info(cfpSubmissionSpeakerToSave.toString());

		businessService.saveCfpSubmissionSpeaker(cfpSubmissionSpeakerFromDb);

		redirectAttributes.addFlashAttribute("successMessage",
				String.format("The speaker '%s' was edited successfully.", cfpSubmissionSpeakerFromDb.getFirstLastName()));
		return "redirect:/s/admin/{eventKey}/cfps";
	}

	@RequestMapping(value="/abstract/{cfpId}", method=RequestMethod.POST)
	public String editCfp(@PathVariable("cfpId") Long cfpId,
	                      CfpSubmission cfpSubmission,
	                      BindingResult result,
	                      RedirectAttributes redirectAttributes,
	                      HttpServletRequest request) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/cfp/index";
		}

		final CfpSubmission cfpSubmissionFromDb = businessService.getCfpSubmission(cfpId);
		final User user = (User) userService.loadUserByUsername(securityFacade.getUsername());
		final Event event = businessService.getCurrentEvent();

		if (request.getParameter("delete") != null) {
			businessService.deleteCfpSubmissionForUser(cfpSubmissionFromDb.getId(), user.getId(), event.getId());
			return "redirect:/s/cfp/index";
		}

		if (result.hasErrors()) {
			return "/edit-cfp";
		}

		cfpSubmissionFromDb.setDescription(cfpSubmission.getDescription());
		cfpSubmissionFromDb.setStatus(cfpSubmission.getStatus());
		cfpSubmissionFromDb.setPresentationType(cfpSubmission.getPresentationType());
		cfpSubmissionFromDb.setSessionRecordingApproved(cfpSubmission.isSessionRecordingApproved());
		cfpSubmissionFromDb.setSkillLevel(cfpSubmission.getSkillLevel());
		cfpSubmissionFromDb.setSlotPreference(cfpSubmission.getSlotPreference());
		cfpSubmissionFromDb.setTitle(cfpSubmission.getTitle());
		cfpSubmissionFromDb.setTopic(cfpSubmission.getTopic());


		final List<CfpSubmissionSpeaker> cfpSubmissionSpeakersFromDb = businessService.getCfpSubmissionSpeakersForUserAndEvent(user, event);

		cfpSubmissionFromDb.getCfpSubmissionSpeakers().clear();

		for (CfpSubmissionSpeaker cfpSubmissionSpeaker : cfpSubmission.getCfpSubmissionSpeakers()) {
			for (CfpSubmissionSpeaker cfpSubmissionSpeakerFromDb : cfpSubmissionSpeakersFromDb) {
				if (cfpSubmissionSpeakerFromDb.getId().equals(cfpSubmissionSpeaker.getId())) {
					cfpSubmissionFromDb.getCfpSubmissionSpeakers().add(cfpSubmissionSpeakerFromDb);
				}
			}
		}

		businessService.saveCfpSubmission(cfpSubmissionFromDb);

		redirectAttributes.addFlashAttribute("successMessage",
				String.format("The abstract '%s' was edited successfully.", cfpSubmissionFromDb.getTitle()));
		return "redirect:/s/cfp/index";
	}

}
