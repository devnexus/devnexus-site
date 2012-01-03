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

import java.util.List;

import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.PresentationDao;
import com.devnexus.ting.core.model.Presentation;

@Repository("presentationDao")
public class PresentationDaoJpa extends GenericDaoJpa< Presentation, Long>
                           implements PresentationDao {

    /** Constructor. */
    public PresentationDaoJpa() {
        super(Presentation.class);
    }

    @Override
    public List<Presentation> getPresentationsForCurrentEvent() {
        return super.entityManager
            .createQuery("select p from Presentation p "
                       + "left join p.event e "
                       + "where e.current = :iscurrent "
                       + "order by p.title ASC", Presentation.class)
            .setParameter("iscurrent", true)
            .getResultList();
    }

    @Override
    public List<Presentation> getPresentationsForEvent(Long eventId) {
        return super.entityManager
        .createQuery("select p from Presentation p "
                   + "    join p.event e "
                   + "where e.id = :eventId "
                   + "order by p.title ASC", Presentation.class)
        .setParameter("eventId", eventId)
        .getResultList();
    }

}
