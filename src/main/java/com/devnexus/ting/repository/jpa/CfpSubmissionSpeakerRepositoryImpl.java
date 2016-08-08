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

import com.devnexus.ting.model.CfpSubmissionSpeaker;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.User;
import com.devnexus.ting.repository.CfpSubmissionSpeakerRepositoryCustom;

@Repository("cfpSubmissionSpeakerDao")
public class CfpSubmissionSpeakerRepositoryImpl implements CfpSubmissionSpeakerRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CfpSubmissionSpeaker getCfpSubmissionSpeakerWithPicture(Long speakerId) {
		return this.entityManager
		.createQuery("select o from CfpSubmissionSpeaker o left outer join fetch o.cfpSpeakerImage where o.id = :id", CfpSubmissionSpeaker.class)
		.setParameter("id", speakerId)
		.getSingleResult();
	}

	@Override
	public List<CfpSubmissionSpeaker> getCfpSubmissionSpeakersForUserAndEvent(User user, Event event) {
		return this.entityManager
		.createQuery("select o from CfpSubmissionSpeaker o "
				+ "where o.createdByUser.id = :userId and o.event.id = :eventId", CfpSubmissionSpeaker.class)
		.setParameter("userId", user.getId())
		.setParameter("eventId", event.getId())
		.getResultList();
	}

	@Override
	public CfpSubmissionSpeaker getSingleCfpSubmissionSpeakerForUserAndEvent(Long cfpSubmissionSpeakerId, Long userId, Long eventId) {
		return this.entityManager
		.createQuery("select o from CfpSubmissionSpeaker o "
				+ "where o.createdByUser.id = :userId "
				+ "and o.event.id = :eventId "
				+ "and o.id = :cfpSubmissionSpeakerId",
				CfpSubmissionSpeaker.class)
		.setParameter("userId", userId)
		.setParameter("eventId", eventId)
		.setParameter("cfpSubmissionSpeakerId", cfpSubmissionSpeakerId)
		.getSingleResult();
	}
}
