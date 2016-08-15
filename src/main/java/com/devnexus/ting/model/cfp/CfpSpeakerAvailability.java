package com.devnexus.ting.model.cfp;

import org.springframework.util.Assert;

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
