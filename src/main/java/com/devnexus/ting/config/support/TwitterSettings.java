package com.devnexus.ting.config.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="devnexus.twitter")
public class TwitterSettings {

	private boolean enabled;
	private Oauth oauth;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Oauth getOauth() {
		return oauth;
	}

	public void setOauth(Oauth oauth) {
		this.oauth = oauth;
	}

	public static class Oauth {

		private String consumerKey;
		private String consumerSecret;
		private String accessToken;
		private String accessTokenSecret;
		public String getConsumerKey() {
			return consumerKey;
		}
		public void setConsumerKey(String consumerKey) {
			this.consumerKey = consumerKey;
		}
		public String getConsumerSecret() {
			return consumerSecret;
		}
		public void setConsumerSecret(String consumerSecret) {
			this.consumerSecret = consumerSecret;
		}
		public String getAccessToken() {
			return accessToken;
		}
		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}
		public String getAccessTokenSecret() {
			return accessTokenSecret;
		}
		public void setAccessTokenSecret(String accessTokenSecret) {
			this.accessTokenSecret = accessTokenSecret;
		}

	}

	public static class Smtp {

		private Integer port;
		private String host;

		public Integer getPort() {
			return port;
		}
		public void setPort(Integer port) {
			this.port = port;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}

	}
}

