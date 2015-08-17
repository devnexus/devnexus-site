/*
 * Copyright 2014 the original author or authors.
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
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.FileData;
import com.devnexus.ting.model.Sponsor;

/**
 *
 * @author Gunnar Hillert
 *
 */
@Controller("adminSponsorController")
public class SponsorController {

	@Autowired private BusinessService businessService;

	@Autowired private Validator validator;

	@RequestMapping(value="/s/admin/{eventKey}/sponsors", method=RequestMethod.GET)
	public String getSponsorsForCurrentEvent(@PathVariable("eventKey") String eventKey,
			ModelMap model, HttpServletRequest request) {
		final Event event = businessService.getEventByEventKey(eventKey);
		final List<Sponsor> sponsors = businessService.getSponsorsForEvent(event.getId());
		model.addAttribute("sponsors", sponsors);
		model.addAttribute("event", event);
		return "/admin/manage-sponsors";
	}

	@RequestMapping(value="/s/admin/{eventKey}/sponsor", method=RequestMethod.GET)
	public String prepareAddSponsor(ModelMap model) {
		final Sponsor sponsorForm = new Sponsor();
		model.addAttribute("sponsor", sponsorForm);
		return "/admin/add-sponsor";
	}

	@RequestMapping(value="/s/admin/{eventKey}/sponsor/{sponsorId}", method=RequestMethod.GET)
	public String prepareEditSponsor(@PathVariable("sponsorId") Long sponsorId, ModelMap model) {
		final Sponsor sponsorFromDb = businessService.getSponsor(sponsorId);
		model.addAttribute("sponsor", sponsorFromDb);
		return "/admin/add-sponsor";
	}

	@RequestMapping(value="/s/admin/{eventKey}/sponsor/{sponsorId}", method=RequestMethod.POST)
	public String editSponsor(@PathVariable("sponsorId") Long sponsorId,
			@RequestParam MultipartFile pictureFile,
			@Valid Sponsor sponsorForm,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/{eventKey}/sponsors";
		}

		if (result.hasErrors()) {
			return "/admin/add-sponsor";
		}

		final Sponsor sponsorFromDb = businessService.getSponsor(sponsorId);

		if (request.getParameter("delete") != null) {
			businessService.deleteSponsor(sponsorFromDb);
			redirectAttributes.addFlashAttribute("successMessage", "The sponsor was deleted successfully.");
			return "redirect:/s/admin/{eventKey}/sponsors";
		}

		sponsorFromDb.setLink(sponsorForm.getLink());
		sponsorFromDb.setName(sponsorForm.getName());
		sponsorFromDb.setSortOrder(sponsorForm.getSortOrder());
		sponsorFromDb.setSponsorLevel(sponsorForm.getSponsorLevel());

		if (pictureFile != null && pictureFile.getSize() > 0) {

			final FileData pictureData;
			if (sponsorFromDb.getLogo()==null) {
				pictureData = new FileData();
			} else {
				pictureData = sponsorFromDb.getLogo();
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

			sponsorFromDb.setLogo(pictureData);
		}

		businessService.saveSponsor(sponsorFromDb);
		redirectAttributes.addFlashAttribute("successMessage",
			String.format("The sponsor '%s' was edited successfully.", sponsorFromDb.getName()));

		return "redirect:/s/admin/{eventKey}/sponsors";
	}

	@RequestMapping(value="/s/admin/{eventKey}/sponsor", method=RequestMethod.POST)
	public String addSponsor(@RequestParam MultipartFile pictureFile,
			@Valid Sponsor sponsorForm, BindingResult result, HttpServletRequest request,
			@PathVariable("eventKey") String eventKey) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/{eventKey}/sponsors";
		}

		if (result.hasErrors()) {
			return "/admin/add-sponsor";
		}

		final Event currentEvent = businessService.getEventByEventKey(eventKey);
		sponsorForm.setEvent(currentEvent);

		if (pictureFile != null && pictureFile.getSize() > 0) {

			 final FileData pictureData = new FileData();

			 try {

				 pictureData.setFileData(IOUtils.toByteArray(pictureFile.getInputStream()));
				 pictureData.setFileSize(pictureFile.getSize());
				 pictureData.setFileModified(new Date());
				 pictureData.setName(pictureFile.getOriginalFilename());
				 pictureData.setType(pictureFile.getContentType());
			 } catch (IOException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }

			 sponsorForm.setLogo(pictureData);

			//TODO
			//String message = "File '" + sponsorForm.getPicture().getName() + "' uploaded successfully";
			//FlashMap.setSuccessMessage(message);

		}

		//TODO
		Sponsor savedSponsor = businessService.saveSponsor(sponsorForm);

		//FlashMap.setSuccessMessage("The sponsor was added successfully.");
		return "redirect:/s/admin/{eventKey}/sponsors";
	}

}
