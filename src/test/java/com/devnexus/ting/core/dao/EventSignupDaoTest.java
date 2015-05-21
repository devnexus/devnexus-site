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
import com.devnexus.ting.model.PurchaseGroup;
import com.devnexus.ting.model.PurchaseItem;
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
        
        PurchaseGroup group1 = new PurchaseGroup();
        PurchaseGroup group2 = new PurchaseGroup();
        
        group1.setEvent(event);
        group2.setEvent(event);
        group1.setEventSignup(es);
        group2.setEventSignup(es);
        
        
        group1.setLabel("Test Label 1");
        group2.setLabel("Test Label 2");
        
        PurchaseItem item1 = new PurchaseItem();
        PurchaseItem item2 = new PurchaseItem();
        PurchaseItem item3 = new PurchaseItem();
        PurchaseItem item4 = new PurchaseItem();
        
        item1.setOpenDate(new Date());
        item2.setOpenDate(new Date());
        item3.setOpenDate(new Date());
        item4.setOpenDate(new Date());
        
        
        item1.setCloseDate(DateUtils.addDays(new Date(), 5));
        item2.setCloseDate(DateUtils.addDays(new Date(), 5));
        item3.setCloseDate(DateUtils.addDays(new Date(), 5));
        item4.setCloseDate(DateUtils.addDays(new Date(), 5));
        
        item1.setLabel("Test Item Label 1");
        item2.setLabel("Test Item Label 2");
        item3.setLabel("Test Item Label 3");
        item4.setLabel("Test Item Label 4");
        
        
        item1.setPrice(new BigDecimal("3.50"));
        item2.setPrice(new BigDecimal("4.20"));
        item3.setPrice(new BigDecimal("1.80"));
        item4.setPrice(new BigDecimal("86753.09"));
        
        item1.setEvent(event);
        item1.setPurchaseGroup(group1);
        item2.setEvent(event);
        item2.setPurchaseGroup(group1);
        group1.getItems().add(item1);
        group1.getItems().add(item2);
        
        item3.setEvent(event);
        item3.setPurchaseGroup(group2);
        item4.setEvent(event);
        item4.setPurchaseGroup(group2);
        group2.getItems().add(item3);
        group2.getItems().add(item4);
        
        
        es.getGroups().add(group1);
        es.getGroups().add(group2);
        
        es = signupDao.save(es);
        Assert.assertNotNull(es.getId());
        es = signupDao.getByEventKey("key");
        Assert.assertNotNull(es);
    }
    
    
}
