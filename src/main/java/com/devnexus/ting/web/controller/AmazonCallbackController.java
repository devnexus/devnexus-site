/*
 * Copyright 2016 the original author or authors.
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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Summers Pittman
 */
@Controller
@org.springframework.transaction.annotation.Transactional
@RequestMapping("/s/amazon/**.act")
public class AmazonCallbackController {

	private static final Log LOG = LogFactory.getLog(AmazonCallbackController.class);

	@RequestMapping(value = "/s/amazon/payment.act", method = RequestMethod.POST)
	public String processPayment(HttpServletRequest request, String recipientEmail, String referenceId, String status) {

		LOG.info("Received message form Amazon:" + formatHttpRequest(request));
		Long referenceLong = 0l;
		// Load user amazon
		// Set amazon's credentials
		// Set amazon to security context
		// SecurityContextHolder.getContext().setAuthentication(new
		// UsernamePasswordAuthenticationToken("System", "System",
		// ArrayUtils.makeList(new UserAuthority AuthorityType.ADMIN)));

		try {
			referenceLong = Long.parseLong(referenceId);
		} catch (NumberFormatException nfe) {
			LOG.error("The reference ID:" + referenceId + " was not a number.");
			LOG.error("Logging amazon information and returning.");
			return "/amazon/OK.jsp";
		}
		if (status.compareToIgnoreCase("PF") == 0) {// problems capt'm. Notify
													// someone
			LOG.info("Payment Failed :");
			// set unpaid
			// set unpending
			// save
		} else {// Money is coming, register the game
			LOG.info("Paying for registration:");
			// set paid
			// set not pending
			// save
		}

		return "OK";
	}

	private String formatHttpRequest(HttpServletRequest request) {

		StringBuilder builder = new StringBuilder();

		for (Object name : request.getParameterMap().keySet()) {
			builder.append(name).append(":").append(((String[]) request.getParameterMap().get(name))[0]);
		}

		return builder.toString();

	}
}
