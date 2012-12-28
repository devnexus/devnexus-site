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

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.devnexus.ting.core.service.BusinessService;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Controller
public class AdminController {

	@Autowired private BusinessService businessService;
	@Autowired private Validator validator;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;

	private final static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping({"/admin/index"})
	public String execute(ModelMap model) {
		return "/admin/index";
	}

	@RequestMapping({"/admin/update-application-cache"})
	public String updateApplicationCache(ModelMap model) {
		businessService.updateApplicationCacheManifest();
		return "redirect:/s/admin/index";
	}


	@RequestMapping("/logout")
	public String logout(ModelMap model) {

		final SecurityContext context = SecurityContextHolder.getContext();

		if (context.getAuthentication() != null) {
			LOGGER.info("Logging out user..." + context.getAuthentication().getName());
		} else {
			LOGGER.warn("User not logged in.");
		}

		context.setAuthentication(null);

		//super.addActionMessage("You logged out successfully.");

		return "/index";
	}

	@RequestMapping("/login")
	public String login(ModelMap model) {
		return "/login";
	}

}
