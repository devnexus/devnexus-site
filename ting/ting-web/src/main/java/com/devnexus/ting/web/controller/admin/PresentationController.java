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
@Controller("adminPresentationController")
public class PresentationController {

	@Autowired private BusinessService businessService;

	@Autowired private Validator validator;


    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    private final static Logger LOGGER = LoggerFactory.getLogger(PresentationController.class);

    @RequestMapping(value="/admin/presentations", method=RequestMethod.GET)
    public String getPresentations(ModelMap model, HttpServletRequest request) {

    	final List<Presentation> presentations = businessService.getAllPresentations();
    	model.addAttribute("presentations", presentations);

        return "/admin/manage-presentations";
    }

    @RequestMapping(value="/admin/presentation", method=RequestMethod.GET)
    public String prepareAddPresentation(ModelMap model) {

    	final List<Event> events = businessService.getAllEvents();
    	model.addAttribute("events", events);

    	final List<Speaker> speakers = businessService.getAllSpeakers();
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

    	businessService.savePresentation(presentation);

        return "redirect:/s/admin/presentations";
    }

    @RequestMapping(value="/admin/presentation/{presentationId}", method=RequestMethod.GET)
    public String prepareEditPresentation(@PathVariable("presentationId") Long presentationId, ModelMap model) {

    	final List<Event> events = businessService.getAllEvents();
    	model.addAttribute("events", events);

    	final List<Speaker> speakers = businessService.getAllSpeakers();
    	model.addAttribute("speakers", speakers);

    	Presentation presentation = businessService.getPresentation(presentationId);

    	model.addAttribute("presentation", presentation);

        return "/admin/add-presentation";
    }

    @RequestMapping(value="/admin/presentation/{presentationId}", method=RequestMethod.POST)
    public String editPresentation(@PathVariable("presentationId") Long presentationId, @Valid Presentation presentation, BindingResult result, HttpServletRequest request) {

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
    	presentationFromDb.setSpeaker(presentation.getSpeaker());
    	presentationFromDb.setTitle(presentation.getTitle());

		businessService.savePresentation(presentationFromDb);

    	FlashMap.setSuccessMessage("The presentation was edited successfully.");
        return "redirect:/s/admin/presentations";
    }
}
