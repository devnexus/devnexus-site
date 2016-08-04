/*
 * Copyright 2002-2016 the original author or authors.
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for CFP speakers.
 *
 */
@Entity
@FilterDefs({
		@FilterDef(name = "presentationFilter"),
		@FilterDef(name = "presentationFilterEventId", parameters=@ParamDef( name="eventId", type="long" ) )
		})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CfpSubmissionSpeaker extends Person {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1071633978769394025L;

	@Transient
	private Speaker speaker;

	@ManyToOne
	@NotNull
	@XmlTransient
	@JsonIgnore
	private Event event;

	@ManyToOne
	@XmlTransient
	@JsonIgnore
	private User createdByUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@XmlTransient
	@JsonIgnore
	@Cascade(CascadeType.ALL)
	@Valid
	private CfpSpeakerImage cfpSpeakerImage;

	@NotEmpty
	@Size(max=255)
	@Email
	private String email;

	@NotEmpty
	@Size(max=255)
	private String location;

	private boolean mustReimburseTravelCost;

	@NotEmpty
	@Size(max=255)
	private String phone;

	@NotEmpty
	@Size(max=255)
	private String tshirtSize;

	@Filters({
		@Filter(name = "presentationFilter", condition = "EVENT = (select e.ID from EVENTS e where e.CURRENT = 'true')"),
		@Filter(name = "presentationFilterEventId", condition = "EVENT = :eventId")
	})
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="cfpSubmissionSpeakers")
	@XmlTransient
	@JsonIgnore
	@BatchSize(size=20)
	private List<CfpSubmission>cfpSubmissions = new ArrayList<CfpSubmission>(0);

	public CfpSubmissionSpeaker() {
	}

	public CfpSubmissionSpeaker(Long id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the mustReimburseTravelCost
	 */
	public boolean isMustReimburseTravelCost() {
		return mustReimburseTravelCost;
	}

	/**
	 * @param mustReimburseTravelCost the mustReimburseTravelCost to set
	 */
	public void setMustReimburseTravelCost(boolean mustReimburseTravelCost) {
		this.mustReimburseTravelCost = mustReimburseTravelCost;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the tshirtSize
	 */
	public String getTshirtSize() {
		return tshirtSize;
	}

	/**
	 * @param tshirtSize the tshirtSize to set
	 */
	public void setTshirtSize(String tshirtSize) {
		this.tshirtSize = tshirtSize;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public CfpSpeakerImage getCfpSpeakerImage() {
		return cfpSpeakerImage;
	}

	public void setCfpSpeakerImage(CfpSpeakerImage cfpSpeakerImage) {
		this.cfpSpeakerImage = cfpSpeakerImage;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * @return the cfpSubmissions
	 */
	public List<CfpSubmission> getCfpSubmissions() {
		return cfpSubmissions;
	}

	/**
	 * @param cfpSubmissions the cfpSubmissions to set
	 */
	public void setCfpSubmissions(List<CfpSubmission> cfpSubmissions) {
		this.cfpSubmissions = cfpSubmissions;
	}

	/**
	 * @return the createdByUser
	 */
	public User getCreatedByUser() {
		return createdByUser;
	}

	/**
	 * @param createdByUser the createdByUser to set
	 */
	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CfpSubmissionSpeaker [" + super.toString()
				+ ", email=" + email + ", location=" + location
				+ ", mustReimburseTravelCost=" + mustReimburseTravelCost
				+ ", phone=" + phone + ", tshirtSize=" + tshirtSize + "]";
	}

}
