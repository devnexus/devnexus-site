package com.devnexus.ting.web.converter;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

import com.devnexus.ting.core.model.Event;

public class StringToEvent implements Converter<String, Event>{

	@Override
	public Event convert(String source) {

		if (StringUtils.isEmpty(source) || !StringUtils.isNumeric(source)) {
			return null;
		} else {
			return new Event(Long.valueOf(source));
		}

	}

}