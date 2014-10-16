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
package com.devnexus.ting.core.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for CFP speakers.
 *
 */
@Entity
@Cacheable()
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE) //, include="non-lazy"
@FilterDefs({
		@FilterDef(name = "presentationFilter"),
		@FilterDef(name = "presentationFilterEventId", parameters=@ParamDef( name="eventId", type="long" ) )
		})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CfpSubmissionSpeaker extends Person {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1071633978769394025L;

	@ManyToOne
	@NotNull
	@XmlTransient
	private CfpSubmission cfpSubmission;

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

	public CfpSubmissionSpeaker() {
	}

	public CfpSubmissionSpeaker(Long id) {
		this.id = id;
	}

	/**
	 * @return the cfpSubmission
	 */
	public CfpSubmission getCfpSubmission() {
		return cfpSubmission;
	}

	/**
	 * @param cfpSubmission the cfpSubmission to set
	 */
	public void setCfpSubmission(CfpSubmission cfpSubmission) {
		this.cfpSubmission = cfpSubmission;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CfpSubmissionSpeaker [cfpSubmission=" + cfpSubmission
				+ ", email=" + email + ", location=" + location
				+ ", mustReimburseTravelCost=" + mustReimburseTravelCost
				+ ", phone=" + phone + ", tshirtSize=" + tshirtSize + "]";
	}

}
