package com.devnexus.ting.repository;

import com.devnexus.ting.model.EventSignup;

public interface EventSignupRepositoryCustom {
    
    EventSignup getByEventKey(String eventKey);
    
    
}
