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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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

/**
 * The persistent class for the speakers database table.
 *
 * @author Gunnar Hillert
 */
@Entity
@Cacheable()
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE) //, include="non-lazy"
@FilterDefs({
		@FilterDef(name = "presentationFilter"),
		@FilterDef(name = "presentationFilterEventId", parameters=@ParamDef( name="eventId", type="long" ) )
		})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Speaker extends Person {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1071633978769394025L;

	//@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="speaker")
	@Filters({
		@Filter(name = "presentationFilter", condition = "EVENT = (select e.ID from EVENTS e where e.CURRENT = 'true')"),
		@Filter(name = "presentationFilterEventId", condition = "EVENT = :eventId")
	})
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="speakers")
	@XmlTransient
	@BatchSize(size=20)
	private List<Presentation>presentations = new ArrayList<Presentation>(0);

	@ManyToMany(fetch=FetchType.LAZY, mappedBy="speakers")
	@XmlTransient
	private Set<Event>events = new HashSet<Event>(0);

	@XmlTransient
	private Long cfpSpeakerId;

	public Speaker() {
	}

	public Speaker(Long id) {
		this.id = id;
	}

	public List<Presentation> getPresentations() {
		return presentations;
	}

	public void setPresentations(List<Presentation> presentations) {
		this.presentations = presentations;
	}

	@Override
	public String toString() {
		return "Speaker [firstName=" + firstName + ", id=" + id + ", lastName="
				+ lastName + "]";
	}

	/**
	 * @param events the events to set
	 */
	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	/**
	 * @return the events
	 */
	public Set<Event> getEvents() {
		return events;
	}

	public Long getCfpSpeakerId() {
		return cfpSpeakerId;
	}

	public void setCfpSpeakerId(Long cfpSpeakerId) {
		this.cfpSpeakerId = cfpSpeakerId;
	}

}
