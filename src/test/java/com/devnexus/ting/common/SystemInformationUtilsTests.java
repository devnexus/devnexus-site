/*
 * Copyright 2002-2013 the original author or authors.
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
package com.devnexus.ting.common;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.pegdown.PegDownProcessor;

/**
 * @author Gunnar Hillert
 *
 *
 */
public class SystemInformationUtilsTests {

	@Test
	public void testGetHtmlCfpEmailTemplate() {

		String template = SystemInformationUtils.getCfpHtmlEmailTemplate();
		assertNotNull(template);

		PegDownProcessor markdownProcessor = TingUtil.getMarkDownProcessor();
		assertNotNull(markdownProcessor);

		String resultingHtml = markdownProcessor.markdownToHtml(template);

		assertNotNull(resultingHtml);

	}

	@Test
	public void testGetTextCfpEmailTemplate() {

		String template = SystemInformationUtils.getCfpTextEmailTemplate();
		assertNotNull(template);

		PegDownProcessor markdownProcessor = TingUtil.getMarkDownProcessor();
		assertNotNull(markdownProcessor);

		String resultingHtml = markdownProcessor.markdownToHtml(template);

		assertNotNull(resultingHtml);

	}

}
