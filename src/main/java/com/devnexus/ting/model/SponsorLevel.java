/*
 * Copyright 2014-2016 the original author or authors.
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
public enum SponsorLevel {

	PLATINUM(100L, "Platinum Sponsor", "sponsorsPlatium"),
	GOLD( 200L, "Gold Sponsor", "sponsorsGold"),
	SILVER( 300L, "Silver Sponsor", "sponsorsSilver"),
	MEDIA_PARTNER(350L, "Media Partner", "sponsorsMediaPartner"),
	COCKTAIL_HOUR( 400L, "Cocktail Hour Sponsor", "sponsorsCocktail"),
	BADGE( 370L, "Badge Sponsor", "sponsorsBadge"),
	LANYARD( 375L, "Lanyard Sponsor", "sponsorsLanyard"),
	DEV_LOUNGE( 380L, "Dev Lounge Sponsor", "sponsorsDevLounge");

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
