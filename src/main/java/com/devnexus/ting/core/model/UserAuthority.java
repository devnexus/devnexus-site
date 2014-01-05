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
package com.devnexus.ting.core.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;


/**
 * The persistent class for the presentations database table.
 */
@Entity
@org.hibernate.annotations.Table(appliesTo = "USER_AUTHORITIES", indexes = {@Index(name = "USER_AUTHORITIES_IDX", columnNames = {"AUTHORITY"})})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserAuthority extends BaseModelObject implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Type(type = "com.devnexus.ting.core.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "com.devnexus.ting.core.model.AuthorityType"),
			@Parameter(name = "identifierMethod", value = "getId"),
			@Parameter(name = "valueOfMethod", value = "fromId")})
	private AuthorityType authority;

	//~~~~Constructors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public UserAuthority() {
	}

	public UserAuthority(User user, AuthorityType authority) {
		super();
		this.user = user;
		this.authority = authority;
	}

	//~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public AuthorityType getAuthority() {
//		return authority;
//	}

	public void setAuthorityType(AuthorityType roleType) {
		this.authority = roleType;
	}

	@Override
	public String getAuthority() {
		return this.authority.getName();
	}

	@Override
	public String toString() {
		return "UserAuthority [authority=" + authority + "]";
	}

}
