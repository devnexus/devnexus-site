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
package com.devnexus.ting.core.dao.jpa;

import com.devnexus.ting.core.dao.EventSignupDao;
import com.devnexus.ting.core.model.registration.EventSignup;

public class EventSignupDaoJpa extends GenericDaoJpa< EventSignup, Long>
        implements EventSignupDao {

    public EventSignupDaoJpa(Class<EventSignup> persistentClass) {
        super(EventSignup.class);
    }

    @Override
    public EventSignup getByEventKey(Long eventId) {
        return super.entityManager.createQuery("select es from EventSignup es "
                + "    join es.event e "
                + "where e.id = :eventKey", EventSignup.class)
                .setParameter("eventKey", eventId)
                .getSingleResult();
    }

}
