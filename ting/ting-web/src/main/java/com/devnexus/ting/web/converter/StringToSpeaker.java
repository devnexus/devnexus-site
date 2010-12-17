package com.devnexus.ting.web.converter;

import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;

import com.devnexus.ting.core.model.Speaker;

public class StringToSpeaker implements Converter<String, Speaker>, Formatter<Speaker>{

	@Override
	public Speaker convert(String source) {

		if (StringUtils.isEmpty(source) || !StringUtils.isNumeric(source)) {
			return null;
		} else {
			return new Speaker(Long.valueOf(source));
		}

	}

	@Override
	public String print(Speaker object, Locale locale) {
		// TODO Auto-generated method stub
		return String.valueOf(object.getId());
	}

	@Override
	public Speaker parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

}