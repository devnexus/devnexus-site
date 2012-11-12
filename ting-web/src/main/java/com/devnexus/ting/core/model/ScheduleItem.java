/*
 * Copyright 2002-2011 the original author or authors.
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

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the speakers database table.
 *
 */
@Entity
@Cacheable()
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE) //, include="non-lazy"
@FilterDefs({
		@FilterDef(name = "scheduleItemFilter"),
		@FilterDef(name = "scheduleItemFilterEventId", parameters=@ParamDef( name="eventId", type="long" ) )
		})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ScheduleItem extends BaseModelObject {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1071633978769394025L;

	@Size(max=255)
	@NotEmpty
	private String type;

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

    @ManyToOne
    @NotNull
    @XmlTransient
    private Event event;

	public ScheduleItem() {
	}

	public ScheduleItem(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ScheduleItem [id=" + id + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

}
