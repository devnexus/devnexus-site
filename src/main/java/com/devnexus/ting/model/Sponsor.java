/*
 * Copyright 2014 the original author or authors.
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
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gunnar Hillert
 *
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sponsor extends BaseModelObject {

	/** serialVersionUID. */
	private static final long serialVersionUID = -6456631481903155317L;

	@ManyToOne
	@NotNull
	@XmlTransient
        @JsonIgnore
	private Event event;

	@NotEmpty
	@Size(max=255)
	protected String name;

	@Type(type = "com.devnexus.ting.core.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.model.SponsorLevel"),
			@Parameter(name = "identifierMethod", value = "getId"),
			@Parameter(name = "valueOfMethod", value = "fromId")})
	private SponsorLevel sponsorLevel;

	private Integer sortOrder;

	@NotEmpty
	@Size(max=255)
	protected String link;

	@ManyToOne(fetch = FetchType.LAZY)
	@XmlTransient
        @JsonIgnore
	@Cascade(CascadeType.ALL)
	protected FileData logo;

	public Sponsor() {
		super();
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public FileData getLogo() {
		return logo;
	}

	public void setLogo(FileData logo) {
		this.logo = logo;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public SponsorLevel getSponsorLevel() {
		return sponsorLevel;
	}

	public void setSponsorLevel(SponsorLevel sponsorLevel) {
		this.sponsorLevel = sponsorLevel;
	}

	@Override
	public String toString() {
		return "Sponsor [name=" + name + ", sponsorLevel=" + sponsorLevel
				+ ", sortOrder=" + sortOrder + "]";
	}

}
