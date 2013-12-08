package com.devnexus.ting.core.model;

import org.springframework.util.Assert;

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
