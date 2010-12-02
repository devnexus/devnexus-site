package com.devnexus.ting.web.converter;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

import com.devnexus.ting.core.model.Speaker;

public class StringToSpeaker implements Converter<String, Speaker>{

	@Override
	public Speaker convert(String source) {

		if (StringUtils.isEmpty(source) || !StringUtils.isNumeric(source)) {
			return null;
		} else {
			return new Speaker(Long.valueOf(source));
		}

	}

}