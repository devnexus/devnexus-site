package com.devnexus.ting.core.dao;

import com.devnexus.ting.core.model.registration.EventSignup;

public interface EventSignupDao {
    
    EventSignup getByEventKey(Long eventId);
    
    
}
