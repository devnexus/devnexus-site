/*
 * Copyright 2014-2015 the original author or authors.
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

/**
 * Keeps track of all Spring application context profiles being used.
 *
 * @author Gunnar Hillert
 *
 */
public final class SpringProfile {

	public static final String MAIL_ENABLED       = "mail-enabled";
	public static final String MAIL_DISABLED      = "!mail-enabled";
	public static final String TWITTER_ENABLED    = "twitter-enabled";
	public static final String PAYPAL_ENABLED     = "paypal-enabled";
	public static final String PAYPAL_LIVE        = "paypal-live";
	public static final String PAYPAL_SANDBOX     = "paypal-sandbox";
	public static final String WEBSOCKET_ENABLED  = "websocket-enabled";
	public static final String WEBSOCKET_DISABLED = "!websocket-enabled";

	public static final String DEMO = "demo";
	public static final String STANDALONE = "standalone";
	public static final String DEFAULT = "default";
}
