package com.devnexus.ting.core.model;

import org.springframework.util.Assert;

public enum ScheduleItemType {


	REGISTRATION(100L, "Registration", "registration"),
	KEYNOTE(200L, "Keynote", "keynote"),
	BREAK( 300L, "Break", "break"),
	SESSION( 400L, "Session", "talk"),
	ADMINISTRATIVE( 500L, "Administrative", "keynote");

	private Long id;
	private String name;
	private String styleName;

	/**
	 * Constructor.
	 *
	 */
	ScheduleItemType(final Long id, final String name, String styleName) {
		this.id = id;
		this.name = name;
		this.styleName = styleName;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStyleName() {
		return styleName;
	}

	public static ScheduleItemType fromId(Long scheduleItemTypeId) {

		Assert.notNull(scheduleItemTypeId, "Parameter scheduleItemType must not be null.");

		for (ScheduleItemType scheduleItemType : ScheduleItemType.values()) {
			if (scheduleItemType.getId().equals(scheduleItemTypeId)) {
				return scheduleItemType;
			}
		}

		return null;
	}



}
