package com.devnexus.ting.web.filter;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.filter.ShallowEtagHeaderFilter;

public class WeakShallowEtagHeaderFilter extends ShallowEtagHeaderFilter {

	@Override
	protected String generateETagHeaderValue(InputStream inputStream) throws IOException {
		return "W/" + super.generateETagHeaderValue(inputStream);
	}

}
