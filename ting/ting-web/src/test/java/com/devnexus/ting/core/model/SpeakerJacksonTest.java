package com.devnexus.ting.core.model;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.junit.Test;

public class SpeakerJacksonTest {

	@Test
	public void testJacksonSerialization() throws JsonGenerationException, JsonMappingException, IOException {

	    ObjectMapper mapper = new ObjectMapper();
	    AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
	    // make deserializer use JAXB annotations (only)
	    mapper.getDeserializationConfig().setAnnotationIntrospector(introspector);
	    // make serializer use JAXB annotations (only)
	    mapper.getSerializationConfig().setAnnotationIntrospector(introspector);

	    Speaker speaker = new Speaker();

	    speaker.setBio("bio");
	    speaker.setFirstName("firstName");
	    speaker.setLastName("lastName");

	    mapper.writeValue(System.out, speaker);

	}


}
