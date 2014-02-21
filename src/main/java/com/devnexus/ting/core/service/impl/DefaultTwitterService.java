/*
 * Copyright 2002-2013 the original author or authors
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package com.devnexus.ting.core.service.impl;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

import com.devnexus.ting.core.model.TwitterMessage;
import com.devnexus.ting.core.service.TwitterService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Implementation of the TwitterService interface.
 *
 * @author Gunnar Hillert
 * @since 1.0
 *
 */
@Service
public class DefaultTwitterService implements TwitterService {

	/** Holds a collection of polled Twitter messages */
	private volatile LoadingCache<Long, TwitterMessage> twitterMessages;

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	/**
	 * Constructor that initializes the 'twitterMessages' Map as a simple LRU
	 * cache holding a maximum of 20 messages.
	 */
	public DefaultTwitterService() {

		this.twitterMessages = CacheBuilder.newBuilder()
			.maximumSize(40)
			.build(new CacheLoader<Long, TwitterMessage>() {
				public TwitterMessage load(Long key)  {
					return null;
				}
			});
	}

	/** {@inheritDoc} */
	@Override
	public Collection<TwitterMessage> getTwitterMessages() {
		SortedSet<TwitterMessage> twitterMessages = new TreeSet<TwitterMessage>(new Comparator<TwitterMessage>() {
			@Override
			public int compare(TwitterMessage twitterMessage1, TwitterMessage twitterMessage2) {
				return twitterMessage2.getCreatedAt().compareTo(twitterMessage1.getCreatedAt());
			}
		});

		twitterMessages.addAll(this.twitterMessages.asMap().values());
		return twitterMessages;
	}

	/**
	 * Called by Spring Integration to populate a simple LRU cache.
	 *
	 * @param tweet - The Spring Integration tweet object.
	 */
	public void addTwitterMessages(Tweet tweet) {

		final TwitterMessage twitterMessage = new TwitterMessage(tweet.getCreatedAt(),
				tweet.getText(),
				tweet.getFromUser(),
				tweet.getProfileImageUrl());
		this.twitterMessages.put(tweet.getCreatedAt().getTime(), twitterMessage);
		messagingTemplate.convertAndSend("/queue/tweets", twitterMessage);
	}

}
