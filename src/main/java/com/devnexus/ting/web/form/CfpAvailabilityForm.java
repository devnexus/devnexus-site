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
package com.devnexus.ting.web.form;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.devnexus.ting.model.ConferenceDay;
import com.devnexus.ting.model.cfp.CfpSpeakerAvailability;

/**
 *
 * @author Gunnar Hillert
 *
 */
public class CfpAvailabilityForm {

	private ConferenceDay conferenceDay;
	private CfpSpeakerAvailability availabilitySelection;

	@DateTimeFormat(pattern="kk:mm")
	private LocalTime startTime;

	@DateTimeFormat(pattern="kk:mm")
	private LocalTime endTime;

	public ConferenceDay getConferenceDay() {
		return conferenceDay;
	}
	public void setConferenceDay(ConferenceDay conferenceDay) {
		this.conferenceDay = conferenceDay;
	}
	public CfpSpeakerAvailability getAvailabilitySelection() {
		return availabilitySelection;
	}
	public void setAvailabilitySelection(CfpSpeakerAvailability availabilitySelection) {
		this.availabilitySelection = availabilitySelection;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

}

