/*
 * Copyright 2015 the original author or authors.
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
package com.devnexus.ting.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import com.devnexus.ting.web.filter.DevNexusSiteMeshFilter;
import com.devnexus.ting.web.filter.ResponseAddHttpHeadersFilter;

/**
 * @author Gunnar Hillert
 *
 */
@Configuration
public class WebFilterConfig {

	@Bean
	public FilterRegistrationBean urlRewriteFilterRegistrationBean () {

		final UrlRewriteFilter urlRewriteFilter = new UrlRewriteFilter();
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		registrationBean.setFilter(urlRewriteFilter);
		registrationBean.addUrlPatterns("/s/*", "/proxy/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean encodingFilterRegistrationBean () {

		final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		registrationBean.setFilter(encodingFilter);
		registrationBean.addUrlPatterns("/s/*");
		registrationBean.setOrder(2);
		return registrationBean;
	}

	// jsonPRequestFilter
	//FilterRegistration.Dynamic jsonPRequestFilterRegistration = servletContext.addFilter("JSONPRequestFilter", JSONPRequestFilter.class);
	//jsonPRequestFilterRegistration.addMappingForUrlPatterns(null, true, "*.json");

	@Bean
	public FilterRegistrationBean lazyLoadingFilterRegistrationBean () {

		final OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		registrationBean.setFilter(openEntityManagerInViewFilter);
		registrationBean.addUrlPatterns("/s/*");
		registrationBean.setOrder(3);
		return registrationBean;
	}

//	// springSecurityFilterChain
//	FilterRegistration.Dynamic springSecurityFilterChainRegistration = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
//	springSecurityFilterChainRegistration.addMappingForUrlPatterns(null, true, "/*");
//
//	// loggingFilter4Logback
//	FilterRegistration.Dynamic loggingFilter4LogbackRegistration = servletContext.addFilter("loggingFilter4Logback", LoggingFilter4Logback.class);
//	loggingFilter4LogbackRegistration.addMappingForUrlPatterns(null, true, "/s/*", "/api/*");
//
//	// etagFilter
//	FilterRegistration.Dynamic etagFilterRegistration = servletContext.addFilter("etagFilter", ShallowEtagHeaderFilter.class);
//	etagFilterRegistration.addMappingForUrlPatterns(null, true, "/s/*");

	@Bean
	public FilterRegistrationBean devNexusSiteMeshFilterRegistrationBean () {

		final DevNexusSiteMeshFilter configurableSiteMeshFilter = new DevNexusSiteMeshFilter();
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		registrationBean.setFilter(configurableSiteMeshFilter);
		registrationBean.addUrlPatterns("/s/*", "/index.jsp");
		registrationBean.setOrder(4);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean responseAddHttpHeadersFilterRegistrationBean() {

		final ResponseAddHttpHeadersFilter responseAddHttpHeadersFilter = new ResponseAddHttpHeadersFilter();
		responseAddHttpHeadersFilter.setSecondsToCache(2592000);
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		registrationBean.setFilter(responseAddHttpHeadersFilter);
		registrationBean.setMatchAfter(true);
		registrationBean.addUrlPatterns("*.css", "*.gif", "*.ico", "*.jpg", "*.svg", "*.png", "*.js");
		registrationBean.setOrder(5);
		return registrationBean;
	}

}
