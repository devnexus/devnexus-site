package com.devnexus.ting.core.model;

import org.springframework.util.Assert;

public enum PresentationType {

	BREAKOUT(100L, "Breakout"),
	KEYNOTE( 200L, "Keynote"),
	WORKSHOP( 300L, "Workshop");

	private Long id;
	private String name;

	/**
	 * Constructor.
	 *
	 */
	PresentationType(final Long id, final String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static PresentationType fromId(Long presentationTypeId) {

		Assert.notNull(presentationTypeId, "Parameter presentationTypeId, must not be null.");

		for (PresentationType presentationType : PresentationType.values()) {
			if (presentationType.getId().equals(presentationTypeId)) {
				return presentationType;
			}
		}

		return null;
	}

}
