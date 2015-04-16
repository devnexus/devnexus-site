package com.devnexus.ting.model;

import org.springframework.util.Assert;

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
