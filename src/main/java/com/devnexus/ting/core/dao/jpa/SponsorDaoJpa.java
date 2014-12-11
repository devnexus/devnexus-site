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
package com.devnexus.ting.core.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.SponsorDao;
import com.devnexus.ting.core.model.Organizer;
import com.devnexus.ting.core.model.Sponsor;

/**
 *
 * @author Gunnar Hillert
 *
 */
@Repository("sponsorDao")
public class SponsorDaoJpa extends GenericDaoJpa< Sponsor, Long>
						implements SponsorDao {

	/** Constructor. */
	private SponsorDaoJpa() {
		super(Sponsor.class);
	}

	@Override
	public List<Sponsor> getSponsorsForEvent(Long eventId) {

		final List<Sponsor> sponsors = super.entityManager
				.createQuery("select s from Sponsor s "
						+ "where s.event.id = :eventId "
						+ "order by s.sponsorLevel ASC, s.sortOrder ASC, s.name ASC", Sponsor.class)
			.setParameter("eventId", eventId)
			.getResultList();

		return sponsors;
	}

	@Override
	public Sponsor getSponsorWithPicture(Long sponsorId) {
		return super.entityManager
		.createQuery("select s from Sponsor s left outer join fetch s.logo where s.id = :id", Sponsor.class)
		.setParameter("id", sponsorId)
		.getSingleResult();
	}
}
