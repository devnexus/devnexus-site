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
package com.devnexus.ting;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import javax.servlet.descriptor.TaglibDescriptor;

import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.valves.RemoteIpValve;
import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.jboss.aerogear.unifiedpush.SenderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.devnexus.ting.core.applicationlistener.ContextRefreshedEventListener;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Primary;

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

	@Autowired
	MessageSource messageSource;

	public static void main(String[] args) throws Exception {
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
		if(container instanceof TomcatEmbeddedServletContainerFactory) {
			final TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = (TomcatEmbeddedServletContainerFactory) container;
			tomcatEmbeddedServletContainerFactory.addContextCustomizers(
					new TomcatContextCustomizer() {
						@Override
						public void customize(Context context) {
							context.addWelcomeFile("index.jsp");

							final ErrorPage throwableErrorPage = new ErrorPage();
							throwableErrorPage.setExceptionType("java.lang.Throwable");
							throwableErrorPage.setLocation("/WEB-INF/jsp/error/error.jsp");

							final ErrorPage errorPage403 = new ErrorPage();
							errorPage403.setErrorCode(403);
							errorPage403.setLocation("/WEB-INF/jsp/error/403.jsp");

							final ErrorPage errorPage404 = new ErrorPage();
							errorPage404.setErrorCode(404);
							errorPage404.setLocation("/WEB-INF/jsp/error/404.jsp");

							final ErrorPage errorPage500 = new ErrorPage();
							errorPage500.setErrorCode(500);
							errorPage500.setLocation("/WEB-INF/jsp/error/500.jsp");

							context.addErrorPage(errorPage500);
							context.addErrorPage(errorPage404);
							context.addErrorPage(errorPage403);
							context.addErrorPage(throwableErrorPage);

							final Collection<JspPropertyGroupDescriptor> jspPropertyGroups = new ArrayList<>();
							final Collection<TaglibDescriptor> taglibs = new ArrayList<>();

							final JspPropertyGroup group = new JspPropertyGroup();
							group.addUrlPattern("*.jsp");
							group.setPageEncoding("UTF-8");

							final JspPropertyGroupDescriptor descriptor = new JspPropertyGroupDescriptorImpl(group);

							jspPropertyGroups.add(descriptor);

							final JspConfigDescriptor jspConfigDescriptor = new JspConfigDescriptorImpl(jspPropertyGroups, taglibs);
							context.setJspConfigDescriptor(jspConfigDescriptor);

							Container jsp = context.findChild("jsp");
							if (jsp instanceof Wrapper) {
								((Wrapper)jsp).addInitParameter("development", "false");
							}
						}
			});
			tomcatEmbeddedServletContainerFactory.addAdditionalTomcatConnectors(createConnector());
			tomcatEmbeddedServletContainerFactory.addContextValves(createRemoteIpValves());
		}
	}

	private static RemoteIpValve createRemoteIpValves() {
		RemoteIpValve remoteIpValve = new RemoteIpValve();
		remoteIpValve.setRemoteIpHeader("x-forwarded-for");
		remoteIpValve.setProtocolHeader("x-forwarded-proto");
		return remoteIpValve;
	}

	private static Connector createConnector() {
		Connector connector = new Connector("AJP/1.3");
		connector.setPort(8099);
		return connector;
	}

}
