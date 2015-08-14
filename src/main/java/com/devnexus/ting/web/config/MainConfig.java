/*
 * Copyright 2002-2014 the original author or authors.
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
package com.devnexus.ting.web.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.devnexus.ting.core.applicationlistener.ContextRefreshedEventListener;
import com.google.common.cache.CacheBuilder;


/**
 * @author Gunnar Hillert
 *
 */
@Configuration
@Import({
	CloudConfig.class,
	ServicesConfig.class,
	//WebSocketConfig.class,
	WebSecurityConfig.class,
	WebConfig.class
})
@EnableCaching
public class MainConfig {

    
	@Bean
	public GuavaCacheManager cacheManager() {
		final CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
		.recordStats();
		final GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(cacheBuilder);
		return cacheManager;
	}

	@Bean
	ContextRefreshedEventListener seedDataEventListener() {
		return new ContextRefreshedEventListener();
	}
}
