package com.devnexus.ting.web.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class DevNexusSiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		// Map default decorator. This shall be applied to all paths if no other
		// paths match.
		builder.addDecoratorPath("/*", "/decorators/default-v2.jsp")
				.addExcludedPath("/api/*")
				.addExcludedPath("/index");
	}
}