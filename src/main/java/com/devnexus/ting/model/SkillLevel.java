package com.devnexus.ting.model;

import org.springframework.util.Assert;

public enum SkillLevel {

	BEGINNER(100L, "Beginner"),
	INTERMEDIATE( 200L, "Intermediate"),
	ADVANCED( 300L, "Advanced");

	private Long id;
	private String name;

	/**
	 * Constructor.
	 *
	 */
	SkillLevel(final Long id, final String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static SkillLevel fromId(Long skillLevelId) {

		Assert.notNull(skillLevelId, "Parameter skillLevelId, must not be null.");

		for (SkillLevel skillLevel : SkillLevel.values()) {
			if (skillLevel.getId().equals(skillLevelId)) {
				return skillLevel;
			}
		}

		return null;
	}
}
