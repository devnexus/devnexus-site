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


import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.devnexus.ting.core.service.BusinessService;

/**
 * 
 * Used to manage registration forms and validation/combination rules.
 *
 * @author Summers Pittman
 *
 */
@Controller("adminRegistrationController")
public class RegistrationController {

	@Autowired private BusinessService businessService;

	@Autowired private Validator validator;

	@RequestMapping(value="/admin/registration", method=RequestMethod.GET)
	public String getSpeakers(ModelMap model, HttpServletRequest request,
			@RequestParam(value="eventId", required=false) Long eventId) {

		return "/admin/manage-registration";
	}

}
