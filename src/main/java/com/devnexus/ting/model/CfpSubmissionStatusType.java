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
public enum CfpSubmissionStatusType {

	REJECTED("REJECTED", "Rejected", "cfp-rejected"),
	ACCEPTED("ACCEPTED", "Accepted", "cfp-accepted"),
	PENDING("PENDING", "Pending", "cfp-pending");

	private String key;
	private String name;
	private String styleName;

	/**
	 * Constructor.
	 *
	 */
	CfpSubmissionStatusType(final String key, final String name, String styleName) {
		this.key = key;
		this.name = name;
		this.styleName = styleName;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public String getStyleName() {
		return styleName;
	}

	public static CfpSubmissionStatusType fromKey(String cfpSubmissionStatusTypeId) {

		Assert.notNull(cfpSubmissionStatusTypeId, "Parameter scheduleItemType must not be null.");

		for (CfpSubmissionStatusType cfpSubmissionStatusType : CfpSubmissionStatusType.values()) {
			if (cfpSubmissionStatusType.getKey().equals(cfpSubmissionStatusTypeId)) {
				return cfpSubmissionStatusType;
			}
		}

		return null;
	}



}
