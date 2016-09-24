/*
 * Copyright 2016 the original author or authors.
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

import com.devnexus.ting.common.SpringProfile;
import javax.inject.Inject;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import ro.isdc.wro.http.ConfigurableWroFilter;

/**
 *
 * @author Summers Pittman
 */
@Configuration
@Profile({ SpringProfile.DEVELOPMENT_ENABLED })
@ImportResource("classpath:spring/spring-development-context.xml")
public class DevelopmentConfig {

	@Inject
	private ConfigurableWroFilter filter;

	@Bean
	@Profile({ SpringProfile.DEVELOPMENT_ENABLED })
	public FilterRegistrationBean configureWroFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns("/wro/*");
		registrationBean.setOrder(Integer.MAX_VALUE);
		return registrationBean;
	}

}
