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
package com.devnexus.ting.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

import com.devnexus.ting.common.TingUtil;

/**
 * The persistent class for the speakers database table.
 *
 */
@Entity
@Cacheable()
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE) //, include="non-lazy"
@FilterDefs({
		@FilterDef(name = "roomFilter"),
		@FilterDef(name = "roomFilterEventId", parameters=@ParamDef( name="eventId", type="long" ) )
		})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Room extends BaseModelObject implements Comparable<Room> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1071633978769394025L;

	@Size(max=255)
	@NotEmpty
	private String name;

	@Size(max=255)
	private String track;

	@Size(max=255)
	private String cssStyleName;

	@Size(max=255)
	private String color;

	@NotNull
	private Integer capacity;

	@Size(max=10000)
	private String description;

	@NotNull
	private Integer roomOrder;

	@ManyToOne
	//@JoinColumn(name="EVENT_ID")
	@NotNull
	@XmlTransient
	private Event event;

	@OneToMany(mappedBy="room", targetEntity=ScheduleItem.class,
	fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@XmlTransient
	private Set<ScheduleItem>scheduleItems = new HashSet<>(0);

	public Room() {
	}

	public Room(Long id) {
		this.id = id;
	}

	public Room(Long id, String name, Integer capacity, Integer order, Event event) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.roomOrder = order;
		this.event = event;
	}

	@Override
	public String toString() {
		return "Room [name=" + name + ", id=" + id + ", capacity="
				+ capacity + "]";
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

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getRoomOrder() {
		return roomOrder;
	}

	public void setRoomOrder(Integer order) {
		this.roomOrder = order;
	}

	public String getCssStyleName() {
		return cssStyleName;
	}

	public void setCssStyleName(String cssStyleName) {
		this.cssStyleName = cssStyleName;
	}

	public String getDescription() {
		return this.description;
	}

	public String getDescriptionAsHtml() {
		return TingUtil.getMarkDownProcessor().markdownToHtml(this.description);
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public Set<ScheduleItem> getScheduleItems() {
		return scheduleItems;
	}

	public Set<ScheduleItem> getScheduleItemsWithAssignedSessions() {
		Set<ScheduleItem> scheduleItemsWithAssignedSessions = new HashSet<>(0);

		for (ScheduleItem scheduleItem : this.getScheduleItems()) {
			if (scheduleItem.getPresentation() != null) {
				scheduleItemsWithAssignedSessions.add(scheduleItem);
			}
		}
		return scheduleItemsWithAssignedSessions;
	}

	public void setScheduleItems(Set<ScheduleItem> scheduleItems) {
		this.scheduleItems = scheduleItems;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public int compareTo(Room o) {
		return roomOrder.compareTo(o.roomOrder);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Room room = (Room) o;

		if (capacity != null ? !capacity.equals(room.capacity) : room.capacity != null) return false;
		if (cssStyleName != null ? !cssStyleName.equals(room.cssStyleName) : room.cssStyleName != null) return false;
		if (description != null ? !description.equals(room.description) : room.description != null) return false;
		if (event != null ? !event.equals(room.event) : room.event != null) return false;
		if (name != null ? !name.equals(room.name) : room.name != null) return false;
		if (roomOrder != null ? !roomOrder.equals(room.roomOrder) : room.roomOrder != null) return false;
		if (track != null ? !track.equals(room.track) : room.track != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (track != null ? track.hashCode() : 0);
		result = 31 * result + (cssStyleName != null ? cssStyleName.hashCode() : 0);
		result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (roomOrder != null ? roomOrder.hashCode() : 0);
		result = 31 * result + (event != null ? event.hashCode() : 0);
		return result;
	}
}
