/*
 * Copyright 2002-2012 the original author or authors.
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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
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

	@NotNull
	private Integer capacity;

	@Size(max=10000)
	private String description;

	@NotNull
	private Integer roomOrder;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="speaker")
	@Filters({
		@Filter(name = "roomFilter", condition = "EVENT = (select e.ID from EVENTS e where e.CURRENT = 'true')"),
		@Filter(name = "roomFilterEventId", condition = "EVENT = :eventId")
	})
	@XmlTransient
	@BatchSize(size=20)
	private Set<Presentation> presentations = new HashSet<Presentation>(0);

	@ManyToOne
	//@JoinColumn(name="EVENT_ID")
	@NotNull
	@XmlTransient
	private Event event;

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

	public Set<Presentation> getPresentations() {
		return presentations;
	}

	public void setPresentations(Set<Presentation> presentations) {
		this.presentations = presentations;
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

    @Override
    public int compareTo(Room o) {
        return roomOrder.compareTo(o.roomOrder);
    }
}
