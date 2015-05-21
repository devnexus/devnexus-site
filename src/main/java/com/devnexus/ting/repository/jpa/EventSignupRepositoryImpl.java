/*
 * Copyright 2002-2011 the original author or authors.
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

import com.devnexus.ting.repository.EventSignupRepositoryCustom;
import com.devnexus.ting.model.EventSignup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository("eventSignupDao")
public class EventSignupRepositoryImpl
        implements EventSignupRepositoryCustom {

    
	@PersistenceContext
	private EntityManager entityManager;


    @Override
    public EventSignup getByEventKey(String eventKey) {
        return entityManager.createQuery("select es from EventSignup es "
                + "    join es.event e "
                + "where e.eventKey = :eventKey", EventSignup.class)
                .setParameter("eventKey", eventKey)
                .getSingleResult();
    }

}
