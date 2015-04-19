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

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Range;

import com.devnexus.ting.common.TingUtil;

/**
 * The persistent class for the speakers database table.
 * @author Gunnar Hillert
 *
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Evaluation extends BaseModelObject {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1071633978769394025L;

	@ManyToOne
	@NotNull
	@XmlTransient
	private Event event;

	@Size(max=10000)
	private String comment;

	@NotNull(message="Please rate the event.")
	@Range(min=1, max=10, message="Please rate the event.")
	private Integer rating;

	public Evaluation() {
	}

	public Evaluation(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Evaluation [rating=" + rating + "]";
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getCommentAsHtml() {
		return TingUtil.getMarkDownProcessor().markdownToHtml(this.comment);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
