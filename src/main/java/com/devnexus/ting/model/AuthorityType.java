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

import org.springframework.util.Assert;

/**
 *
 * @author Gunnar Hillert
 *
 */
public enum AuthorityType {

	ADMIN(100L, "ADMIN"),
	CFP_REVIEWER( 200L, "CFP_REVIEWER"),
	APP_USER(300L, "APP_USER");

	private Long id;
	private String name;

	/**
	 * Constructor.
	 *
	 */
	AuthorityType(final Long id, final String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static AuthorityType fromId(Long presentationTypeId) {

		Assert.notNull(presentationTypeId, "Parameter roleTypeId, must not be null.");

		for (AuthorityType presentationType : AuthorityType.values()) {
			if (presentationType.getId().equals(presentationTypeId)) {
				return presentationType;
			}
		}

		return null;
	}

}
