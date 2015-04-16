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
package com.devnexus.ting.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.devnexus.ting.model.Sponsor;
import com.devnexus.ting.repository.SponsorRepositoryCustom;

/**
 *
 * @author Gunnar Hillert
 *
 */
@Repository("sponsorDao")
public class SponsorRepositoryImpl implements SponsorRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Sponsor> getSponsorsForEvent(Long eventId) {

		final List<Sponsor> sponsors = this.entityManager
				.createQuery("select s from Sponsor s "
						+ "where s.event.id = :eventId "
						+ "order by s.sponsorLevel ASC, s.sortOrder ASC, s.name ASC", Sponsor.class)
			.setParameter("eventId", eventId)
			.getResultList();

		return sponsors;
	}

	@Override
	public Sponsor getSponsorWithPicture(Long sponsorId) {
		return this.entityManager
		.createQuery("select s from Sponsor s left outer join fetch s.logo where s.id = :id", Sponsor.class)
		.setParameter("id", sponsorId)
		.getSingleResult();
	}
}
