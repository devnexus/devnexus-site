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
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.springframework.util.StringUtils;

import com.devnexus.ting.common.TingUtil;


/**
 * The persistent class for the presentations database table.
 *
 * @author Gunnar Hillert
 */
@Entity
@Cacheable()
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Table(appliesTo = "PRESENTATIONS", indexes = {@Index(name = "PRESENTATION_IDX", columnNames = {"TITLE"})})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Presentation extends BaseModelObject implements Comparable<Presentation> {
	private static final long serialVersionUID = 1L;

	@Size(max = 255)
	private String audioLink;

	@Size(max = 10000)
	private String description;

	@ManyToOne
	@NotNull
	@XmlTransient
	private Event event;

	@ManyToOne(fetch = FetchType.LAZY)
	@XmlTransient
	@Cascade(CascadeType.ALL)
	@Valid
	private FileData presentationFile;

	@Size(max = 255)
	private String presentationLink;

	@ManyToMany(fetch=FetchType.LAZY)
	@OrderBy("lastName ASC")
	private List<Speaker>speakers = new ArrayList<Speaker>(0);

	@Size(max = 255)
	private String title;

	private transient String tagsAsText;

	@Type(type = "com.devnexus.ting.core.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.model.PresentationType"),
			@Parameter(name = "identifierMethod", value = "getId"),
			@Parameter(name = "valueOfMethod", value = "fromId")})
	private PresentationType presentationType;

	@Type(type = "com.devnexus.ting.core.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.model.SkillLevel"),
			@Parameter(name = "identifierMethod", value = "getId"),
			@Parameter(name = "valueOfMethod", value = "fromId")})
	private SkillLevel skillLevel;

	@ManyToOne
	@JoinColumn(name = "TRACK_ID")
	private Track track;

	@OneToOne(mappedBy = "presentation")
	@XmlTransient
	private ScheduleItem scheduleItem;

	@ManyToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY)
	private Set<PresentationTag>presentationTags = new HashSet<>(0);

	@XmlTransient
	private Long cfpId;

	//~~~~Constructors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public Presentation() {
	}

	//~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public ScheduleItem getScheduleItem() {
		return scheduleItem;
	}

	public void setScheduleItem(ScheduleItem scheduleItem) {
		this.scheduleItem = scheduleItem;
	}

	public String getAudioLink() {
		return audioLink;
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

	public FileData getPresentationFile() {
		return presentationFile;
	}

	public String getPresentationLink() {
		return this.presentationLink;
	}

	public String getTitle() {
		return this.title;
	}

	public void setAudioLink(String audioLink) {
		this.audioLink = audioLink;
	}

	public void setDescription(String abstract_) {
		this.description = abstract_;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setPresentationFile(FileData presentationFile) {
		this.presentationFile = presentationFile;
	}

	public void setPresentationLink(String presentationLink) {
		this.presentationLink = presentationLink;
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

	public Room getRoom() {
		if (scheduleItem != null) {
			return scheduleItem.getRoom();
		} else {
			return null;
		}
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public Set<PresentationTag> getPresentationTags() {
		return presentationTags;
	}

	public void setPresentationTags(Set<PresentationTag> presentationTags) {
		this.presentationTags = presentationTags;
	}

	public String getTagsAsText() {
		return tagsAsText;
	}

	public void setTagsAsText(String tagsAsText) {
		this.tagsAsText = tagsAsText;
	}

	public void convertPresentationTagsToText() {
		final List<String> tagAsString = new ArrayList<>();
		for (PresentationTag presentationTag : this.presentationTags) {
			tagAsString.add(presentationTag.getName());
		}
		this.tagsAsText = StringUtils.collectionToCommaDelimitedString(tagAsString);
	}

	/**
	 * @return the speakers
	 */
	public List<Speaker> getSpeakers() {
		return speakers;
	}

	/**
	 * @param speakers the speakers to set
	 */
	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}

	public Long getCfpId() {
		return cfpId;
	}

	public void setCfpId(Long cfpId) {
		this.cfpId = cfpId;
	}

	@Override
	public int compareTo(Presentation o) {
		if (presentationType == PresentationType.KEYNOTE && o.presentationType == PresentationType.BREAKOUT) {
			return 1;
		} else if (presentationType == PresentationType.BREAKOUT && o.presentationType == PresentationType.KEYNOTE) {
			return -1;
		}

		if (getRoom() == null) {
			if (o.getRoom() != null) {
				return -1;
			} else {
				return title.compareTo(o.title);
			}
		} else if ((o.getRoom() == null)) {
			return 1;
		} else {
			if (o.getRoom().compareTo(getRoom()) == 0) {
				return title.compareTo(o.title);
			} else {
				return getRoom().compareTo(o.getRoom());
			}
		}
	}

}
