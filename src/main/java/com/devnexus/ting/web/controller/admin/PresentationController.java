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
package com.devnexus.ting.web.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
import com.devnexus.ting.core.model.PresentationTag;
import com.devnexus.ting.core.model.PresentationType;
import com.devnexus.ting.core.model.SkillLevel;
import com.devnexus.ting.core.model.Speaker;
import com.devnexus.ting.core.model.Track;
import com.devnexus.ting.core.service.BusinessService;


/**
 * @author Gunnar Hillert
 * @since 1.0
 */
@Controller("adminPresentationController")
public class PresentationController {

	@Autowired private BusinessService businessService;

	@Autowired private Validator validator;

	private void prepareReferenceData(ModelMap model, Event event) {
		final Set<PresentationType> presentationTypes = EnumSet.allOf(PresentationType.class);
		model.addAttribute("presentationTypes", presentationTypes);

		final Set<SkillLevel> skillLevels = EnumSet.allOf(SkillLevel.class);
		model.addAttribute("skillLevels", skillLevels);

		final List<Track> tracks = businessService.getTracksForEvent(event.getId());
		model.addAttribute("tracks", tracks);
	}

	@RequestMapping(value="/admin/presentations", method=RequestMethod.GET)
	public String getPresentations(@RequestParam(value="eventId", required = false) Long eventId, ModelMap model, HttpServletRequest request) {

		final List<Presentation> presentations;

		if (eventId != null) {
			presentations = businessService.getPresentationsForEventOrderedByName(eventId);
		}
		else {
			presentations = businessService.getAllPresentations();
		}

		model.addAttribute("presentations", presentations);

		return "/admin/manage-presentations";
	}

	@RequestMapping(value="/admin/presentation", method=RequestMethod.GET)
	public String prepareAddPresentation(@RequestParam(value="eventId", required = false) Long eventId, ModelMap model) {

		final Event event;

		if (eventId == null) {
			event = businessService.getCurrentEvent();
		}
		else {
			event = businessService.getEvent(eventId);
		}

		prepareReferenceData(model, event);

		final List<Speaker> speakers = businessService.getSpeakersForEvent(event.getId());
		model.addAttribute("speakers", speakers);

		final Presentation presentation = new Presentation();
		presentation.setEvent(event);

		model.addAttribute("presentation", presentation);

		return "/admin/add-presentation";
	}

	@RequestMapping(value="/admin/presentation", method=RequestMethod.POST)
	public String addPresentation(@Valid Presentation presentation, BindingResult bindingResult, ModelMap model, HttpServletRequest request) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/presentations";
		}

		if (bindingResult.hasErrors()) {
			this.prepareReferenceData(model, presentation.getEvent());
			return "/admin/add-presentation";
		}

		final Presentation presentationToSave = new Presentation();

		if (presentation.getSpeaker() != null && presentation.getSpeaker().getId() != null) {
			Speaker speakerFromDb = businessService.getSpeaker(presentation.getSpeaker().getId());
			presentationToSave.setSpeaker(speakerFromDb);
		}

		if (presentation.getEvent() != null && presentation.getEvent().getId() != null) {
			Event eventFromDb = businessService.getEvent(presentation.getEvent().getId());
			presentationToSave.setEvent(eventFromDb);

		}

		presentationToSave.setAudioLink(presentation.getAudioLink());
		presentationToSave.setDescription(presentation.getDescription());
		presentationToSave.setPresentationLink(presentation.getPresentationLink());
		presentationToSave.setTitle(presentation.getTitle());

		presentationToSave.setSkillLevel(presentation.getSkillLevel());

		if (presentation.getTrack().getId() != null) {
			final Track trackFromDb = businessService.getTrack(presentation.getTrack().getId());
			presentationToSave.setTrack(trackFromDb);
		}

		presentationToSave.setPresentationType(presentation.getPresentationType());

		final Set<PresentationTag> presentationTagsToSave = processPresentationTags(presentation.getTagsAsText());
		presentationToSave.getPresentationTags().addAll(presentationTagsToSave);

		businessService.savePresentation(presentationToSave);
		return "redirect:/s/admin/presentations";
	}

	@RequestMapping(value="/admin/presentation/{presentationId}", method=RequestMethod.GET)
	public String prepareEditPresentation(@PathVariable("presentationId") Long presentationId, ModelMap model) {

		final Presentation presentation = businessService.getPresentation(presentationId);
		presentation.convertPresentationTagsToText();
		model.addAttribute("presentation", presentation);

		final List<Speaker> speakers = businessService.getSpeakersForEvent(presentation.getEvent().getId());
		model.addAttribute("speakers", speakers);

		this.prepareReferenceData(model, presentation.getEvent());

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
		presentationFromDb.setPresentationLink(presentation.getPresentationLink());
		presentationFromDb.setTitle(presentation.getTitle());

		presentationFromDb.setSkillLevel(presentation.getSkillLevel());

		if (presentation.getTrack().getId() != null) {
			final Track trackFromDb = businessService.getTrack(presentation.getTrack().getId());
			presentationFromDb.setTrack(trackFromDb);
		}

		presentationFromDb.setPresentationType(presentation.getPresentationType());

		final Set<PresentationTag> presentationTagsToSave = processPresentationTags(presentation.getTagsAsText());
		presentationFromDb.getPresentationTags().clear();
		presentationFromDb.getPresentationTags().addAll(presentationTagsToSave);

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

	private Set<PresentationTag> processPresentationTags(String tagsAsText) {

		final Set<PresentationTag> presentationTagsToSave = new HashSet<>();

		if (!tagsAsText.trim().isEmpty()) {
			Set<String> tags = StringUtils.commaDelimitedListToSet(tagsAsText);

			for (String tag : tags) {
				if (tag != null) {

					final String massagedTagName = tag.trim().toLowerCase(Locale.ENGLISH);
					PresentationTag tagFromDb = businessService.getPresentationTag(massagedTagName);

					if (tagFromDb == null) {
						PresentationTag presentationTag = new PresentationTag();
						presentationTag.setName(massagedTagName);
						tagFromDb = businessService.savePresentationTag(presentationTag);
					}

					presentationTagsToSave.add(tagFromDb);
				}
			}
		}

		return presentationTagsToSave;
	}
}
