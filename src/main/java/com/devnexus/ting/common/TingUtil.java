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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

/**
 *
 * @author Gunnar Hillert
 *
 */
public final class TingUtil {

	/** PegDownProcessor is not thread-safe but can be reused per thread */
	private static final ThreadLocal<PegDownProcessor> markdownProcessor = new ThreadLocal<PegDownProcessor>() {

		@Override
		protected PegDownProcessor initialValue() {
			return new PegDownProcessor(Extensions.ALL);
		}

	};

	public static PegDownProcessor getMarkDownProcessor() {
		return markdownProcessor.get();
	}

	private static class Token {

		public Token(String value) {
			this.value = value;
		}

		public Token(String value, String replacement) {
			this.value = value;
			this.replacement = replacement;
		}

		public String getReplacement() {
			return replacement;
		}

		public String getValue() {
			return value;
		}

		private String replacement;
		private String value;
	}

	private static final Pattern REGEX_URL = Pattern
			.compile("\\(?\\b((?:https?://|ftps?://|mailto:|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|])");

	private static final String REPLACE_URL = "<a target=\"_blank\" href=\"{1}\">{0}</a>";

	public static String convertTweetTextToHTML(String tweetText) {
		Matcher m = REGEX_URL.matcher(tweetText);
		List<Token> tokens = new ArrayList<Token>();
		int position = 0;
		String tokenContent = null;

		while (m.find()) {
			int start = m.start();
			int end = m.end();
			String replacement = m.group();

			if (replacement.startsWith("www")) {
				replacement = "http://" + replacement;
			}

			tokenContent = tweetText.substring(position, start);
			tokens.add(new Token(tokenContent));
			tokenContent = tweetText.substring(start, end);
			tokens.add(new Token(tokenContent, replacement));
			position = end;
		}

		tokenContent = tweetText.substring(position, tweetText.length());
		tokens.add(new Token(tokenContent));

		StringBuilder buffer = new StringBuilder();

		for (Token token : tokens) {
			if (token.getReplacement() != null) {
				buffer.append(MessageFormat.format(REPLACE_URL,
						token.getValue(), token.getReplacement()));
			} else {
				buffer.append(token.getValue().replace("<", "&lt;")
						.replace(">", "&gt;"));
			}
		}

		tweetText = buffer.toString();

		// Handling Hashtags

		String patternStr = "(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(tweetText);
		String result = "";

		// Search for Hashtags
		while (matcher.find()) {
			result = matcher.group();
			result = result.replace(" ", "");
			String search = result.replace("#", "");
			String searchHTML = "<a href='http://search.twitter.com/search?q="
					+ search + "'>" + result + "</a>";
			tweetText = tweetText.replace(result, searchHTML);
		}

		// Search for Users
		patternStr = "(?:\\s|\\A)[@]+([A-Za-z0-9-_]+)";
		pattern = Pattern.compile(patternStr);
		matcher = pattern.matcher(tweetText);

		while (matcher.find()) {
			result = matcher.group();
			result = result.replace(" ", "");
			String rawName = result.replace("@", "");
			String userHTML = String.format(
					"<a href='http://twitter.com/%s'>%s</a>", rawName, result);
			tweetText = tweetText.replace(result, userHTML);
		}

		return tweetText;

	}
}
