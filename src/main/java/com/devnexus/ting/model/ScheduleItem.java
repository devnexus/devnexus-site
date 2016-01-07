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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A ScheduleItem represents a fragment of a conference (Event) schedule.
 *
 */
@Entity
@FilterDefs({
		@FilterDef(name = "scheduleItemFilter"),
		@FilterDef(name = "scheduleItemFilterEventId", parameters=@ParamDef( name="eventId", type="long" ) ),
		@FilterDef(name = "userFilter", parameters=@ParamDef( name="userId", type="long" ) )
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ScheduleItem extends BaseModelObject {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1071633978769394025L;

	@Type(type = "com.devnexus.ting.core.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.model.ScheduleItemType"),
			@Parameter(name = "identifierMethod", value = "getId"),
			@Parameter(name = "valueOfMethod", value = "fromId") })
	private ScheduleItemType scheduleItemType;

	@Size(max=255)
	private String title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromTime;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date toTime;

	@ManyToOne
	@JoinColumn(name="ROOM_ID")
	private Room room;

	@ManyToOne
	@JoinColumn(name="PRESENTATION_ID")
	private Presentation presentation;

	@Filters({
		@Filter(name = "userFilter", condition = "USER_ID = :userId")
	})
	@OneToMany(fetch=FetchType.LAZY, mappedBy="scheduleItem")
	private Set<UserScheduleItem>userScheduleItems = new HashSet<>(0);

	@ManyToOne
	@NotNull
	@XmlTransient
        @JsonIgnore
	private Event event;

	@Transient
	private int rowspan = 1;
	public ScheduleItem() {
	}

	public ScheduleItem(Long id) {
		this.id = id;
	}

	public ScheduleItemType getScheduleItemType() {
		return scheduleItemType;
	}

	public void setScheduleItemType(ScheduleItemType scheduleItemType) {
		this.scheduleItemType = scheduleItemType;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Presentation getPresentation() {
		return presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public Set<UserScheduleItem> getUserScheduleItems() {
		return userScheduleItems;
	}

	public void setUserScheduleItems(Set<UserScheduleItem> userScheduleItems) {
		this.userScheduleItems = userScheduleItems;
	}

	public boolean isFavorite() {
		if (this.userScheduleItems.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public String toString() {
		return "ScheduleItem [scheduleItemType=" + scheduleItemType
				+ ", title=" + title + ", id=" + id + "]";
	}

}
