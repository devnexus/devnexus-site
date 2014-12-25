package com.devnexus.ting.core.model;

import org.springframework.util.Assert;

public enum SponsorLevel {

	PLATINUM(100L, "Platinum Sponsor", "sponsorsPlatium"),
	GOLD( 200L, "Gold Sponsor", "sponsorsGold"),
        MEDIA_PARTNER(250L, "Media Partner", "sponsorsCocktail"),
	SILVER( 300L, "Silver Sponsor", "sponsorsSilver"),
	COCKTAIL_HOUR( 400L, "Cocktail Hour Sponsor", "sponsorsCocktail");

	private Long id;
	private String name;
	private String cssStyleClass;

	/**
	 * Constructor.
	 *
	 */
	SponsorLevel(final Long id, final String name, final String cssStyleClass) {
		this.id = id;
		this.name = name;
		this.cssStyleClass = cssStyleClass;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCssStyleClass() {
		return cssStyleClass;
	}

	public static SponsorLevel fromId(Long sponsorLevelId) {

		Assert.notNull(sponsorLevelId, "Parameter sponsorLevelId, must not be null.");

		for (SponsorLevel sponsorLevel : SponsorLevel.values()) {
			if (sponsorLevel.getId().equals(sponsorLevelId)) {
				return sponsorLevel;
			}
		}

		return null;
	}

}
