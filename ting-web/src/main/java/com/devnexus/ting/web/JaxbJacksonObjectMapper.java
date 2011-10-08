package com.devnexus.ting.web;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class JaxbJacksonObjectMapper extends ObjectMapper {

	public JaxbJacksonObjectMapper() {
		final AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
		super.getDeserializationConfig().setAnnotationIntrospector(introspector);
		super.getSerializationConfig().setAnnotationIntrospector(introspector);
    }

}
