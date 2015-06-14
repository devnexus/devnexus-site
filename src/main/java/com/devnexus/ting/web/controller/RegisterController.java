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
package com.devnexus.ting.web.controller;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.EventSignup;
import com.devnexus.ting.model.ScheduleItemList;
import com.devnexus.ting.model.SpeakerList;
import com.devnexus.ting.web.form.RegisterForm;
import com.devnexus.ting.model.TicketGroup;
import com.devnexus.ting.web.form.RegisterFormPageTwo;
import com.devnexus.ting.web.form.SignupRegisterView;
import com.devnexus.ting.web.form.TickerOrderDetail;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * Display Register Form
 *
 * @author summers
 */
@Controller
public class RegisterController {

    @Autowired
    private BusinessService businessService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegistrationFormForCurrentEvent(Model model) {
        Event currentEvent = businessService.getCurrentEvent();
        EventSignup eventSignup = businessService.getEventSignup();
        prepareHeader(currentEvent, model);
        model.addAttribute("signupRegisterView", new SignupRegisterView(eventSignup));
        
        model.addAttribute("registerForm", new RegisterForm());
        
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String validateInitialFormAndPrepareDetailsForm(Model model,@Valid RegisterForm registerForm, BindingResult result) {

        TicketGroup ticketGroup = businessService.getTicketGroup(registerForm.getTicketGroup());
        Event currentEvent = businessService.getCurrentEvent();
        EventSignup eventSignup = businessService.getEventSignup();
        prepareHeader(currentEvent, model);
        model.addAttribute("signupRegisterView", new SignupRegisterView(eventSignup));
        
        if (registerForm.getTicketCount() < ticketGroup.getMinPurchase()) {
            result.addError(new FieldError("registerForm", "ticketCount", "You need to buy more tickets for this Registration Type."));
        }

        if (!StringUtils.isEmpty(ticketGroup.getCouponCode())) {
            if (!ticketGroup.getCouponCode().equals(registerForm.getCouponCode()))
            result.addError(new FieldError("registerForm", "couponCode", "Invalid Coupon Code."));
        }
        
        
        if (result.hasErrors()) {
            model.addAttribute("registerForm", registerForm);
            return "register";
        }
        
        
        RegisterFormPageTwo registerFormPageTwo = new RegisterFormPageTwo();
        registerFormPageTwo.copyPageOne(registerForm);
        
        model.addAttribute("registerFormPageTwo", registerFormPageTwo);
        
        
        return "register2";
        
        
    }

    @RequestMapping(value = "/registerPageTwo", method = RequestMethod.POST)
    public String validateDetailsForm(Model model,@Valid RegisterFormPageTwo registerForm, BindingResult result) {

        TicketGroup ticketGroup = businessService.getTicketGroup(registerForm.getTicketGroup());
        Event currentEvent = businessService.getCurrentEvent();
        EventSignup eventSignup = businessService.getEventSignup();
        prepareHeader(currentEvent, model);
        model.addAttribute("signupRegisterView", new SignupRegisterView(eventSignup));
        model.addAttribute("registerFormPageTwo", registerForm);
        
        if (result.hasErrors()) {
            return "register2";
        }
        
        if (registerForm.getTicketCount() < ticketGroup.getMinPurchase()) {
            result.addError(new FieldError("registerFormPageTwo", "ticketCount", "You need to buy more tickets for this Registration Type."));
        }

        if (!StringUtils.isEmpty(ticketGroup.getCouponCode())) {
            if (!ticketGroup.getCouponCode().equals(registerForm.getCouponCode()))
            result.addError(new FieldError("registerFormPageTwo", "couponCode", "Invalid Coupon Code."));
        }
        
        for (int index = 0; index < registerForm.getOrderDetails().size(); index++) {
            TickerOrderDetail orderDetails = registerForm.getOrderDetails().get(index);
            
            if (StringUtils.isEmpty(orderDetails.getFirstName())) {
                result.addError(new FieldError("registerFormPageTwo", "orderDetails["+index+"].firstName", "First Name is required."));    
            }
            
            if (StringUtils.isEmpty(orderDetails.getLastName())) {
                result.addError(new FieldError("registerFormPageTwo", "orderDetails["+index+"].lastName", "Last Name is required."));    
            }
            
            if (StringUtils.isEmpty(orderDetails.getEmailAddress())) {
                result.addError(new FieldError("registerFormPageTwo", "orderDetails["+index+"].emailAddress", "Email Address is required."));    
            }
            
        }
        
        if (result.hasErrors()) {
            return "register2";
        }
        
        return "index";
        
        
    }
    
    private void prepareHeader(Event event, Model model) {
        final ScheduleItemList scheduleItemList = businessService.getScheduleForEvent(event.getId());
        
        model.addAttribute("event", event);
        SpeakerList speakers = new SpeakerList();
        speakers.setSpeakers(businessService.getSpeakersForEvent(event.getId()));
        model.addAttribute("speakerList", speakers);

        model.addAttribute("scheduleItemList", scheduleItemList);

    }

}
