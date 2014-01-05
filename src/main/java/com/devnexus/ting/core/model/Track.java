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
import org.hibernate.annotations.Filters;
import org.hibernate.validator.constraints.NotEmpty;

import com.devnexus.ting.common.TingUtil;

/**
 * The persistent class for the tracks database table.
 *
 * @author Gunnar Hillert
 *
 */
@Entity
@Cacheable()
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE) //, include="non-lazy"
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Track extends BaseModelObject implements Comparable<Track> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1071633978769394025L;

	@Size(max=255)
	@NotEmpty
	private String name;

	@Size(max=255)
	private String cssStyleName;

	@Size(max=10000)
	private String description;

	@NotNull
	private Integer trackOrder;

	@ManyToOne
	@NotNull
	@XmlTransient
	private Event event;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="track")
	@Filters({
		@Filter(name = "presentationFilter", condition = "EVENT = (select e.ID from EVENTS e where e.CURRENT = 'true')"),
		@Filter(name = "presentationFilterEventId", condition = "EVENT = :eventId")
	})
	@XmlTransient
	@BatchSize(size=20)
	private Set<Presentation> presentations = new HashSet<Presentation>(0);

	public Track() {
	}

	public Track(Long id) {
		this.id = id;
	}

	public Track(Long id, String name, Integer trackOrder, Event event) {
		super();
		this.id = id;
		this.name = name;
		this.event = event;
		this.trackOrder = trackOrder;
	}

	@Override
	public String toString() {
		return "Track [name=" + name + ", event=" + event + "]";
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

	public Integer getTrackOrder() {
		return trackOrder;
	}

	public void setTrackOrder(Integer trackOrder) {
		this.trackOrder = trackOrder;
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

	public Set<Presentation> getPresentations() {
		return presentations;
	}

	public void setPresentations(Set<Presentation> presentations) {
		this.presentations = presentations;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Track o) {
		return trackOrder.compareTo(o.trackOrder);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((cssStyleName == null) ? 0 : cssStyleName.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((trackOrder == null) ? 0 : trackOrder.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Track other = (Track) obj;
		if (cssStyleName == null) {
			if (other.cssStyleName != null)
				return false;
		} else if (!cssStyleName.equals(other.cssStyleName))
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (trackOrder == null) {
			if (other.trackOrder != null)
				return false;
		} else if (!trackOrder.equals(other.trackOrder))
			return false;
		return true;
	}

}
