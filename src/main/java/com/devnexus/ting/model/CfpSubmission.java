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
package com.devnexus.ting.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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
			@Parameter(name = "enumClass", value = "com.devnexus.ting.model.PresentationType"),
			@Parameter(name = "identifierMethod", value = "getId"),
			@Parameter(name = "valueOfMethod", value = "fromId") })
	private PresentationType presentationType;

	@NotNull
	@Type(type = "com.devnexus.ting.core.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.model.SkillLevel"),
			@Parameter(name = "identifierMethod", value = "getId"),
			@Parameter(name = "valueOfMethod", value = "fromId") })
	private SkillLevel skillLevel;

	@Type(type = "com.devnexus.ting.core.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.model.CfpSubmissionStatusType"),
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

	public String getSlotPreference() {
		return slotPreference;
	}

	public void setSlotPreference(String slotPreference) {
		this.slotPreference = slotPreference;
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
	 * @return the speakers
	 */
	public boolean speakerRequiresTravelCostReimburment() {
		for (CfpSubmissionSpeaker speaker : this.speakers) {
			if (speaker.isMustReimburseTravelCost()) {
				return true;
			}
		}
		return false;
	}

	public String getSpeakerLocation() {
		final Set<String> speakerLocations = new HashSet<String>();

		for (CfpSubmissionSpeaker speaker : this.speakers) {
			speakerLocations.add(speaker.getLocation());
		}

		return StringUtils.collectionToCommaDelimitedString(speakerLocations);
	}

	/**
	 * @param speakers the speakers to set
	 */
	public void setSpeakers(List<CfpSubmissionSpeaker> speakers) {
		this.speakers = speakers;
	}

	public String getSpeakersAsString(boolean firstNameOnly) {
		final StringBuilder stringBuilder = new StringBuilder();
		final Iterator<CfpSubmissionSpeaker> speakerIterator = this.getSpeakers().iterator();

		boolean isFirst = true;

		while(speakerIterator.hasNext()) {
			CfpSubmissionSpeaker speaker = speakerIterator.next();

			if (speakerIterator.hasNext() && !isFirst) {
				stringBuilder.append(", ");
			}
			else if (!speakerIterator.hasNext() && !isFirst) {
				stringBuilder.append(" & ");
			}

			if (firstNameOnly) {
				stringBuilder.append(speaker.getFirstName());
			}
			else {
				stringBuilder.append(speaker.getFirstLastName());
			}

			isFirst = false;
		}
		return stringBuilder.toString();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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

	public Long getSpeakerIdForCfpSpeakerId(Long cfpSubmissionSpeakerId) {
		Assert.notNull(cfpSubmissionSpeakerId, "cfpSubmissionSpeakerId must not be null.");

		for (CfpSubmissionSpeaker speaker : this.getSpeakers()) {
			if (cfpSubmissionSpeakerId.equals(speaker.getId())) {
				return speaker.getSpeaker().getId();
			}
		}

		throw new IllegalStateException("Could not find CFP Submission Speaker for id " + cfpSubmissionSpeakerId);
	}

}

