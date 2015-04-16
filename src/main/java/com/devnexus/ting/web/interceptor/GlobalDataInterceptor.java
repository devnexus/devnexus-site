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
package com.devnexus.ting.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.Event;

public class GlobalDataInterceptor implements HandlerInterceptor {

	@Autowired
	private BusinessService businessService;

	@Autowired private Environment environment;

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {

		final List<Event>events = businessService.getAllNonCurrentEvents();
		final Event currentEvent = businessService.getCurrentEvent();
		request.setAttribute("eventsForMenu", events);
		request.setAttribute("currentEvent", currentEvent);

		final String cfpState = environment.getProperty("cfpState.state");
		request.setAttribute("cfpState", cfpState);

		final String registrationState = environment.getProperty("registration.state");
		request.setAttribute("registrationState", registrationState);

		return true;
	}

}
