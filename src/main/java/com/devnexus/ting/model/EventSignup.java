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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.SortNatural;

/**
 * The event sign up is all tickets available for a single Event.
 *
 * @author Summers Pittman
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventSignup extends BaseModelObject {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "eventSignup", targetEntity = TicketGroup.class, fetch = FetchType.EAGER)
	@SortNatural
	private SortedSet<TicketGroup> groups = new TreeSet<>();

	@ManyToOne
	@JoinColumn(name = "EVENT_ID")
	@NotNull
	@XmlTransient
	@JsonIgnore
	private Event event;

	public Set<TicketGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<TicketGroup> groups) {
		this.groups = new TreeSet<>(groups);
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Optional<TicketGroup> getGroup(TicketGroup purchaseGroup) {
		for (TicketGroup group : groups) {
			if (group.getId().equals(purchaseGroup.getId())) {
				return Optional.of(group);
			}
		}
		return Optional.empty();
	}

}
