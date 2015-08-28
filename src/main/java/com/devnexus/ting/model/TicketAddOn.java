/*
 * Copyright 2015 the original author or authors.
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

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * This is per ticket add ons, things like Workshops etc.
 *
 * @author Summers Pittman
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketAddOn extends BaseModelObject {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Size(max = 255)
	protected String label;

	@NotNull
	private BigDecimal price;

	@NotNull
	private Integer maxAvailableTickets = 5000;

	@ManyToOne
	@XmlTransient
	@NotNull
	private Event event;

        @ManyToOne
	@XmlTransient
	@NotNull
	private EventSignup eventSignup;
        
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getMaxAvailableTickets() {
		return maxAvailableTickets;
	}

	public void setMaxAvailableTickets(Integer maxAvailableTickets) {
		this.maxAvailableTickets = maxAvailableTickets;
	}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public EventSignup getEventSignup() {
        return eventSignup;
    }

    public void setEventSignup(EventSignup eventSignup) {
        this.eventSignup = eventSignup;
    }

        

}
