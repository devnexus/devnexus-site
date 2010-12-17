package com.devnexus.ting.web.converter;

import org.springframework.core.convert.converter.Converter;

import com.devnexus.ting.core.model.Speaker;

public class SpeakerToString implements Converter<Speaker, String> {

	@Override
	public String convert(Speaker source) {

		if (source == null) {
			return null;
		} else {
			return String.valueOf(source.getId());
		}

	}

}