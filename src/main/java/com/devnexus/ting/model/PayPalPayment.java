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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * @author Summers Pittman
 */
@Entity
public class PayPalPayment extends BaseModelObject {

	private static final long serialVersionUID = 1L;

	private String paypalId;
	private String payerId;
	private String paymentId;
	private String registrationKey;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "payment", targetEntity = PaypalLink.class, fetch = FetchType.EAGER)
	private List<PaypalLink> links = new ArrayList<>();

	public String getPaypalId() {
		return paypalId;
	}

	public void setPaypalId(String paypalId) {
		this.paypalId = paypalId;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getRegistrationKey() {
		return registrationKey;
	}

	public void setRegistrationKey(String registrationKey) {
		this.registrationKey = registrationKey;
	}

	public List<PaypalLink> getLinks() {
		return links;
	}

	public void setLinks(List<PaypalLink> links) {
		this.links = links;
	}

	public void addLink(PaypalLink link) {
		links.add(link);
		link.setPayment(this);
	}

}
