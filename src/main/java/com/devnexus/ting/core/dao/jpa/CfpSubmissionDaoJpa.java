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
package com.devnexus.ting.core.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.CfpSubmissionDao;
import com.devnexus.ting.core.model.CfpSubmission;

/**
 * 
 * @author Gunnar Hillert
 *
 */
@Repository("cfpSubmissionDao")
public class CfpSubmissionDaoJpa extends GenericDaoJpa< CfpSubmission, Long>
						   implements CfpSubmissionDao {

	/** Constructor. */
	private CfpSubmissionDaoJpa() {
		super(CfpSubmission.class);
	}

	@Override
	public List<CfpSubmission> getCfpSubmissions(Long eventId) {

		final List<CfpSubmission> cfpSubmissions = super.entityManager
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
