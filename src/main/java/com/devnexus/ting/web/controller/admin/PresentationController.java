/*
 * Copyright 2002-2012 the original author or authors.
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

import java.io.IOException;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.FileData;
import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.PresentationType;
import com.devnexus.ting.core.model.SkillLevel;
import com.devnexus.ting.core.model.Speaker;
import com.devnexus.ting.core.service.BusinessService;


/**
 * @author Gunnar Hillert
 * @since 1.0
 */
@Controller("adminPresentationController")
public class PresentationController {

	@Autowired private BusinessService businessService;

	@Autowired private Validator validator;


	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PresentationController.class);

	@RequestMapping(value="/admin/presentations", method=RequestMethod.GET)
	public String getPresentations(ModelMap model, HttpServletRequest request) {

		final List<Presentation> presentations = businessService.getAllPresentations();
		model.addAttribute("presentations", presentations);

		return "/admin/manage-presentations";
	}

	@RequestMapping(value="/admin/presentation", method=RequestMethod.GET)
	public String prepareAddPresentation(ModelMap model) {

		final List<Event> events = businessService.getAllEventsOrderedByName();
		model.addAttribute("events", events);

		final Set<PresentationType> presentationTypes = EnumSet.allOf(PresentationType.class);
		model.addAttribute("presentationTypes", presentationTypes);

		final Set<SkillLevel> skillLevels = EnumSet.allOf(SkillLevel.class);
		model.addAttribute("skillLevels", skillLevels);

		final List<Speaker> speakers = businessService.getAllSpeakersOrderedByName();
		model.addAttribute("speakers", speakers);

		final Presentation presentation = new Presentation();
		model.addAttribute("presentation", presentation);

		return "/admin/add-presentation";
	}

	@RequestMapping(value="/admin/presentation", method=RequestMethod.POST)
	public String addPresentation(@Valid Presentation presentation, BindingResult bindingResult, HttpServletRequest request) {
		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/presentations";
		}

		if (bindingResult.hasErrors()) {
			return "/admin/add-presentation";
		}

		if (presentation.getSpeaker() != null && presentation.getSpeaker().getId() != null) {
			Speaker speakerFromDb = businessService.getSpeaker(presentation.getSpeaker().getId());
			presentation.setSpeaker(speakerFromDb);

		}

		businessService.savePresentation(presentation);
		return "redirect:/s/admin/presentations";
	}

	@RequestMapping(value="/admin/presentation/{presentationId}", method=RequestMethod.GET)
	public String prepareEditPresentation(@PathVariable("presentationId") Long presentationId, ModelMap model) {

		final List<Event> events = businessService.getAllEventsOrderedByName();
		model.addAttribute("events", events);

		final Set<PresentationType> presentationTypes = EnumSet.allOf(PresentationType.class);
		model.addAttribute("presentationTypes", presentationTypes);

		final Set<SkillLevel> skillLevels = EnumSet.allOf(SkillLevel.class);
		model.addAttribute("skillLevels", skillLevels);

		final List<Speaker> speakers = businessService.getAllSpeakersOrderedByName();
		model.addAttribute("speakers", speakers);

		Presentation presentation = businessService.getPresentation(presentationId);

		model.addAttribute("presentation", presentation);

		return "/admin/add-presentation";
	}

	@RequestMapping(value="/admin/presentation/{presentationId}", method=RequestMethod.POST)
	public String editPresentation(@PathVariable("presentationId") Long presentationId,
			@RequestParam MultipartFile uploadedFile,
								@Valid Presentation presentation,
								BindingResult result,
								HttpServletRequest request,
								RedirectAttributes redirectAttributes) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/presentations";
		}

		if (result.hasErrors()) {
			return "/admin/add-presentation";
		}

		final Presentation presentationFromDb = businessService.getPresentation(presentationId);

		presentationFromDb.setAudioLink(presentation.getAudioLink());
		presentationFromDb.setDescription(presentation.getDescription());
		presentationFromDb.setEvent(presentation.getEvent());
		presentationFromDb.setPresentationLink(presentation.getPresentationLink());
		presentationFromDb.setTitle(presentation.getTitle());

		presentationFromDb.setSkillLevel(presentation.getSkillLevel());
		presentationFromDb.setPresentationType(presentation.getPresentationType());

		if (uploadedFile != null && uploadedFile.getSize() > 0) {

			final FileData presentationData;
			if (presentationFromDb.getPresentationFile()==null) {
				presentationData = new FileData();
			} else {
				presentationData = presentationFromDb.getPresentationFile();
			}

			try {

				presentationData.setFileData(IOUtils.toByteArray(uploadedFile.getInputStream()));
				presentationData.setFileSize(uploadedFile.getSize());
				presentationData.setFileModified(new Date());
				presentationData.setName(uploadedFile.getOriginalFilename());
				presentationData.setType(uploadedFile.getContentType());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			presentationFromDb.setPresentationFile(presentationData);

			String message = "File '" + presentationData.getName() + "' uploaded successfully";
			redirectAttributes.addFlashAttribute("successMessage", message);
		}

		businessService.savePresentation(presentationFromDb);

		redirectAttributes.addFlashAttribute("successMessage", "The presentation was edited successfully.");
		return "redirect:/s/admin/presentations";
	}
}
