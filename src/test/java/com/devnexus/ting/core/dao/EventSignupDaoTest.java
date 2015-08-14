/*
 * Copyright 2014 summers.
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
package com.devnexus.ting.core.dao;

import com.devnexus.ting.repository.EventSignupRepository;
import com.devnexus.ting.model.EventSignup;
import com.devnexus.ting.model.TicketGroup;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.repository.EventRepository;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

    

public class EventSignupDaoTest extends BaseDaoIntegrationTest {
    
    @Autowired EventSignupRepository signupDao;
    @Autowired EventRepository eventDao;
    
    @Test
    public void save() {
        
        Event event = new Event(42l, "key", "title", true);
        event = eventDao.save(event);
        
        EventSignup es = new EventSignup();
        
        es.setEvent(event);
        
        TicketGroup group1 = new TicketGroup();
        TicketGroup group2 = new TicketGroup();
        
        group1.setEvent(event);
        group2.setEvent(event);
        group1.setEventSignup(es);
        group2.setEventSignup(es);
        
        
        group1.setLabel("Test Label 1");
        group2.setLabel("Test Label 2");
        
        
        es.getGroups().add(group1);
        es.getGroups().add(group2);
        
        es = signupDao.save(es);
        Assert.assertNotNull(es.getId());
        es = signupDao.getByEventKey("key");
        Assert.assertNotNull(es);
    }
    
    
}
