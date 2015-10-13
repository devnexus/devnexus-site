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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.devnexus.ting.web.converter.StringToEvent;
import com.devnexus.ting.web.converter.StringToPresentationType;
import com.devnexus.ting.web.converter.StringToPurchaseGroup;
import com.devnexus.ting.web.converter.StringToRoom;
import com.devnexus.ting.web.converter.StringToSkillLevel;
import com.devnexus.ting.web.converter.StringToSponsorLevel;
import com.devnexus.ting.web.interceptor.GlobalDataInterceptor;

/**
 *
 * @author Gunnar Hillert
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("utf-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		final LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource());
		return localValidatorFactoryBean;
	}

	@Autowired
	private Environment environment;

	@Bean
	public GlobalDataInterceptor globalDataInterceptor() {
		return new GlobalDataInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globalDataInterceptor()).addPathPatterns("/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*");
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToEvent());
		registry.addConverter(new StringToRoom());
		registry.addConverter(new StringToSkillLevel());
		registry.addConverter(new StringToPresentationType());
		registry.addConverter(new StringToSponsorLevel());
		registry.addConverter(new StringToPurchaseGroup());
	}

	@Override
	public Validator getValidator() {
		return validator();
	}

}
