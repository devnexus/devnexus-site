/*
 * Copyright 2016 the original author or authors.
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

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.devnexus.ting.model.CfpSubmissionSpeakerConferenceDay;
import com.devnexus.ting.repository.CfpSubmissionSpeakerConferenceDayRepositoryCustom;

public class CfpSubmissionSpeakerConferenceDayRepositoryImpl implements CfpSubmissionSpeakerConferenceDayRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CfpSubmissionSpeakerConferenceDay getCfpSubmissionSpeakerConferenceDayForDayAndSpeaker(Long cfpSpeakerId,
			Long conferenceDayId) {

		CfpSubmissionSpeakerConferenceDay cfpSubmissionSpeakerConferenceDay;

		try {
			cfpSubmissionSpeakerConferenceDay = this.entityManager
			.createQuery("select sd from CfpSubmissionSpeakerConferenceDay sd where sd.conferenceDay.id = :conferenceDayId and "
					+ "sd.cfpSubmissionSpeaker.id = :cfpSpeakerId", CfpSubmissionSpeakerConferenceDay.class)
			.setParameter("cfpSpeakerId", cfpSpeakerId)
			.setParameter("conferenceDayId", conferenceDayId)
			.getSingleResult();
		}
		catch (NoResultException e) {
			cfpSubmissionSpeakerConferenceDay = null;
		}

		return cfpSubmissionSpeakerConferenceDay;
	}

}
