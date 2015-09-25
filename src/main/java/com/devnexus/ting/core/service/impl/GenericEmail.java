/*
 * Copyright 2015 the original author or authors.
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
package com.devnexus.ting.core.service.impl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gunnar Hillert
 *
 */
public class GenericEmail {

	private String html;
	private String text;
	private String from;
	private String subject;
	private List<String> to = new ArrayList<>();
	private List<String> cc = new ArrayList<>();

	public String getHtml() {
		return html;
	}

	public GenericEmail setHtml(String html) {
		this.html = html;
		return this;
	}
	public String getText() {
		return text;
	}
	public GenericEmail setText(String text) {
		this.text = text;
		return this;
	}
	public String getFrom() {
		return from;
	}
	public GenericEmail setFrom(String from) {
		this.from = from;
		return this;
	}
	public String getSubject() {
		return subject;
	}
	public GenericEmail setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	public List<String> getTo() {
		return to;
	}
	public GenericEmail addTo(String to) {
		this.to.add(to);
		return this;
	}
	public List<String> getCc() {
		return cc;
	}
	public GenericEmail setCc(String cc) {
		this.cc.add(cc);
		return this;
	}

}
