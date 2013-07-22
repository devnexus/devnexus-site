package com.devnexus.ting.core.model;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

public class SpeakerJacksonTest {

	@Test
	@Ignore
	public void testJacksonSerialization() throws JsonGenerationException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
		// make deserializer use JAXB annotations (only)
		mapper.setAnnotationIntrospector(introspector);
		// make serializer use JAXB annotations (only)
		mapper.setAnnotationIntrospector(introspector);

		Speaker speaker = new Speaker();

		speaker.setBio("bio");
		speaker.setFirstName("firstName");
		speaker.setLastName("lastName");

		mapper.writeValue(System.out, speaker);

	}


}
