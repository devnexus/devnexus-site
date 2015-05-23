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
import com.devnexus.ting.model.PurchaseGroup;
import com.devnexus.ting.model.PurchaseItem;
import com.devnexus.ting.repository.EventSignupRepository;
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

    @RequestMapping(value = "/admin/{eventKey}/registration/purchaseGroup", method = RequestMethod.GET)
    public String showAddPurchaseGroupForm(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey) {

        EventSignup signUp = eventSignupController.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("eventSignUp", signUp);
        model.addAttribute("purchaseGroup", new PurchaseGroup());

        return "/admin/add-purchasegroup";
    }
    
    
    @RequestMapping(value = "/admin/{eventKey}/registration/purchaseItem", method = RequestMethod.GET)
    public String prepareAddPurchaseItem(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey) {

        EventSignup signUp = eventSignupController.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("eventSignUp", signUp);
        model.addAttribute("purchaseItem", new PurchaseItem());

        return "/admin/add-purchaseitem";
    }

    @RequestMapping(value = "/admin/{eventKey}/registration/purchaseGroup", method = RequestMethod.POST)
    public String addPurchaseGroup(@PathVariable(value = "eventKey") String eventKey, @Valid PurchaseGroup purchaseGroupForm, BindingResult result, HttpServletRequest request) {

        EventSignup signUp = eventSignupController.getByEventKey(eventKey);

        if (request.getParameter("cancel") != null) {
            return String.format("redirect:/s/admin/%s/registration/", eventKey);
        }

        if (result.hasErrors()) {
            return String.format("/admin/add-purchasegroup", eventKey);
        }

        signUp.getGroups().add(purchaseGroupForm);
        purchaseGroupForm.setEventSignup(signUp);
        purchaseGroupForm.setEvent(signUp.getEvent());
        eventSignupController.saveAndFlush(signUp);
        
        
        return String.format("redirect:/s/admin/%s/registration/", eventKey);
    }

}
