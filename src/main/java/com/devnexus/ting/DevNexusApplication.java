/*
 * Copyright 2016-2017 the original author or authors.
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
package com.devnexus.ting;

import java.util.TimeZone;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.jboss.aerogear.unifiedpush.SenderClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartException;

import com.devnexus.ting.core.applicationlistener.ContextRefreshedEventListener;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;

/**
 * Main entry point for the DevNexus application.
 *
 * @author Gunnar Hillert
 *
 */
@EnableCaching
@SpringBootApplication
public class DevNexusApplication implements EmbeddedServletContainerCustomizer {

	@Autowired
	private Environment environment;

	private static final Logger LOGGER = LoggerFactory.getLogger(DevNexusApplication.class);

	@Autowired
	MessageSource messageSource;

	public static void main(String[] args) throws Exception {
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final SpringApplication application = new SpringApplication(DevNexusApplication.class);
		application.addInitializers(new DefaultApplicationContextInitializer());
		application.run(args);
	}

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

	@Bean
	@Primary
	ObjectMapper jacksonObjectMapper() {
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(om.getSerializationConfig().getDefaultVisibilityChecker()
			.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
			.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
			.withSetterVisibility(JsonAutoDetect.Visibility.NONE)
			.withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
		return om;
	}

	@Bean
	public SenderClient javaSender() {
		return new SenderClient(environment.getRequiredProperty("TING_PUSH_URL"));
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		if (container instanceof JettyEmbeddedServletContainerFactory) {
			final JettyEmbeddedServletContainerFactory jetty = (JettyEmbeddedServletContainerFactory) container;
			final JettyServerCustomizer customizer = new JettyServerCustomizer() {
				@Override
				public void customize(Server server) {
					final Handler handler = server.getHandler();
					final WebAppContext webAppContext;
					if (handler instanceof GzipHandler) {
						webAppContext = (WebAppContext)((GzipHandler) handler).getHandler();
					}
					else if (handler instanceof WebAppContext) {
						webAppContext = (WebAppContext) handler;
					}
					else {
						throw new java.lang.IllegalStateException(handler.toString());
					}
					webAppContext.setBaseResource(Resource.newClassPathResource("webroot"));
				}
			};
			jetty.addServerCustomizers(customizer);
			jetty.addErrorPages(
				new ErrorPage(HttpStatus.NOT_FOUND, "/WEB-INF/jsp/error/404.jsp"),
				new ErrorPage(HttpStatus.FORBIDDEN, "/WEB-INF/jsp/error/403.jsp"),
				new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/s/handleGlobaleErrors"),
				new ErrorPage(MultipartException.class, "/s/handleGlobaleErrors"),
				new ErrorPage("/s/handleGlobaleErrors"));
		}
	}
}
