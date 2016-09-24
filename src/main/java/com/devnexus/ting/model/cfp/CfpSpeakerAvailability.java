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
package com.devnexus.ting.model.cfp;

import org.springframework.util.Assert;

/**
 *
 * @author Gunnar Hillert
 *
 */
public enum CfpSpeakerAvailability {

	ANY_TIME("ANY_TIME", "Any Time"),
	NO_AVAILABILITY("NO_AVAILABILITY", "Not Available"),
	PARTIAL_AVAILABILITY("PARTIAL_AVAILABILITY", "Partially Available");

	private String key;
	private String name;

	/**
	 * Constructor.
	 *
	 */
	CfpSpeakerAvailability(final String key, final String name) {
		this.key = key;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public static CfpSpeakerAvailability fromKey(String cfpSpeakerAvailabilityId) {

		Assert.notNull(cfpSpeakerAvailabilityId, "Parameter cfpSpeakerAvailabilityId must not be null.");

		for (CfpSpeakerAvailability cfpSpeakerAvailability : CfpSpeakerAvailability.values()) {
			if (cfpSpeakerAvailability.getKey().equals(cfpSpeakerAvailabilityId)) {
				return cfpSpeakerAvailability;
			}
		}

		return null;
	}

}
