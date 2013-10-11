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
package com.devnexus.ting.core.model;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.devnexus.ting.common.TingUtil;

/**
 * Represents a Call for Papers Submission.
 *
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CfpSubmission extends Person {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1071633978769394025L;

	private File pictureFile2;

	@ManyToOne
	@NotNull
	@XmlTransient
	private Event event;

	@NotEmpty
	@Size(max=255)
	private String title;

	@NotEmpty
	@Size(max=255)
	private String tshirtSize;

	@NotEmpty
	@Size(max=255)
	private String phone;

	@NotEmpty
	@Size(max=255)
	@Email
	private String email;

	@Size(max=1000)
	private String slotPreference;

	@NotEmpty
	@Size(max=10000)
	private String description;

	@NotEmpty
	@Size(max=255)
	private String topic;

	private boolean sessionRecordingApproved = true;

	@NotNull
	@Type(type = "com.hillert.apptools.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.core.model.PresentationType"),
			@Parameter(name = "identifierMethod", value = "getId"),
			@Parameter(name = "valueOfMethod", value = "fromId") })
	private PresentationType presentationType;

	@NotNull
	@Type(type = "com.hillert.apptools.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.core.model.SkillLevel"),
			@Parameter(name = "identifierMethod", value = "getId"),
			@Parameter(name = "valueOfMethod", value = "fromId") })
	private SkillLevel skillLevel;

	public CfpSubmission() {
	}

	public CfpSubmission(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Speaker [firstName=" + firstName + ", id=" + id + ", lastName="
				+ lastName + "]";
	}

	public String getDescription() {
		return this.description;
	}

	public String getDescriptionAsHtml() {
		return TingUtil.getMarkDownProcessor().markdownToHtml(this.description);
	}

	public Event getEvent() {
		return event;
	}

	public String getTitle() {
		return this.title;
	}

	public void setDescription(String abstract_) {
		this.description = abstract_;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PresentationType getPresentationType() {
		return presentationType;
	}

	public void setPresentationType(PresentationType presentationType) {
		this.presentationType = presentationType;
	}

	public SkillLevel getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(SkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}

	public boolean isSessionRecordingApproved() {
		return sessionRecordingApproved;
	}

	public void setSessionRecordingApproved(boolean sessionRecordingApproved) {
		this.sessionRecordingApproved = sessionRecordingApproved;
	}

	public String getTshirtSize() {
		return tshirtSize;
	}

	public void setTshirtSize(String tshirtSize) {
		this.tshirtSize = tshirtSize;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSlotPreference() {
		return slotPreference;
	}

	public void setSlotPreference(String slotPreference) {
		this.slotPreference = slotPreference;
	}

	public File getPictureFile2() {
		return pictureFile2;
	}

	public void setPictureFile2(File pictureFile) {
		this.pictureFile2 = pictureFile;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}

