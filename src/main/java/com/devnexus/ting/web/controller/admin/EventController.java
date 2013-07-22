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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.Speaker;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.web.form.EventForm;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 *
 */
@Controller
public class EventController {

	@Autowired private BusinessService businessService;

	@Autowired private Validator validator;


	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;

	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@RequestMapping(value="/admin/event", method=RequestMethod.GET)
	public String prepareAddEvent(ModelMap model) {

		final EventForm eventForm = new EventForm();
		model.addAttribute(eventForm);

		final List<Speaker>speakers = businessService.getAllSpeakersOrderedByName();
		model.addAttribute("speakers", speakers);

		return "/admin/add-event";
	}

	@RequestMapping(value="/admin/event/{id}", method=RequestMethod.GET)
	public String prepareEditEvent(@PathVariable("id") Long eventId, ModelMap model) {

		final EventForm eventForm = new EventForm();

		final Event event = businessService.getEvent(eventId);
		eventForm.setEvent(event);

		model.addAttribute(eventForm);

		final List<Speaker>speakers = businessService.getAllSpeakersOrderedByName();
		model.addAttribute("speakers", speakers);

		return "/admin/add-event";
	}

	@RequestMapping(value="/admin/event/{id}", method=RequestMethod.POST)
	public String editEvent(@PathVariable("id") Long eventId, @Valid EventForm eventForm,
			BindingResult bindingResult,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

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
				redirectAttributes.addFlashAttribute("succesMessage", "Event successfully deleted.");
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
		eventFromDb.setCurrent(eventForm.getEvent().isCurrent());

		Set<Speaker> speakers = new HashSet<Speaker>();

		if (eventForm.getEvent().getSpeakers() != null
				&& !eventForm.getEvent().getSpeakers().isEmpty()) {

			for (Speaker speaker : eventForm.getEvent().getSpeakers()) {
				Speaker speakerFromDb = businessService.getSpeaker(speaker.getId());
				speakers.add(speakerFromDb);
			}

			eventFromDb.setSpeakers(speakers);

		}

		validator.validate(eventForm);
		eventForm.getEvent().getEventKey();

		businessService.saveEvent(eventFromDb);

		return "redirect:/s/admin/index";
	}

	@RequestMapping(value="/admin/event", method=RequestMethod.POST)
	public String addEvent(@Valid EventForm eventForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

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

				redirectAttributes.addFlashAttribute("succesMessage", "Event successfully deleted.");
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

		final List<Event> events = businessService.getAllEventsOrderedByName();
		model.addAttribute("events", events);

		return "/admin/manage-events";
	}

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Set.class, "skills", new CustomCollectionEditor(Set.class)
//          {
//            @Override
//            protected Object convertElement(Object element)
//            {
//                Long id = null;
//
//                if(element instanceof String && !((String)element).equals("")){
//                    //From the JSP 'element' will be a String
//                    try{
//                        id = Long.parseLong((String) element);
//                    }
//                    catch (NumberFormatException e) {
//                        System.out.println("Element was " + ((String) element));
//                        e.printStackTrace();
//                    }
//                }
//                else if(element instanceof Long) {
//                    //From the database 'element' will be a Long
//                    id = (Long) element;
//                }
//
//                return id != null ? employeeService.loadSkillById(id) : null;
//            }
//          });
//    }

}
