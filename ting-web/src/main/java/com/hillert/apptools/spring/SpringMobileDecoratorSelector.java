/*
 * Copyright 2002-2011 the original author or authors.
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
package com.hillert.apptools.spring;

import java.io.IOException;

import org.sitemesh.DecoratorSelector;
import org.sitemesh.content.Content;
import org.sitemesh.webapp.WebAppContext;
import org.springframework.mobile.device.site.SitePreference;

public class SpringMobileDecoratorSelector implements
		DecoratorSelector<WebAppContext> {
	private final DecoratorSelector<WebAppContext> fallbackSelector;

	public SpringMobileDecoratorSelector(
			DecoratorSelector<WebAppContext> fallbackSelector) {
		this.fallbackSelector = fallbackSelector;
	}

	@Override
	public String[] selectDecoratorPaths(Content content, WebAppContext context) {

		final SitePreference sitePreference = (SitePreference) context.getRequest().getAttribute("currentSitePreference");

		final String decorator;

		if (sitePreference.isMobile()) {
			decorator = "/WEB-INF/jsp/decorators/mobile.jsp";
		} else {
			decorator = null;
		}

		if (decorator != null) {
			return decorator.split(",");
		} else {
			// Otherwise, fallback to the standard configuration.
			try {
				return fallbackSelector.selectDecoratorPaths(content, context);
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
