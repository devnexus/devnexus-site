/*
 * Copyright 2002-2013 the original author or authors.
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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.Event;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheStats;

/**
 * Main Admin Controller.
 *
 * @author Gunnar Hillert
 *
 */
@Controller
public class AdminController {

	@Autowired private BusinessService businessService;
	@Autowired private Validator validator;
	@Autowired GuavaCacheManager cacheManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping({"/admin", "/admin/"})
	public String redirectToAdmin(ModelMap model) {
		return "redirect:/s/admin/index";
	}

	@RequestMapping({"/admin/index"})
	public String execute(ModelMap model) {
		final Event currentEvent = businessService.getCurrentEvent();
		model.addAttribute("eventKey", currentEvent.getEventKey());
		return "redirect:/s/admin/{eventKey}/index";
	}

	@RequestMapping("/admin/{eventKey}/index")
	public String getSpeakersForEvent(@PathVariable("eventKey") String eventKey, Model model) {
		final Event event = businessService.getEventByEventKey(eventKey);
		model.addAttribute("event", event);
		model.addAttribute("events", businessService.getAllEventsOrderedByName());

		final Collection<String> cacheNames = cacheManager.getCacheNames();
		final Map<String, CacheStats> cacheStats = new HashMap<>();

		for (String cacheName : cacheNames) {
			Cache c = (Cache) cacheManager.getCache(cacheName).getNativeCache();
			LOGGER.warn("Cache Stats for '{}' : {}", cacheName, c.stats().toString());
			cacheStats.put(cacheName, c.stats());
		}
		model.addAttribute("cacheStats", cacheStats);
		return "/admin/index";
	}

	@RequestMapping(value="/admin/index", method=RequestMethod.POST)
	public String changeEvent(@ModelAttribute("event") Event event,
			BindingResult bindingResult,
			ModelMap model) {
		model.addAttribute("eventKey", businessService.getEvent(event.getId()).getEventKey());
		return "redirect:/s/admin/{eventKey}/index";
	}

	@RequestMapping({"/admin/update-application-cache"})
	public String updateApplicationCache(ModelMap model) {
		businessService.updateApplicationCacheManifest();
		return "redirect:/s/admin/index";
	}

	@RequestMapping({"/admin/reset-spring-cache"})
	public String resetSpringCache(ModelMap model) {
		Collection<String> cacheNames = cacheManager.getCacheNames();
		LOGGER.warn("Clearing caches: {}", StringUtils.collectionToCommaDelimitedString(cacheNames));
		for (String cacheName : cacheNames) {
			cacheManager.getCache(cacheName).clear();
		}
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
