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
package com.devnexus.ting.web.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
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

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.FileData;
import com.devnexus.ting.model.Organizer;

/**
 *
 * @author Gunnar Hillert
 *
 */
@Controller("adminOrganizerController")
public class OrganizerController {

	@Autowired private BusinessService businessService;

	@RequestMapping(value="/s/admin/organizers", method=RequestMethod.GET)
	public String getOrganizers(ModelMap model, HttpServletRequest request) {

		final List<Organizer> organizers = businessService.getAllOrganizers();
		model.addAttribute("organizers", organizers);

		return "/admin/manage-organizers";
	}

	@RequestMapping(value="/s/admin/organizer", method=RequestMethod.GET)
	public String prepareAddOrganizer(ModelMap model) {

		Organizer organizerForm = new Organizer();

		model.addAttribute("organizer", organizerForm);

		return "/admin/add-organizer";
	}

	@RequestMapping(value="/s/admin/organizer/{organizerId}", method=RequestMethod.GET)
	public String prepareEditOrganizer(@PathVariable("organizerId") Long organizerId, ModelMap model) {

		final Organizer organizerFromDb = businessService.getOrganizer(organizerId);

		model.addAttribute("organizer", organizerFromDb);

		return "/admin/add-organizer";
	}

	@RequestMapping(value="/s/admin/organizer/{organizerId}", method=RequestMethod.POST)
	public String editOrganizer(@PathVariable("organizerId") Long organizerId,
			@RequestParam MultipartFile pictureFile,
			@Valid Organizer organizerForm,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/index";
		}

		if (result.hasErrors()) {
			return "/admin/add-organizer";
		}

		final Organizer organizerFromDb = businessService.getOrganizer(organizerId);

		if (request.getParameter("delete") != null) {
			businessService.deleteOrganizer(organizerFromDb);
			//FlashMap.setSuccessMessage("The organizer was deleted successfully.");
			return "redirect:/s/admin/organizers";
		}

		organizerFromDb.setBio(organizerForm.getBio());
		organizerFromDb.setFirstName(organizerForm.getFirstName());
		organizerFromDb.setLastName(organizerForm.getLastName());
		organizerFromDb.setCompany(organizerForm.getCompany());

		organizerFromDb.setGooglePlusId(organizerForm.getGooglePlusId());
		organizerFromDb.setLinkedInId(organizerForm.getLinkedInId());
		organizerFromDb.setTwitterId(organizerForm.getTwitterId());
		organizerFromDb.setLanyrdId(organizerForm.getLanyrdId());
		organizerFromDb.setGithubId(organizerForm.getGithubId());
		organizerFromDb.setSortOrder(organizerForm.getSortOrder());

		if (pictureFile != null && pictureFile.getSize() > 0) {

			final FileData pictureData;
			if (organizerFromDb.getPicture()==null) {
				pictureData = new FileData();
			} else {
				pictureData = organizerFromDb.getPicture();
			}

			try {

				pictureData.setFileData(IOUtils.toByteArray(pictureFile.getInputStream()));
				pictureData.setFileSize(pictureFile.getSize());
				pictureData.setFileModified(new Date());
				pictureData.setName(pictureFile.getOriginalFilename());

			} catch (IOException e) {
				throw new IllegalStateException("Error while processing image for speaker " + organizerForm.getFirstLastName(), e);
			}

			organizerForm.setPicture(pictureData);

			String message = "File '" + organizerForm.getPicture().getName() + "' uploaded successfully";
			redirectAttributes.addFlashAttribute("successMessage", message);
		}

		businessService.saveOrganizer(organizerFromDb);

		redirectAttributes.addFlashAttribute("successMessage", "The organizer was edited successfully.");

		return "redirect:/s/admin/organizers";
	}

	@RequestMapping(value="/s/admin/organizer", method=RequestMethod.POST)
	public String addOrganizer(@RequestParam MultipartFile pictureFile,
			@Valid Organizer organizerForm, BindingResult result,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/organizers";
		}

		if (result.hasErrors()) {
			return "/admin/add-organizer";
		}

		if (pictureFile != null && pictureFile.getSize() > 0) {

			final FileData pictureData = new FileData();

			try {

				pictureData.setFileData(IOUtils.toByteArray(pictureFile.getInputStream()));
				pictureData.setFileSize(pictureFile.getSize());
				pictureData.setFileModified(new Date());
				pictureData.setName(pictureFile.getOriginalFilename());

			} catch (IOException e) {
				throw new IllegalStateException("Error while processing image for speaker " + organizerForm.getFirstLastName(), e);
			}

			organizerForm.setPicture(pictureData);

			String message = "File '" + organizerForm.getPicture().getName() + "' uploaded successfully";
			redirectAttributes.addFlashAttribute("successMessage", message);

		}

		Organizer savedOrganizer = businessService.saveOrganizer(organizerForm);

		redirectAttributes.addFlashAttribute("successMessage",
				String.format("The organizer '%s' was added successfully.", savedOrganizer.getFirstLastName()));

		return "redirect:/s/admin/organizers";
	}

}
