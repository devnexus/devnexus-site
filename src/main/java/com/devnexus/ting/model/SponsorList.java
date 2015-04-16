/*
 * Copyright 2014 the original author or authors.
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gunnar Hillert
 *
 */
@XmlRootElement(name="sponsors")
@XmlAccessorType(XmlAccessType.FIELD)
public class SponsorList extends BaseModelObject {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@XmlElement(name="sponsor")
	private List<Sponsor> sponsors = new ArrayList<>();

	Map<SponsorLevel, Integer> sponsorLevelCount = new HashMap<>();

	final Map<Long, String> logos = new HashMap<>();

	public SponsorList() {
		for (SponsorLevel level : SponsorLevel.values()) {
			sponsorLevelCount.put(level, Integer.valueOf(0));
		}
	}

	public List<Sponsor> getSponsors() {
		return sponsors;
	}

	public void setSponsors(List<Sponsor> sponsors) {
		this.sponsors = sponsors;
	}

	public Map<SponsorLevel, Integer> getSponsorLevelCount() {
		return sponsorLevelCount;
	}

	public void setSponsorLevelCount(Map<SponsorLevel, Integer> sponsorLevelCount) {
		this.sponsorLevelCount = sponsorLevelCount;
	}

	public Map<Long, String> getLogos() {
		return logos;
	}

	public void addSponsor(Sponsor sponsor, String imageData) {
		sponsors.add(sponsor);
		logos.put(sponsor.getId(), imageData);
		this.sponsorLevelCount.put(sponsor.getSponsorLevel(), Integer.valueOf(sponsorLevelCount.get(sponsor.getSponsorLevel()).intValue() + 1));
	}
}
