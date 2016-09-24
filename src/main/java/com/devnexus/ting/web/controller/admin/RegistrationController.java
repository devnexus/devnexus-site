/*
 * Copyright 2015 the original author or authors.
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
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supercsv.io.ICsvBeanWriter;

import com.devnexus.ting.config.support.CsvExportSettings;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.Dashboard;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.EventSignup;
import com.devnexus.ting.model.RegistrationDetails;
import com.devnexus.ting.model.TicketGroup;
import com.devnexus.ting.model.TicketOrderDetail;
import com.devnexus.ting.repository.EventSignupRepository;
import com.devnexus.ting.web.form.RegistrationSearchForm;
import com.devnexus.ting.web.form.SignupRegisterView;

/**
 *
 * Used to manage registration forms and validation/combination rules.
 *
 * @author Summers Pittman
 *
 */
@Controller("adminRegistrationController")
public class RegistrationController {

	@Inject
	private EventSignupRepository eventSignupRepository;

	@Inject
	private CsvExportSettings csvSettings;

	@Inject
	private BusinessService businessService;

	@RequestMapping(value = "/s/admin/{eventKey}/registration", method = RequestMethod.GET)
	public String loadRegistration(ModelMap model, HttpServletRequest request,
			@PathVariable(value = "eventKey") String eventKey) {

		EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

		model.addAttribute("event", signUp.getEvent());
		model.addAttribute("eventSignup", signUp);

		return "/admin/manage-registration";
	}

	@RequestMapping(value = "/s/admin/{eventKey}/registration/ticketGroup", method = RequestMethod.GET)
	public String showAddTicketGroupForm(ModelMap model, HttpServletRequest request,
			@PathVariable(value = "eventKey") String eventKey) {

		EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

		model.addAttribute("event", signUp.getEvent());
		model.addAttribute("eventSignUp", signUp);
		model.addAttribute("ticketGroup", new TicketGroup());

		return "/admin/add-ticketgroup";
	}

	@RequestMapping(value = "/s/admin/{eventKey}/reporting", method = RequestMethod.GET)
	public void createReport(@PathVariable(value = "eventKey") String eventKey, HttpServletResponse response)
			throws IOException {

		Event event = businessService.getEventByEventKey(eventKey);

		String csvFileName = eventKey + "registrations.csv";

		response.setContentType("text/csv");

		// creates mock data
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
		response.setHeader(headerKey, headerValue);

		List<RegistrationDetails> registrations = businessService.findRegistrationsForEvent(event);

		try ( // uses the Super CSV API to generate CSV data from the model data
				ICsvBeanWriter csvWriter = new TicketCsvWriter(response.getWriter(),
						new SimpleDateFormat(csvSettings.getDateFormat()))) {
			String[] header = { "First Name", "Last Name", "Email Address", "City", "State", "County", "Job Title",
					"Company", "T Shirt Size", "Vegetarian Meal", "Allow Sponsor To Contact", "Purchase Date",
					"Ticket Type", "Payment state" };

			csvWriter.writeHeader(header);

			for (RegistrationDetails registration : registrations) {
				for (TicketOrderDetail detail : registration.getOrderDetails()) {
					csvWriter.write(detail, header);
				}
			}
		}

	}

	@RequestMapping(value = "/s/admin/{eventKey}/dashboard", method = RequestMethod.GET)
	public String showDashboard(ModelMap model, HttpServletRequest request,
			@PathVariable(value = "eventKey") String eventKey) {

		EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

		Dashboard dashboard = businessService.generateDashBoardForSignUp(signUp);

		model.addAttribute("event", signUp.getEvent());
		model.addAttribute("dashboard", dashboard);

		return "/admin/dashboard";
	}

	@RequestMapping(value = "/s/admin/{eventKey}/registration/ticketGroup/{groupId}", method = RequestMethod.GET)
	public String prepareEditTicketGroupForm(ModelMap model, HttpServletRequest request,
			@PathVariable(value = "eventKey") String eventKey, @PathVariable(value = "groupId") String groupId) {

		EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

		model.addAttribute("event", signUp.getEvent());
		model.addAttribute("eventSignUp", signUp);
		model.addAttribute("ticketGroup",
				signUp.getGroups().stream().reduce(new TicketGroup(), ticketGroupReducer(groupId)));

		return "/admin/add-ticketgroup";
	}

	@RequestMapping(value = "/s/admin/{eventKey}/registration/ticketGroup", method = RequestMethod.POST)
	public String addTicketGroup(@PathVariable(value = "eventKey") String eventKey, @Valid TicketGroup ticketGroupForm,
			BindingResult result, HttpServletRequest request) {

		EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

		if (request.getParameter("cancel") != null) {
			return String.format("redirect:/s/admin/%s/registration/", eventKey);
		}

		if (result.hasErrors()) {
			return String.format("/admin/add-ticketgroup", eventKey);
		}

		signUp.getGroups().add(ticketGroupForm);
		ticketGroupForm.setEventSignup(signUp);
		ticketGroupForm.setEvent(signUp.getEvent());

		ticketGroupForm.getCouponCodes().forEach((code) -> {
			code.setTicketGroup(ticketGroupForm);
		});

		eventSignupRepository.saveAndFlush(signUp);

		return String.format("redirect:/s/admin/%s/registration/", eventKey);
	}

	@RequestMapping(value = "/s/admin/{eventKey}/editRegistration", method = RequestMethod.GET)
	public String editRegistrationsShowSearch(ModelMap model, HttpServletRequest request,
			@PathVariable(value = "eventKey") String eventKey) {

		EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

		model.addAttribute("event", signUp.getEvent());
		model.addAttribute("eventSignup", signUp);
		model.addAttribute("registrationSearchForm", new RegistrationSearchForm());
		return "/admin/search-registrations";
	}

	@RequestMapping(value = "/s/admin/{eventKey}/editRegistration/{registrationId}", method = RequestMethod.GET)
	public String editRegistrations(ModelMap model, HttpServletRequest request,
			@PathVariable(value = "eventKey") String eventKey,
			@PathVariable(value = "registrationId") String registrationId) {

		EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);
		model.addAttribute("event", signUp.getEvent());

		RegistrationDetails registerForm = businessService.getRegistrationForm(registrationId);

		Event currentEvent = businessService.getCurrentEvent();
		EventSignup eventSignup = businessService.getEventSignup();

		model.addAttribute("signupRegisterView", new SignupRegisterView(eventSignup));
		model.addAttribute("registrationDetails", registerForm);
		model.addAttribute("paymentStates", RegistrationDetails.PaymentState.values());

		return "/admin/edit-registration";
	}

	@RequestMapping(value = "/s/admin/{eventKey}/editRegistration/{registrationId}/resendEmail", method = RequestMethod.GET)
	public String resendRegistrationsEmail(ModelMap model, HttpServletRequest request,
			@PathVariable(value = "eventKey") String eventKey,
			@PathVariable(value = "registrationId") String registrationId) {

		EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);
		model.addAttribute("event", signUp.getEvent());

		RegistrationDetails registerForm = businessService.getRegistrationForm(registrationId);

		EventSignup eventSignup = businessService.getEventSignup();

		model.addAttribute("signupRegisterView", new SignupRegisterView(eventSignup));
		model.addAttribute("registrationDetails", registerForm);
		model.addAttribute("paymentStates", RegistrationDetails.PaymentState.values());

		businessService.resendRegistrationEmail(registerForm);

		return "/admin/edit-registration";
	}

	@RequestMapping(value = "/s/admin/{eventKey}/editRegistration/{registrationId}", method = RequestMethod.POST)
	public String saveEditedRegistrations(ModelMap model, HttpServletRequest request,
			@PathVariable(value = "eventKey") String eventKey,
			@PathVariable(value = "registrationId") String registrationId, @Valid RegistrationDetails registerForm,
			BindingResult result) {

		EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);
		model.addAttribute("event", signUp.getEvent());

		RegistrationDetails originalForm = businessService.getRegistrationForm(registrationId);

		originalForm.setContactEmailAddress(registerForm.getContactEmailAddress());
		originalForm.setContactName(registerForm.getContactName());
		originalForm.setPaymentState(registerForm.getPaymentState());
		originalForm.setContactPhoneNumber(registerForm.getContactPhoneNumber());
		originalForm.getOrderDetails().removeAll(originalForm.getOrderDetails());
		originalForm.getOrderDetails().addAll(registerForm.getOrderDetails());

		originalForm.getOrderDetails().forEach(detail -> {
			detail.setRegistration(originalForm);
		});

		businessService.updateRegistration(originalForm);

		return "redirect:/s/admin";
	}

	@RequestMapping(value = "/s/admin/{eventKey}/editRegistration", method = RequestMethod.POST)
	public String editRegistrationsShowSearchResults(ModelMap model, HttpServletRequest request,
			@PathVariable(value = "eventKey") String eventKey, @Valid RegistrationSearchForm searchForm,
			BindingResult result) {

		EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

		Set<RegistrationDetails> detailsResults = new HashSet<>();
		Set<TicketOrderDetail> orderDetailsResults = new HashSet<>();

		model.addAttribute("event", signUp.getEvent());
		model.addAttribute("eventSignup", signUp);
		model.addAttribute("registrationSearchForm", searchForm);

		List searchResults = businessService.findRegistrations(searchForm.getEmail(), searchForm.getName(), signUp);

		for (Object object : searchResults) {
			if (object instanceof RegistrationDetails) {
				detailsResults.add((RegistrationDetails) object);
			}

			if (object instanceof TicketOrderDetail) {
				orderDetailsResults.add((TicketOrderDetail) object);
			}
		}

		model.addAttribute("registrations", detailsResults);
		model.addAttribute("tickets", orderDetailsResults);

		return "/admin/search-registrations";
	}

	@RequestMapping(value = "/s/admin/{eventKey}/registration/ticketGroup/{groupId}", method = RequestMethod.POST)
	public String editTicketGroup(@PathVariable(value = "eventKey") String eventKey,
			@PathVariable(value = "groupId") String groupId, @Valid TicketGroup ticketGroupForm, BindingResult result,
			HttpServletRequest request) {

		EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

		if (request.getParameter("cancel") != null) {
			return String.format("redirect:/s/admin/%s/registration/", eventKey);
		}

		if (result.hasErrors()) {
			return String.format("/admin/add-ticketgroup", eventKey);
		}

		TicketGroup group = signUp.getGroups().stream().reduce(new TicketGroup(), ticketGroupReducer(groupId));
		group.setLabel(ticketGroupForm.getLabel());
		group.setCloseDate(ticketGroupForm.getCloseDate());
		group.setOpenDate(ticketGroupForm.getOpenDate());
		group.setCouponCode(ticketGroupForm.getCouponCodes());
		group.setDescription(ticketGroupForm.getDescription());
		group.setMinPurchase(ticketGroupForm.getMinPurchase());
		group.setPrice(ticketGroupForm.getPrice());
		group.setRegisterFormUrl(ticketGroupForm.getRegisterFormUrl());

		eventSignupRepository.saveAndFlush(signUp);

		return String.format("redirect:/s/admin/%s/registration/", eventKey);
	}

	private BinaryOperator<TicketGroup> ticketGroupReducer(String groupId) {
		return (left, right) -> {
			return Long.parseLong(groupId) == right.getId() ? right : left;
		};
	}

}
