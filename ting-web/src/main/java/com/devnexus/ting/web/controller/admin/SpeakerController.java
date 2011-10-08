package com.devnexus.ting.web.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devnexus.ting.common.AppHomeSource;
import com.devnexus.ting.common.Apphome;
import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.Speaker;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.web.filter.FlashMap;
import com.devnexus.ting.web.form.EventForm;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Controller("adminSpeakerController")
public class SpeakerController {

	@Autowired private BusinessService businessService;

	@Autowired private Validator validator;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    private final static Logger LOGGER = LoggerFactory.getLogger(SpeakerController.class);

    @RequestMapping(value="/admin/speakers", method=RequestMethod.GET)
    public String getSpeakers(ModelMap model, HttpServletRequest request) {

    	final List<Speaker> speakers = businessService.getAllSpeakers();
    	model.addAttribute("speakers", speakers);

        return "/admin/manage-speakers";
    }

    @RequestMapping(value="/admin/speaker", method=RequestMethod.GET)
    public String prepareAddSpeaker(ModelMap model) {

    	final List<Event> events = businessService.getAllEvents();

    	model.addAttribute("events", events);

    	Speaker speakerForm = new Speaker();

    	model.addAttribute("speaker", speakerForm);

        return "/admin/add-speaker";
    }

    @RequestMapping(value="/admin/speaker/{speakerId}", method=RequestMethod.GET)
    public String prepareEditSpeaker(@PathVariable("speakerId") Long speakerId, ModelMap model) {

    	final List<Event> events = businessService.getAllEvents();

    	model.addAttribute("events", events);

    	Speaker speakerForm = businessService.getSpeaker(speakerId);

    	model.addAttribute("speaker", speakerForm);

        return "/admin/add-speaker";
    }

    @RequestMapping(value="/admin/speaker/{speakerId}", method=RequestMethod.POST)
    public String editSpeaker(@PathVariable("speakerId") Long speakerId, @RequestParam MultipartFile pictureFile, @Valid Speaker speakerForm, BindingResult result, HttpServletRequest request) {

        if (request.getParameter("cancel") != null) {
        	return "redirect:/s/admin/index";
        }

		if (result.hasErrors()) {
	        return "/admin/add-speaker";
		}

    	if (pictureFile != null && pictureFile.getSize() > 0) {

			try {
				SystemInformationUtils.setSpeakerImage(speakerForm.getPicture(), pictureFile.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String message = "File '" + speakerForm.getPicture() + "' uploaded successfully";
			FlashMap.setSuccessMessage(message);
    	}

    	final Speaker speakerFromDb = businessService.getSpeaker(speakerId);

    	speakerFromDb.setBio(speakerForm.getBio());
    	speakerFromDb.setFirstName(speakerForm.getFirstName());
    	speakerFromDb.setLastName(speakerForm.getLastName());
    	speakerFromDb.setPicture(speakerForm.getPicture());

		businessService.saveSpeaker(speakerFromDb);

    	FlashMap.setSuccessMessage("The speaker was edited successfully.");
        return "redirect:/s/admin/speakers";
    }

    @RequestMapping(value="/admin/speaker", method=RequestMethod.POST)
    public String addSpeaker(@RequestParam MultipartFile pictureFile, @Valid Speaker speakerForm, BindingResult result, HttpServletRequest request) {

        if (request.getParameter("cancel") != null) {
        	return "redirect:/s/admin/speakers";
        }

		if (result.hasErrors()) {
	        return "/admin/add-speaker";
		}

    	if (pictureFile != null && pictureFile.getSize() > 0) {

			try {
				SystemInformationUtils.setSpeakerImage(speakerForm.getPicture(), pictureFile.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String message = "File '" + speakerForm.getPicture() + "' uploaded successfully";
			FlashMap.setSuccessMessage(message);

    	}

		Speaker savedSpeaker = businessService.saveSpeaker(speakerForm);

    	FlashMap.setSuccessMessage("The speaker was added successfully.");
        return "redirect:/s/admin/speakers";
    }

}
