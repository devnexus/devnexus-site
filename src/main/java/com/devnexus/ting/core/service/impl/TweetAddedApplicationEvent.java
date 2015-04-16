package com.devnexus.ting.core.service.impl;

import org.springframework.context.ApplicationEvent;

import com.devnexus.ting.model.TwitterMessage;

public class TweetAddedApplicationEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public TweetAddedApplicationEvent(TwitterMessage tweet) {
		super(tweet);
	}

	public TwitterMessage getTwitterMessage() {
		return (TwitterMessage) super.getSource();
	}

}
