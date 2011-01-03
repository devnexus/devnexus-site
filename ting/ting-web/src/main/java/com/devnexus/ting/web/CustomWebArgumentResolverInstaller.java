package com.devnexus.ting.web;

import org.springframework.mobile.device.mvc.DeviceWebArgumentResolver;
import org.springframework.mobile.device.site.SitePreferenceWebArgumentResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import javax.inject.Inject;

@Component
public class CustomWebArgumentResolverInstaller {

	@Inject
	public CustomWebArgumentResolverInstaller(
			AnnotationMethodHandlerAdapter controllerInvoker) {
		WebArgumentResolver[] resolvers = new WebArgumentResolver[2];
		resolvers[0] = new DeviceWebArgumentResolver();

		resolvers[1] = new SitePreferenceWebArgumentResolver();

		controllerInvoker.setCustomArgumentResolvers(resolvers);

	}

}
