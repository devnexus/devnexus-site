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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * A purchase group is a collection if Items of which only one may be purchased.
 *
 * @author Summers Pittman
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketGroup extends BaseModelObject {

	private static final long serialVersionUID = 1L;

	public static TicketGroup fromId(Long valueOf) {
		TicketGroup group = new TicketGroup();
		group.setId(valueOf);
		return group;
	}

	@ManyToOne
	//@JoinColumn(name="EVENT_ID")
	@NotNull
	@XmlTransient
	private Event event;

	@NotEmpty
	@Size(max = 255)
	protected String label;

	@ManyToOne
	@XmlTransient
	protected EventSignup eventSignup;

	@Size(max = 10000)
	private String description;

	@NotNull
	private BigDecimal price;

	@NotNull
	private Integer minPurchase = 1;

	@NotNull
	private Integer maxAvailableTickets = 5000;


	@Temporal(TemporalType.DATE)
	private Date openDate;

	@Temporal(TemporalType.DATE)
	private Date closeDate;

	@NotEmpty
	@Size(max = 255)
	protected String registerFormUrl;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="ticketGroup")
	protected List<CouponCode> couponCodes = new ArrayList<>();

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public EventSignup getEventSignup() {
		return eventSignup;
	}

	public void setEventSignup(EventSignup eventSignup) {
		this.eventSignup = eventSignup;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getRegisterFormUrl() {
		return registerFormUrl;
	}

	public void setRegisterFormUrl(String registerFormUrl) {
		this.registerFormUrl = registerFormUrl;
	}

	public List<CouponCode> getCouponCodes() {
		return couponCodes;
	}

	public void setCouponCode(List<CouponCode> couponCodes) {
		this.couponCodes = new ArrayList<CouponCode>(couponCodes.size());
		for (CouponCode code : couponCodes) {
			if (code.getCode() != null && !code.getCode().isEmpty()) {
				this.couponCodes.add(code);
				code.setTicketGroup(this);
			}
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMinPurchase() {
		return minPurchase;
	}

	public void setMinPurchase(Integer minPurchase) {
		this.minPurchase = minPurchase;
	}

	public Integer getMaxAvailableTickets() {
		return maxAvailableTickets;
	}

	public void setMaxAvailableTickets(Integer maxAvailableTickets) {
		this.maxAvailableTickets = maxAvailableTickets;
	}

}
