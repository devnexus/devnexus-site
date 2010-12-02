package com.devnexus.ting.web.controller.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devnexus.ting.core.model.Event;
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
@Controller
public class EventController {

	@Autowired private BusinessService businessService;

	@Autowired private Validator validator;


    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    private final static Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @RequestMapping(value="/admin/event", method=RequestMethod.GET)
    public String prepareAddEvent(ModelMap model) {

    	final EventForm eventForm = new EventForm();
    	model.addAttribute(eventForm);

    	final List<Speaker>speakers = businessService.getAllSpeakers();
    	model.addAttribute("speakers", speakers);

        return "/admin/add-event";
    }

    @RequestMapping(value="/admin/event/{id}", method=RequestMethod.GET)
    public String prepareEditEvent(@PathVariable("id") Long eventId, ModelMap model) {

    	final EventForm eventForm = new EventForm();

		final Event event = businessService.getEvent(eventId);
		eventForm.setEvent(event);

    	model.addAttribute(eventForm);

    	final List<Speaker>speakers = businessService.getAllSpeakers();
    	model.addAttribute("speakers", speakers);

        return "/admin/add-event";
    }

    @RequestMapping(value="/admin/event/{id}", method=RequestMethod.POST)
    public String editEvent(@PathVariable("id") Long eventId, @Valid EventForm eventForm, BindingResult bindingResult, HttpServletRequest request) {

        if (request.getParameter("cancel") != null) {
        	return "redirect:/s/admin/index";
        }

        if (request.getParameter("delete") != null) {

        	if (eventForm.getEvent().getId() != null) {
        		final Event event = businessService.getEvent(eventForm.getEvent().getId());

        		if (event == null) {
        			throw new IllegalStateException("No event exists for id " + eventForm.getEvent().getId());
        		}

        		businessService.deleteEvent(event);
        		FlashMap.setSuccessMessage("Event successfully deleted.");
        		return "redirect:/s/admin/index";
        	} else {
        		throw new IllegalStateException("No event id provided. Cannot delete.");
        	}

        }

        if (bindingResult.hasErrors()) {
        	return "/admin/add-event";
        }

        final Event eventFromDb = businessService.getEvent(eventId);

        eventFromDb.setTitle(eventForm.getEvent().getTitle());
        eventFromDb.setEventKey(eventForm.getEvent().getEventKey());

        Set<Speaker> speakers = new HashSet<Speaker>();
        for (Speaker speaker : eventForm.getEvent().getSpeakers()) {
        	Speaker speakerFromDb = businessService.getSpeaker(speaker.getId());
        	speakers.add(speakerFromDb);
        }

        eventFromDb.setSpeakers(speakers);

        validator.validate(eventForm);
    	eventForm.getEvent().getEventKey();

    	businessService.saveEvent(eventFromDb);

        return "redirect:/s/admin/index";
    }

    @RequestMapping(value="/admin/event", method=RequestMethod.POST)
    public String addEvent(@Valid EventForm eventForm, BindingResult bindingResult, HttpServletRequest request) {

        if (request.getParameter("cancel") != null) {
        	return "redirect:/s/admin/index";
        }

        if (request.getParameter("delete") != null) {

        	if (eventForm.getEvent().getId() != null) {
        		final Event event = businessService.getEvent(eventForm.getEvent().getId());

        		if (event == null) {
        			throw new IllegalStateException("No event exists for id " + eventForm.getEvent().getId());
        		}

        		businessService.deleteEvent(event);
        		FlashMap.setSuccessMessage("Event successfully deleted.");
        		return "redirect:/s/admin/index";
        	} else {
        		throw new IllegalStateException("No event id provided. Cannot delete.");
        	}

        }

        if (bindingResult.hasErrors()) {
        	return "/admin/add-event";
        }

        Set<Speaker> speakers = new HashSet<Speaker>();
        if (eventForm.getEvent().getSpeakers() != null) {
	        for (Speaker speaker : eventForm.getEvent().getSpeakers()) {
	        	Speaker speakerFromDb = businessService.getSpeaker(speaker.getId());
	        	speakers.add(speakerFromDb);
	        }
        }

        eventForm.getEvent().setSpeakers(speakers);

    	businessService.saveEvent(eventForm.getEvent());

        return "redirect:/s/admin/index";
    }

    @RequestMapping(value="/admin/events", method=RequestMethod.GET)
    public String getEvents(ModelMap model, HttpServletRequest request) {

    	final List<Event> events = businessService.getAllEvents();
    	model.addAttribute("events", events);

        return "/admin/manage-events";
    }

}
