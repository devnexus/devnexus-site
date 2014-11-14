package com.devnexus.ting.core.dao;

import com.devnexus.ting.core.model.registration.EventSignup;
import org.springframework.stereotype.Repository;

public interface EventSignupDao extends GenericDao<EventSignup, Long>{
    
    EventSignup getByEventKey(String eventKey);
    
    
}
