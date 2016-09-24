/*
 * Copyright 2015 the original author or authors.
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
package com.devnexus.ting.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.devnexus.ting.model.EventSignup;
import com.devnexus.ting.model.TicketGroup;

/**
 *
 * @author Summers Pittman
 */
public class SignupRegisterView {

	private final EventSignup signUp;
	private final Date today = new Date();

	public SignupRegisterView(EventSignup signUp) {
		this.signUp = signUp;
	}

	public List<TicketGroup> getGroups() {
		return new ArrayList<TicketGroup>(signUp.getGroups().stream().filter(item -> {
			return item.getCloseDate().after(today) && item.getOpenDate().before(today);
		}).sorted((ticket, other) -> {
			return ticket.getLabel().compareTo(other.getLabel());
		}).collect(Collectors.toList()));
	}

}
