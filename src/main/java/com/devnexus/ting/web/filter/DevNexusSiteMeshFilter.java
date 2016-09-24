/*
 * Copyright 2002-2016 the original author or authors.
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
package com.devnexus.ting.web.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.tagrules.html.Sm2TagRuleBundle;

public class DevNexusSiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		// Map default decorator. This shall be applied to all paths if no other
		// paths match.
		builder
		.setTagRuleBundles(new Sm2TagRuleBundle())
		.addDecoratorPath("/s/admin/*", "/WEB-INF/jsp/decorators/admin.jsp")
		.addDecoratorPath("/s/*", "/WEB-INF/jsp/decorators/default-v2.jsp")
		.addExcludedPath("/api/*")
		.addExcludedPath("/s/")
		.addExcludedPath("/s/index*");
	}
}