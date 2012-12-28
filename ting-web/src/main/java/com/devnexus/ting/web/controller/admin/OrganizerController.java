/*
 * Copyright 2002-2011 the original author or authors.
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

import com.devnexus.ting.core.model.FileData;
import com.devnexus.ting.core.model.Organizer;
import com.devnexus.ting.core.service.BusinessService;

/**
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Controller("adminOrganizerController")
public class OrganizerController {

	@Autowired private BusinessService businessService;

	@Autowired private Validator validator;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;

	private final static Logger LOGGER = LoggerFactory.getLogger(OrganizerController.class);

	@RequestMapping(value="/admin/organizers", method=RequestMethod.GET)
	public String getOrganizers(ModelMap model, HttpServletRequest request) {

		final List<Organizer> organizers = businessService.getAllOrganizers();
		model.addAttribute("organizers", organizers);

		return "/admin/manage-organizers";
	}

	@RequestMapping(value="/admin/organizer", method=RequestMethod.GET)
	public String prepareAddOrganizer(ModelMap model) {

		Organizer organizerForm = new Organizer();

		model.addAttribute("organizer", organizerForm);

		return "/admin/add-organizer";
	}

	@RequestMapping(value="/admin/organizer/{organizerId}", method=RequestMethod.GET)
	public String prepareEditOrganizer(@PathVariable("organizerId") Long organizerId, ModelMap model) {

		final Organizer organizerFromDb = businessService.getOrganizer(organizerId);

		model.addAttribute("organizer", organizerFromDb);

		return "/admin/add-organizer";
	}

	@RequestMapping(value="/admin/organizer/{organizerId}", method=RequestMethod.POST)
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
				pictureData.setType(pictureFile.getContentType());
				pictureData.setName(pictureFile.getOriginalFilename());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			organizerFromDb.setPicture(pictureData);

			String message = "File '" + pictureData.getName() + "' uploaded successfully";
			//FlashMap.setSuccessMessage(message);
		}

		businessService.saveOrganizer(organizerFromDb);

		redirectAttributes.addFlashAttribute("successMessage", "The organizer was edited successfully.");

		return "redirect:/s/admin/organizers";
	}

	@RequestMapping(value="/admin/organizer", method=RequestMethod.POST)
	public String addOrganizer(@RequestParam MultipartFile pictureFile, @Valid Organizer organizerForm, BindingResult result, HttpServletRequest request) {

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
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }

			 organizerForm.setPicture(pictureData);

			String message = "File '" + organizerForm.getPicture().getName() + "' uploaded successfully";
			//FlashMap.setSuccessMessage(message);

		}

		Organizer savedOrganizer = businessService.saveOrganizer(organizerForm);

		//FlashMap.setSuccessMessage("The organizer was added successfully.");
		return "redirect:/s/admin/organizers";
	}

}
