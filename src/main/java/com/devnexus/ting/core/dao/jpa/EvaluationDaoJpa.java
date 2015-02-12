/*
 * Copyright 2002-2013 the original author or authors.
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

import com.devnexus.ting.core.dao.EvaluationDao;
import com.devnexus.ting.core.model.Evaluation;

@Repository("evaluationDao")
public class EvaluationDaoJpa extends GenericDaoJpa<Evaluation, Long>
						implements EvaluationDao {

	/** Constructor. */
	private EvaluationDaoJpa() {
		super(Evaluation.class);
	}

	@Override
	public List<Evaluation> getEvaluationsForCurrentEvent() {
		return super.entityManager
				.createQuery("select eval from Evaluation eval "
						   + "left join eval.event e "
						   + "where e.current = :iscurrent "
						   + "order by eval.createdDate DESC", Evaluation.class)
				.setParameter("iscurrent", true)
				.getResultList();
	}

	@Override
	public List<Evaluation> getEvaluationsForEvent(Long eventId) {
		return super.entityManager
				.createQuery("select eval from Evaluation eval "
						   + "left join eval.event e "
						   + "where e.id = :eventId "
						   + "order by eval.createdDate DESC", Evaluation.class)
				.setParameter("eventId", eventId)
				.getResultList();
	}

}
