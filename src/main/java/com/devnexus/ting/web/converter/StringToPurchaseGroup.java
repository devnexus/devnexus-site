/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.devnexus.ting.web.converter;

import com.devnexus.ting.model.TicketGroup;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;


/**
 *
 * @author Gunnar Hillert
 * @author Summers Pittman
 *
 */
public class StringToPurchaseGroup implements Converter<String, TicketGroup>{

	@Override
	public TicketGroup convert(String source) {
		if (StringUtils.isEmpty(source) || !StringUtils.isNumeric(source)) {
			return null;
		} else {
			return TicketGroup.fromId(Long.valueOf(source));
		}
	}
}
