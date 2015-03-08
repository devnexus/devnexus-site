/*
 * Copyright 2013-2014 the original author or authors.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jboss.aerogear.unifiedpush.SenderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.feed.AtomFeedHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import ro.isdc.wro.extensions.processor.css.Less4jProcessor;
import ro.isdc.wro.extensions.processor.css.YUICssCompressorProcessor;
import ro.isdc.wro.extensions.processor.js.UglifyJsProcessor;
import ro.isdc.wro.http.ConfigurableWroFilter;
import ro.isdc.wro.manager.factory.ConfigurableWroManagerFactory;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.factory.SimpleProcessorsFactory;
import ro.isdc.wro.model.resource.processor.impl.css.CssImportPreProcessor;

import com.devnexus.ting.web.JaxbJacksonObjectMapper;
import com.devnexus.ting.web.converter.StringToEvent;
import com.devnexus.ting.web.converter.StringToPresentationType;
import com.devnexus.ting.web.converter.StringToRoom;
import com.devnexus.ting.web.converter.StringToSkillLevel;
import com.devnexus.ting.web.converter.StringToSpeaker;
import com.devnexus.ting.web.converter.StringToSponsorLevel;
import com.devnexus.ting.web.interceptor.GlobalDataInterceptor;

/**
 *
 * @author Gunnar Hillert
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.devnexus.ting.web")
@ImportResource("classpath:spring/spring-integration-twitter-context.xml")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment environment;

	@Autowired
	private Jaxb2Marshaller jaxbMarshaller;

	@Autowired
	@Qualifier("mvcConversionService")
	private ConfigurableConversionService conversionService;

	@Autowired
	private MessageSource messageSource;


//	@Autowired
//	private Validator validator;

	@Bean
	LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(this.messageSource);
		return localValidatorFactoryBean;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addFormatters(org.springframework.format.FormatterRegistry)
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToEvent());
		registry.addConverter(new StringToRoom());
		registry.addConverter(new StringToSkillLevel());
		registry.addConverter(new StringToPresentationType());
		registry.addConverter(new StringToSponsorLevel());
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#getValidator()
	 */
	@Override
	public Validator getValidator() {
		return validator();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globalDataInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(true)
			.useJaf(false)
			.ignoreAcceptHeader(true)
			.mediaType("html", MediaType.TEXT_HTML)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.defaultContentType(MediaType.TEXT_HTML);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public JaxbJacksonObjectMapper jaxbJacksonObjectMapper() {
		return new JaxbJacksonObjectMapper();
	}

	@Bean
	public GlobalDataInterceptor globalDataInterceptor() {
		return new GlobalDataInterceptor();
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		return new LocaleChangeInterceptor();
	}

	@Bean
	public CookieLocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}

	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();

		final ConfigurableWebBindingInitializer bindingInitializer = new ConfigurableWebBindingInitializer();

		conversionService.addConverter(new StringToEvent());
		conversionService.addConverter(new StringToRoom());
		conversionService.addConverter(new StringToSpeaker());
		conversionService.addConverter(new StringToSkillLevel());
		conversionService.addConverter(new StringToPresentationType());
		conversionService.addConverter(new StringToSponsorLevel());

		bindingInitializer.setConversionService(conversionService);
		bindingInitializer.setValidator(validator());

		requestMappingHandlerAdapter.setWebBindingInitializer(bindingInitializer);

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>(3);
		messageConverters.add(jsonConverter());
		messageConverters.add(marshallingConverter());
		messageConverters.add(atomConverter());
		requestMappingHandlerAdapter.setMessageConverters(messageConverters);

		return requestMappingHandlerAdapter;
	}

	@Bean
	public MappingJackson2HttpMessageConverter jsonConverter() {
		final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(jaxbJacksonObjectMapper());
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		return converter;
	}

	@Bean
	public MarshallingHttpMessageConverter marshallingConverter() {
		MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter(jaxbMarshaller);
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML));
		return converter;
	}

	@Bean
	public AtomFeedHttpMessageConverter atomConverter() {
		AtomFeedHttpMessageConverter converter = new AtomFeedHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_ATOM_XML));
		return converter;
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		final List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

		final BeanNameViewResolver r1 = new BeanNameViewResolver();

		final InternalResourceViewResolver r2 = new InternalResourceViewResolver();
		r2.setPrefix("/WEB-INF/jsp/");
		r2.setSuffix(".jsp");
		r2.setViewClass(JstlView.class);

		resolvers.add(r1);
		resolvers.add(r2);

		final ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);

		final List<View> defaultViews = new ArrayList<View>(2);

		final MarshallingView marshallingView = new MarshallingView(jaxbMarshaller);
		final MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
		mappingJackson2JsonView.setObjectMapper(jaxbJacksonObjectMapper());

		defaultViews.add(mappingJackson2JsonView);
		defaultViews.add(marshallingView);

		resolver.setDefaultViews(defaultViews);
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public DefaultRequestToViewNameTranslator viewNameTranslator() {
		return new DefaultRequestToViewNameTranslator();
	}

	@Bean
	public MediaType jt() {
		return new MediaType("application", "json");
	}

	@Bean
	public SenderClient javaSender() {
		return new SenderClient(environment.getRequiredProperty("TING_PUSH_URL"));
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		final CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(8194304);
		return multipartResolver;
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
		simpleMappingExceptionResolver.setDefaultErrorView("error/error");
		return simpleMappingExceptionResolver;
	}

	@Bean
	public ConfigurableWroFilter wro4jFilter() {
		final ConfigurableWroFilter wroFilter = new ConfigurableWroFilter();

		wroFilter.setDebug(false);
//		wroFilter.setDebug(true);
//		wroFilter.setCacheUpdatePeriod(1);
//		wroFilter.setModelUpdatePeriod(1);

		final ConfigurableWroManagerFactory wroManagerFactory = new ConfigurableWroManagerFactory();
		final SimpleProcessorsFactory simpleProcessorsFactory = new SimpleProcessorsFactory();

		simpleProcessorsFactory.addPreProcessor(new CssImportPreProcessor());
		simpleProcessorsFactory.addPostProcessor(new Less4jProcessor());
		simpleProcessorsFactory.addPostProcessor(new YUICssCompressorProcessor());
		simpleProcessorsFactory.addPostProcessor(new UglifyJsProcessor());

		wroManagerFactory.setProcessorsFactory(simpleProcessorsFactory);
		wroManagerFactory.setModelFactory(new DevNexusWroModelFactory());
		wroFilter.setWroManagerFactory(wroManagerFactory);
		return wroFilter;
	}

	public class DevNexusWroModelFactory implements WroModelFactory {
		public WroModel create() {
			return new WroModel().addGroup(new Group("all")
				.addResource(Resource.create("classpath:less/main.less", ResourceType.CSS))
				.addResource(Resource.create("/assets/css/vendor/animate.css", ResourceType.CSS))
				.addResource(Resource.create("/assets/js/jquery1.11.1.min.js", ResourceType.JS))
				.addResource(Resource.create("/assets/js/jquery.modernizr.js", ResourceType.JS))
				.addResource(Resource.create("/assets/js/jquery.scrollTo.js", ResourceType.JS))
				.addResource(Resource.create("/assets/js/jquery.easing.min.js", ResourceType.JS))
				.addResource(Resource.create("/assets/js/jquery-plugins/jquery.succinct.js", ResourceType.JS))
				.addResource(Resource.create("/assets/js/bootstrap.min.js", ResourceType.JS))
				.addResource(Resource.create("/assets/js/other/masonry.pkgd.js", ResourceType.JS))
				.addResource(Resource.create("/assets/js/jquery-plugins/jquery.fittext.js", ResourceType.JS))
				.addResource(Resource.create("/assets/js/other/scrollr.js", ResourceType.JS))
				.addResource(Resource.create("/assets/js/other/imagesloaded.pkgd.min.js", ResourceType.JS)));
		}

		public void destroy() {
			//do some clean-up if required.
		}
	}
}
