/*
 * Copyright 2014-2016 the original author or authors.
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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.aerogear.unifiedpush.JavaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.core.service.CalendarServices;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.ScheduleItem;
import com.devnexus.ting.model.User;
import com.devnexus.ting.model.UserScheduleItem;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * This class will service a users requests for their personalized calendar.
 *
 * @author summers
 * @author Gunnar Hillert
 */
@Controller
public class CalendarController {

	@Value("#{environment.TING_PUSH_APP_ID}")
	private String PUSH_APP_ID;

	@Value("#{environment.TING_PUSH_MASTER_SECRET}")
	private String PUSH_APP_SECRET;

	static {
		System.setProperty("jsse.enableSNIExtension", "false");
	}

	@Autowired
	JavaSender javaSender;

	@Autowired
	CalendarServices calendarService;

	@Autowired
	private BusinessService businessService;

	@RequestMapping(value={"/s/usercalendar"}, method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<UserScheduleItem>>  calendar() throws JsonProcessingException {
		return calendar(businessService.getCurrentEvent().getEventKey());
	}

	@RequestMapping(value={"/s/{eventKey}/usercalendar"}, method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<UserScheduleItem>>  calendar(@PathVariable("eventKey") String eventKey) throws JsonProcessingException {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof  String) {
			headers.add("WWW-Authenticate", "Google realm=\"http://www.devnexus.org\"");
			return new ResponseEntity<List<UserScheduleItem>>(new ArrayList<UserScheduleItem>(), headers, HttpStatus.UNAUTHORIZED);
		}

		final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Event event = businessService.getEventByEventKey(eventKey);

		List<UserScheduleItem> calendar = calendarService.getUserSchedule(user, event);
		return new ResponseEntity<>(calendar, headers, HttpStatus.OK);
	}

	@RequestMapping(value="/s/{eventKey}/usercalendar/{scheduleItemId}", method={RequestMethod.POST, RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<UserScheduleItem> addToCalendar(
			@PathVariable("eventKey") String eventKey,
			@PathVariable("scheduleItemId") Long scheduleItemId,
			HttpServletRequest request) {

		HttpHeaders headers = new HttpHeaders();

		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof  String) {
			headers.add("WWW-Authenticate", "Google realm=\"http://www.devnexus.org\"");
			return new ResponseEntity<>(new UserScheduleItem(), headers, HttpStatus.UNAUTHORIZED);
		}

		final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Event event = businessService.getEventByEventKey(eventKey);

		if (user == null) {
			return new ResponseEntity<>(null, headers, HttpStatus.FORBIDDEN);
		}

		final ScheduleItem scheduleItem = businessService.getScheduleItem(scheduleItemId);

		final UserScheduleItem savedUserScheduleItem = calendarService.addScheduleItemToUserSchedule(user, scheduleItem);

		return new ResponseEntity<>(savedUserScheduleItem, headers, HttpStatus.OK);
	}

	@RequestMapping(value="/s/usercalendar/{id}", method={RequestMethod.POST, RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<UserScheduleItem>  updateCalendar(@PathVariable("id") Long id, HttpServletRequest request) {
		return addToCalendar(businessService.getCurrentEvent().getEventKey(), id, request);
	}

	@RequestMapping(value="/s/{eventKey}/usercalendar/{scheduleItemId}", method={RequestMethod.DELETE})
	@ResponseBody
	public ResponseEntity<UserScheduleItem> removeFromCalendar(
			@PathVariable("eventKey") String eventKey,
			@PathVariable("scheduleItemId") Long scheduleItemId,
			HttpServletRequest request) {

		HttpHeaders headers = new HttpHeaders();

		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof  String) {
			headers.add("WWW-Authenticate", "Google realm=\"http://www.devnexus.org\"");
			return new ResponseEntity<>(new UserScheduleItem(), headers, HttpStatus.UNAUTHORIZED);
		}

		final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Event event = businessService.getEventByEventKey(eventKey);

		if (user == null) {
			return new ResponseEntity<>(null, headers, HttpStatus.FORBIDDEN);
		}

		final ScheduleItem scheduleItem = businessService.getScheduleItem(scheduleItemId);

		calendarService.removeScheduleItemFromUserSchedule(user, scheduleItem);

		return new ResponseEntity<>(null, headers, HttpStatus.OK);
	}

}
