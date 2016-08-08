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
package com.devnexus.ting.model;

import java.time.LocalTime;

/**
*
* @author Gunnar Hillert
*
*/
public class CfpSubmissionSpeakerConferenceDay {

	private ConferenceDay conferenceDay;
	private CfpSubmissionSpeaker cfpSubmissionSpeaker;

	private LocalTime startTime;
	private LocalTime endTime;
	private boolean availableAllDay = true;

	public ConferenceDay getConferenceDay() {
		return conferenceDay;
	}
	public void setConferenceDay(ConferenceDay conferenceDay) {
		this.conferenceDay = conferenceDay;
	}
	public CfpSubmissionSpeaker getCfpSubmissionSpeaker() {
		return cfpSubmissionSpeaker;
	}
	public void setCfpSubmissionSpeaker(CfpSubmissionSpeaker cfpSubmissionSpeaker) {
		this.cfpSubmissionSpeaker = cfpSubmissionSpeaker;
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
	public boolean isAvailableAllDay() {
		return availableAllDay;
	}
	public void setAvailableAllDay(boolean availableAllDay) {
		this.availableAllDay = availableAllDay;
	}

}
