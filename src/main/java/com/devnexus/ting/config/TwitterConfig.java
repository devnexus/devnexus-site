/*
 * Copyright 2013 the original author or authors.
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
package com.devnexus.ting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import com.devnexus.ting.common.SpringProfile;
import com.devnexus.ting.config.support.TwitterSettings;
import com.devnexus.ting.core.service.impl.DefaultTwitterService;


/**
 * Spring Social Configuration.
 * @author Gunnar Hillert
 */
@Configuration
@ImportResource({
	"classpath:spring/spring-integration-twitter-context.xml"
})
public class TwitterConfig {

	@Autowired
	private TwitterSettings twitterSettings;

	@Bean
	@Profile(SpringProfile.TWITTER_ENABLED)
	public TwitterTemplate twitterTemplate() {
		return new TwitterTemplate(
				twitterSettings.getOauth().getConsumerKey(),
				twitterSettings.getOauth().getConsumerSecret(),
				twitterSettings.getOauth().getAccessToken(),
				twitterSettings.getOauth().getAccessTokenSecret()
			);
	}

	@Bean
	@Profile(SpringProfile.TWITTER_ENABLED)
	public DefaultTwitterService twitterService() {
		return new DefaultTwitterService();
	}

}
