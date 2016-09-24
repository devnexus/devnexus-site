/*
 * Copyright 2016 the original author or authors.
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
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Summers Pittman
 */
@Entity
public class PaypalLink extends BaseModelObject {
	private String href;
	private String rel;
	private String httpMethod;

	@ManyToOne()
	@XmlTransient
	@JsonIgnore
	@JoinColumn(name = "payment")
	private PayPalPayment payment;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String method) {
		this.httpMethod = method;
	}

	public PayPalPayment getPayment() {
		return payment;
	}

	public void setPayment(PayPalPayment payment) {
		this.payment = payment;
	}

}
