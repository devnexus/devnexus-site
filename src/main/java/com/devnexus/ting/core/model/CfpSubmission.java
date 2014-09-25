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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
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
 * @author Gunnar Hillert
 *
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CfpSubmission extends BaseModelObject {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1071633978769394025L;

	private File pictureFile2;

	@ManyToOne
	@NotNull
	@XmlTransient
	private Event event;

	@Valid
	@OrderBy("lastName ASC")
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="cfpSubmission")
	private List<CfpSubmissionSpeaker>speakers = new ArrayList<CfpSubmissionSpeaker>(0);

	@NotEmpty
	@Size(max=255)
	private String title;

	@Deprecated
	@Size(max=10000)
	protected String bio;

	@Deprecated
	@Size(max=255)
	protected String firstName;

	@Deprecated
	@Size(max=255)
	protected String lastName;

	@ManyToOne(fetch = FetchType.LAZY)
	@XmlTransient
	@Deprecated
	protected FileData picture;

	@Deprecated
	@Size(max=255)
	protected String twitterId;

	@Deprecated
	@Size(max=255)
	protected String googlePlusId;

	@Deprecated
	@Size(max=255)
	protected String linkedInId;

	@Deprecated
	@Size(max=255)
	protected String lanyrdId;

	@Deprecated
	@Size(max=255)
	protected String githubId;

	@Deprecated
	@Size(max=255)
	private String tshirtSize;

	@Deprecated
	@Size(max=255)
	private String phone;

	@Deprecated
	@Size(max=255)
	private String location;

	@Deprecated
	private boolean mustReimburseTravelCost;

	@Deprecated
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
	@Type(type = "com.devnexus.ting.core.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.core.model.PresentationType"),
			@Parameter(name = "identifierMethod", value = "getId"),
			@Parameter(name = "valueOfMethod", value = "fromId") })
	private PresentationType presentationType;

	@NotNull
	@Type(type = "com.devnexus.ting.core.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.core.model.SkillLevel"),
			@Parameter(name = "identifierMethod", value = "getId"),
			@Parameter(name = "valueOfMethod", value = "fromId") })
	private SkillLevel skillLevel;

	@Type(type = "com.devnexus.ting.core.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.core.model.CfpSubmissionStatusType"),
			@Parameter(name = "identifierMethod", value = "getKey"),
			@Parameter(name = "valueOfMethod", value = "fromKey") })
	private CfpSubmissionStatusType status;

	public CfpSubmission() {
	}

	public CfpSubmission(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CfpSubmission [event=" + event + ", # speakers=" + speakers.size()
				+ ", title=" + title + ", topic=" + topic
				+ ", sessionRecordingApproved=" + sessionRecordingApproved
				+ ", presentationType=" + presentationType + ", skillLevel="
				+ skillLevel + ", status=" + status + "]";
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

	@Deprecated
	public String getTshirtSize() {
		return tshirtSize;
	}

	@Deprecated
	public void setTshirtSize(String tshirtSize) {
		this.tshirtSize = tshirtSize;
	}

	@Deprecated
	public String getPhone() {
		return phone;
	}

	@Deprecated
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Deprecated
	public String getEmail() {
		return email;
	}

	@Deprecated
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSlotPreference() {
		return slotPreference;
	}

	public void setSlotPreference(String slotPreference) {
		this.slotPreference = slotPreference;
	}

	@Deprecated
	public File getPictureFile2() {
		return pictureFile2;
	}

	@Deprecated
	public void setPictureFile2(File pictureFile) {
		this.pictureFile2 = pictureFile;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public CfpSubmissionStatusType getStatus() {
		return status;
	}

	public void setStatus(CfpSubmissionStatusType status) {
		this.status = status;
	}

	/**
	 * @return the location
	 */
	@Deprecated
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	@Deprecated
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the mustReimburseTravelCost
	 */
	@Deprecated
	public boolean isMustReimburseTravelCost() {
		return mustReimburseTravelCost;
	}

	/**
	 * @param mustReimburseTravelCost the mustReimburseTravelCost to set
	 */
	@Deprecated
	public void setMustReimburseTravelCost(boolean mustReimburseTravelCost) {
		this.mustReimburseTravelCost = mustReimburseTravelCost;
	}

	/**
	 * @return the speakers
	 */
	public List<CfpSubmissionSpeaker> getSpeakers() {
		return speakers;
	}

	public CfpSubmissionSpeaker getSpeakerById(Long id) {
		for (CfpSubmissionSpeaker speaker : this.speakers) {
			if (speaker.getId() != null && speaker.getId().equals(id)) {
				return speaker;
			}
		}
		return null;
	}

	/**
	 * @param speakers the speakers to set
	 */
	public void setSpeakers(List<CfpSubmissionSpeaker> speakers) {
		this.speakers = speakers;
	}

	/**
	 * @return the bio
	 */
	@Deprecated
	public String getBio() {
		return bio;
	}

	/**
	 * @param bio the bio to set
	 */
	@Deprecated
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * @return the firstName
	 */
	@Deprecated
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	@Deprecated
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	@Deprecated
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	@Deprecated
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the picture
	 */
	@Deprecated
	public FileData getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	@Deprecated
	public void setPicture(FileData picture) {
		this.picture = picture;
	}

	/**
	 * @return the twitterId
	 */
	@Deprecated
	public String getTwitterId() {
		return twitterId;
	}

	/**
	 * @param twitterId the twitterId to set
	 */
	@Deprecated
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	/**
	 * @return the googlePlusId
	 */
	@Deprecated
	public String getGooglePlusId() {
		return googlePlusId;
	}

	/**
	 * @param googlePlusId the googlePlusId to set
	 */
	@Deprecated
	public void setGooglePlusId(String googlePlusId) {
		this.googlePlusId = googlePlusId;
	}

	/**
	 * @return the linkedInId
	 */
	@Deprecated
	public String getLinkedInId() {
		return linkedInId;
	}

	/**
	 * @param linkedInId the linkedInId to set
	 */
	@Deprecated
	public void setLinkedInId(String linkedInId) {
		this.linkedInId = linkedInId;
	}

	/**
	 * @return the lanyrdId
	 */
	@Deprecated
	public String getLanyrdId() {
		return lanyrdId;
	}

	/**
	 * @param lanyrdId the lanyrdId to set
	 */
	@Deprecated
	public void setLanyrdId(String lanyrdId) {
		this.lanyrdId = lanyrdId;
	}

	/**
	 * @return the githubId
	 */
	@Deprecated
	public String getGithubId() {
		return githubId;
	}

	/**
	 * @param githubId the githubId to set
	 */
	@Deprecated
	public void setGithubId(String githubId) {
		this.githubId = githubId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bio == null) ? 0 : bio.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime
				* result
				+ ((presentationType == null) ? 0 : presentationType.hashCode());
		result = prime * result + (sessionRecordingApproved ? 1231 : 1237);
		result = prime * result
				+ ((skillLevel == null) ? 0 : skillLevel.hashCode());
		result = prime * result
				+ ((slotPreference == null) ? 0 : slotPreference.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CfpSubmission other = (CfpSubmission) obj;
		if (bio == null) {
			if (other.bio != null)
				return false;
		} else if (!bio.equals(other.bio))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (presentationType != other.presentationType)
			return false;
		if (sessionRecordingApproved != other.sessionRecordingApproved)
			return false;
		if (skillLevel != other.skillLevel)
			return false;
		if (slotPreference == null) {
			if (other.slotPreference != null)
				return false;
		} else if (!slotPreference.equals(other.slotPreference))
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}

}

