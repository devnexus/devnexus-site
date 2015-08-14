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

import com.devnexus.ting.model.EventSignup;
import com.devnexus.ting.model.TicketGroup;
import com.devnexus.ting.repository.EventSignupRepository;
import java.util.function.BinaryOperator;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

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
    private EventSignupRepository eventSignupController;

    @RequestMapping(value = "/admin/{eventKey}/registration", method = RequestMethod.GET)
    public String loadRegistration(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey) {

        EventSignup signUp = eventSignupController.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("eventSignup", signUp);

        return "/admin/manage-registration";
    }

    @RequestMapping(value = "/admin/{eventKey}/registration/ticketGroup", method = RequestMethod.GET)
    public String showAddTicketGroupForm(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey) {

        EventSignup signUp = eventSignupController.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("eventSignUp", signUp);
        model.addAttribute("ticketGroup", new TicketGroup());

        return "/admin/add-ticketgroup";
    }

    
    @RequestMapping(value = "/admin/{eventKey}/invoicing", method = RequestMethod.GET)
    public String showInvoicing(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey) {

        EventSignup signUp = eventSignupController.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        
        return "/admin/invoicing";
    }
    
    @RequestMapping(value = "/admin/{eventKey}/dashboard", method = RequestMethod.GET)
    public String showDashboard(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey) {

        EventSignup signUp = eventSignupController.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        
        return "/admin/dashboard";
    }

    
    @RequestMapping(value = "/admin/{eventKey}/registration/ticketGroup/{groupId}", method = RequestMethod.GET)
    public String prepareEditTicketGroupForm(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey, @PathVariable(value = "groupId") String groupId) {

        EventSignup signUp = eventSignupController.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("eventSignUp", signUp);
        model.addAttribute("ticketGroup", signUp.getGroups().stream().reduce(new TicketGroup(), ticketGroupReducer(groupId)));

        return "/admin/add-ticketgroup";
    }

    @RequestMapping(value = "/admin/{eventKey}/registration/ticketGroup", method = RequestMethod.POST)
    public String addTicketGroup(@PathVariable(value = "eventKey") String eventKey, @Valid TicketGroup ticketGroupForm, BindingResult result, HttpServletRequest request) {

        EventSignup signUp = eventSignupController.getByEventKey(eventKey);

        if (request.getParameter("cancel") != null) {
            return String.format("redirect:/s/admin/%s/registration/", eventKey);
        }

        if (result.hasErrors()) {
            return String.format("/admin/add-ticketgroup", eventKey);
        }

        signUp.getGroups().add(ticketGroupForm);
        ticketGroupForm.setEventSignup(signUp);
        ticketGroupForm.setEvent(signUp.getEvent());
        eventSignupController.saveAndFlush(signUp);

        return String.format("redirect:/s/admin/%s/registration/", eventKey);
    }

    @RequestMapping(value = "/admin/{eventKey}/registration/ticketGroup/{groupId}", method = RequestMethod.POST)
    public String editTicketGroup(@PathVariable(value = "eventKey") String eventKey, @PathVariable(value = "groupId") String groupId, @Valid TicketGroup ticketGroupForm, BindingResult result, HttpServletRequest request) {

        EventSignup signUp = eventSignupController.getByEventKey(eventKey);

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
        
        eventSignupController.saveAndFlush(signUp);

        return String.format("redirect:/s/admin/%s/registration/", eventKey);
    }

    
    private BinaryOperator<TicketGroup> ticketGroupReducer(String groupId) {
        return (left, right) -> {
            return Long.parseLong(groupId) == right.getId() ? right : left;
        };
    }
}
