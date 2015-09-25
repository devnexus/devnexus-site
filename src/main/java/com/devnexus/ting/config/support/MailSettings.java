package com.devnexus.ting.config.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="devnexus.mail")
public class MailSettings {

	private boolean enabled;
	private boolean sendgridEnabled;

	private String  sendgridApiKey;
	private boolean authenticationEnabled;
	private boolean debugEnabled;
	private User user;
	private Smtp smtp;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isSendgridEnabled() {
		return sendgridEnabled;
	}

	public String getSendgridApiKey() {
		return sendgridApiKey;
	}

	public void setSendgridApiKey(String sendgridApiKey) {
		this.sendgridApiKey = sendgridApiKey;
	}

	public void setSendgridEnabled(boolean sendgridEnabled) {
		this.sendgridEnabled = sendgridEnabled;
	}

	public boolean isAuthenticationEnabled() {
		return authenticationEnabled;
	}

	public void setAuthenticationEnabled(boolean authenticationEnabled) {
		this.authenticationEnabled = authenticationEnabled;
	}

	public boolean isDebugEnabled() {
		return debugEnabled;
	}

	public void setDebugEnabled(boolean debugEnabled) {
		this.debugEnabled = debugEnabled;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Smtp getSmtp() {
		return smtp;
	}

	public void setSmtp(Smtp smtp) {
		this.smtp = smtp;
	}

	public static class User {

		private String id;
		private String password;
		private String from;
		private String cc;

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getCc() {
			return cc;
		}
		public void setCc(String cc) {
			this.cc = cc;
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

