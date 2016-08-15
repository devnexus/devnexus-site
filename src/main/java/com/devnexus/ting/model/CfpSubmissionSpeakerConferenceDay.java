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
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.devnexus.ting.model.cfp.CfpSpeakerAvailability;

/**
*
* @author Gunnar Hillert
*
*/
@Entity
public class CfpSubmissionSpeakerConferenceDay extends BaseModelObject {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@NotNull
	private ConferenceDay conferenceDay;

	@ManyToOne
	@NotNull
	private CfpSubmissionSpeaker cfpSubmissionSpeaker;

	private LocalTime startTime;
	private LocalTime endTime;

	@Type(type = "com.devnexus.ting.core.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.model.cfp.CfpSpeakerAvailability"),
			@Parameter(name = "identifierMethod", value = "getKey"),
			@Parameter(name = "valueOfMethod", value = "fromKey") })
	private CfpSpeakerAvailability cfpSpeakerAvailability;

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
	public CfpSpeakerAvailability getCfpSpeakerAvailabilty() {
		return cfpSpeakerAvailability;
	}
	public void setCfpSpeakerAvailability(CfpSpeakerAvailability cfpSpeakerAvailabilty) {
		this.cfpSpeakerAvailability = cfpSpeakerAvailabilty;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof CfpSubmissionSpeakerConferenceDay)) {
			return false;
		}
		CfpSubmissionSpeakerConferenceDay castOther = (CfpSubmissionSpeakerConferenceDay) other;
		return Objects.equals(conferenceDay, castOther.conferenceDay)
				&& Objects.equals(cfpSubmissionSpeaker, castOther.cfpSubmissionSpeaker);
	}
	@Override
	public int hashCode() {
		return Objects.hash(conferenceDay, cfpSubmissionSpeaker);
	}

}
