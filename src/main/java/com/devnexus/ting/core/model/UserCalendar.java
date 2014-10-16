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

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Summers Pittman
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserCalendar extends BaseModelObject {

	private static final long serialVersionUID = 1L;

	private String username;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromTime;

	@ManyToOne
	@JoinColumn(name = "schedule_item_id")
	private ScheduleItem item;

	private boolean fixed = false;
	private boolean template = false;

	private String eventKey;

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public ScheduleItem getItem() {
		return item;
	}

	public void setItem(ScheduleItem item) {
		this.item = item;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public boolean isTemplate() {
		return template;
	}

	public void setTemplate(boolean template) {
		this.template = template;
	}

	/**
	 * Copies a template fields for use by a new user
	 * @return
	 */
	public UserCalendar copy() {

		assert template;

		UserCalendar copy = new UserCalendar();
		copy.setFixed(fixed);
		copy.setFromTime(fromTime);
		copy.setItem(item);
		copy.setEventKey(eventKey);
		return copy;

	}

}
