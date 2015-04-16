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
package com.devnexus.ting.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.devnexus.ting.model.Evaluation;
import com.devnexus.ting.repository.EvaluationRepositoryCustom;

@Repository("evaluationDao")
public class EvaluationRepositoryImpl implements EvaluationRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Evaluation> getEvaluationsForCurrentEvent() {
		return this.entityManager
				.createQuery("select eval from Evaluation eval "
						   + "left join eval.event e "
						   + "where e.current = :iscurrent "
						   + "order by eval.createdDate DESC", Evaluation.class)
				.setParameter("iscurrent", true)
				.getResultList();
	}

	@Override
	public List<Evaluation> getEvaluationsForEvent(Long eventId) {
		return this.entityManager
				.createQuery("select eval from Evaluation eval "
						   + "left join eval.event e "
						   + "where e.id = :eventId "
						   + "order by eval.createdDate DESC", Evaluation.class)
				.setParameter("eventId", eventId)
				.getResultList();
	}

}
