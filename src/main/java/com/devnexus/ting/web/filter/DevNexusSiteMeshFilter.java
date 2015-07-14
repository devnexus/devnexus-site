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