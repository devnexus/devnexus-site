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
package com.devnexus.ting.core.applicationlistener;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;

import com.devnexus.ting.core.service.UserService;
import com.devnexus.ting.model.User;

public class SecurityEventListener implements
									ApplicationListener < AbstractAuthenticationEvent > {

	@Autowired
	private UserService userService;

	/**
	 *   Initialize Logging.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityEventListener.class);

	@Override
	public void onApplicationEvent(AbstractAuthenticationEvent event) {

		if (event instanceof AuthenticationSuccessEvent) {

			final AuthenticationSuccessEvent successEvent = (AuthenticationSuccessEvent) event;
			LOGGER.info("Successful Authentication of User: " + successEvent.getAuthentication().getName());
			successEvent.getAuthentication();

			final User user = (User)successEvent.getAuthentication().getPrincipal();
			userService.trackUserLogin(user);

		} else if (event instanceof InteractiveAuthenticationSuccessEvent) {

			final InteractiveAuthenticationSuccessEvent successEvent = (InteractiveAuthenticationSuccessEvent) event;
			LOGGER.info("Successful Interactive Authentication of User: " + successEvent.getAuthentication().getName());

		} else if (event instanceof AbstractAuthenticationFailureEvent) {

			final AbstractAuthenticationFailureEvent failureEvent = (AbstractAuthenticationFailureEvent) event;
			LOGGER.warn("Authentication Failure for User '"
					+ failureEvent.getAuthentication().getPrincipal() + "' "
					+ failureEvent.getException().getMessage(), failureEvent.getException());

		} else {

			/*
			 * Since we really don't care about other events, we log it for
			 *  now, but because of that we don't throw an explicit exception.
			 */
			LOGGER.error("Unhandled AuthenticationEvent (" + event.getClass().getName() + ") for user '"
					+ event.getAuthentication().getPrincipal() + "':"
					+ event.toString());
		}
	}
}
