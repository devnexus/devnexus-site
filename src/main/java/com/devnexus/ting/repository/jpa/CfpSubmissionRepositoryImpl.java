/*
 * Copyright 2002-2014 the original author or authors.
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

import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.repository.CfpSubmissionRepositoryCustom;

/**
 *
 * @author Gunnar Hillert
 *
 */
public class CfpSubmissionRepositoryImpl implements CfpSubmissionRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	public CfpSubmissionRepositoryImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CfpSubmission> getCfpSubmissions(Long eventId) {

		final List<CfpSubmission> cfpSubmissions = this.entityManager
				.createQuery("select distinct cfp from CfpSubmission cfp "
						+ "    join cfp.event e "
						+ "    join fetch cfp.speakers s "
						+ "where e.id = :eventId "
						+ "order by s.lastName ASC", CfpSubmission.class)
			 .setParameter("eventId", eventId)
			 .getResultList();

		return cfpSubmissions;
	}

}
