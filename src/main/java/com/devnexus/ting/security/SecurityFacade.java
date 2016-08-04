package com.devnexus.ting.security;

import org.springframework.security.core.Authentication;

public interface SecurityFacade {

	Authentication getAuthentication();

	String getUsername();

}
