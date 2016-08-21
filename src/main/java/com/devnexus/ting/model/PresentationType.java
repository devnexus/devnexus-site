package com.devnexus.ting.model;

import org.springframework.util.Assert;

public enum PresentationType {

	BREAKOUT(100L, "Breakout", "75min"),
	KEYNOTE( 200L, "Keynote", "60min"),
	WORKSHOP( 300L, "Workshop", "Full Day, 8h");

	private Long id;
	private String name;
	private String description;

	/**
	 * Constructor.
	 *
	 */
	PresentationType(final Long id, final String name, final String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getNameWithDescription() {
		return name + " (" + description + ")";
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
