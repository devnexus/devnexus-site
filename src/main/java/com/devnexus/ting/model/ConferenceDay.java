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

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;
import org.hibernate.validator.constraints.NotEmpty;

import com.devnexus.ting.common.TingUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ComparisonChain;

/**
 * The persistent class for the speakers database table.
 *
 * @author Gunnar Hillert
 *
 */
@Entity
@FilterDefs({
		@FilterDef(name = "conferenceDayFilter"),
		@FilterDef(name = "conferenceDayFilterEventId", parameters=@ParamDef( name="eventId", type="long" ) )
		})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ConferenceDay extends BaseModelObject implements Comparable<ConferenceDay> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1071633978769394025L;

	@Size(max=255)
	@NotEmpty
	private String name;

	@Size(max=10000)
	private String description;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date day;

	@ManyToOne
	@NotNull
	@JsonIgnore
	private Event event;

//	@OneToMany(mappedBy="room", targetEntity=ScheduleItem.class,
//	fetch=FetchType.LAZY, cascade = CascadeType.ALL)
//	@XmlTransient
//	@JsonIgnore
//	private Set<ScheduleItem>scheduleItems = new HashSet<>(0);

	public ConferenceDay() {
	}

	public ConferenceDay(Long id) {
		this.id = id;
	}

	public ConferenceDay(Long id, String name, Event event, String description) {
		super();
		this.id = id;
		this.name = name;
		this.event = event;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Conference Day [name=" + name + ", id=" + id + ", description="
				+ description + "]";
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public String getDescriptionAsHtml() {
		return TingUtil.getMarkDownProcessor().markdownToHtml(this.description);
	}
//
//1	@JsonIgnore
//	public Set<ScheduleItem> getScheduleItemsWithAssignedSessions() {
//		Set<ScheduleItem> scheduleItemsWithAssignedSessions = new HashSet<>(0);
//
//		for (ScheduleItem scheduleItem : this.getScheduleItems()) {
//			if (scheduleItem.getPresentation() != null) {
//				scheduleItemsWithAssignedSessions.add(scheduleItem);
//			}
//		}
//		return scheduleItemsWithAssignedSessions;
//	}

//	public void setScheduleItems(Set<ScheduleItem> scheduleItems) {
//		this.scheduleItems = scheduleItems;
//	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the day
	 */
	public Date getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(Date day) {
		this.day = day;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((day == null) ? 0 : day.hashCode());
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
		ConferenceDay other = (ConferenceDay) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		return true;
	}

	@Override
	public int compareTo(final ConferenceDay other) {
		return ComparisonChain.start().compare(day, other.day).result();
	}
}
