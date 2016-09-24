/*
 *
 * Borrowed from https://github.com/googleplus/gplus-verifytoken-java/blob/master/src/com/google/plus/samples/verifytoken/Verify.java
 *
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.devnexus.ting.web.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.core.service.CalendarServices;
import com.devnexus.ting.core.service.UserService;
import com.devnexus.ting.core.service.exception.DuplicateUserException;
import com.devnexus.ting.model.AuthorityType;
import com.devnexus.ting.model.MobileSignIn;
import com.devnexus.ting.model.User;
import com.devnexus.ting.model.UserAuthority;
import com.devnexus.ting.model.UserScheduleItem;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * This class manages logins from the Android client using Google's services.
 */
@RestController
public class AndroidLoginController {

	private static final Gson GSON = new GsonBuilder().create();

	@Autowired
	UserService userService;

	@Autowired
	BusinessService businessService;

	@Autowired
	CalendarServices calendarServices;

	@Value("#{environment.TING_CLIENT_ID}")
	private String CLIENT_ID;

	@Value("#{environment.TING_CLIENT_SECRET}")
	private String TING_CLIENT_SECRET;

	private static final String SCOPES = "https://www.googleapis.com/auth/plus.login "
			+ "https://www.googleapis.com/auth/userinfo.email " + "https://www.googleapis.com/auth/userinfo.profile";

	private static final HttpTransport TRANSPORT = new NetHttpTransport();
	private static final JacksonFactory FACTORY = JacksonFactory.getDefaultInstance();

	/**
	 * This will verify tokens sent from an Authenticated Android device that
	 * the user is who the user says it is.
	 * <p/>
	 * Additionally it will create an account if one does not exist.
	 */
	@RequestMapping(value = "/s/loginAndroid", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {

		try {

			AndroidAuthentication auth = GSON.fromJson(request.getReader(), AndroidAuthentication.class);
			String accessToken = auth.idToken;

			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
					new JacksonFactory()).setAudience(Arrays.asList(CLIENT_ID))
							// If you retrieved the token on Android using the
							// Play Services 8.3 API or newer, set
							// the issuer to "https://accounts.google.com".
							// Otherwise, set the issuer to
							// "accounts.google.com". If you need to verify
							// tokens from multiple sources, build
							// a GoogleIdTokenVerifier for each issuer and try
							// them both.
							.setIssuer("https://accounts.google.com").build();

			GoogleIdToken idToken = verifier.verify(accessToken);
			Payload payload = idToken.getPayload();

			User user;
			try {
				user = (User) userService.loadUserByUsername("google:" + payload.getSubject());
			} catch (UsernameNotFoundException e) {
				user = new User();
				user.setEmail(payload.getEmail());
				user.setUsername("google:" + payload.getSubject());
				user.setUserAuthorities(new HashSet<UserAuthority>(1));
				user.getUserAuthorities().add(new UserAuthority(user, AuthorityType.APP_USER));
				user.setFirstName((String) payload.get("given_name"));
				user.setLastName((String) payload.get("family_name"));
				byte[] password = new byte[16];
				new SecureRandom().nextBytes(password);
				user.setPassword(Arrays.toString(password));

				try {
					userService.addUser(user);
				} catch (DuplicateUserException ex) {
					Logger.getLogger(AndroidLoginController.class.getName()).log(Level.SEVERE, null, ex);
					throw new RuntimeException(ex);
				}

				user = (User) userService.loadUserByUsername(user.getUsername());

			}

			MobileSignIn signIn = new MobileSignIn();
			signIn.setToken(new BigInteger(512, new SecureRandom()).toString(32));
			signIn.setUser(user);
			user.getMobileTokens().add(signIn);

			userService.updateUser(user);

			return "{\"token\":\"" + signIn.getToken() + "\"}";

		} catch (IOException | GeneralSecurityException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);

			throw new RuntimeException(e);
		}

	}

	@RequestMapping(path = "/s/user-schedule", method = RequestMethod.GET)
	public List<UserScheduleItem> getUserScheduleCurrentEvent(HttpServletRequest request,
			HttpServletResponse response) {

		String accessToken = request.getHeader("authToken");

		User user;
		user = (User) userService.loadUserByAndroidToken(accessToken);

		return businessService.getUserScheduleItemsForCurrentEventForUser(user);

	}

	@RequestMapping(path = "/s/user-schedule", method = { RequestMethod.POST, RequestMethod.PUT })
	public List<UserScheduleItem> updateUserScheduleCurrentEvent(HttpServletRequest request) {

		try {
			String accessToken = request.getHeader("authToken");

			User user;
			user = (User) userService.loadUserByAndroidToken(accessToken);

			JsonArray presentaitonIds = new JsonParser().parse(request.getReader()).getAsJsonArray();
			List<UserScheduleItem> scheduleItems = new ArrayList<>(presentaitonIds.size());

			for (int index = 0; index < presentaitonIds.size(); index++) {
				UserScheduleItem item = new UserScheduleItem();

				item.setUser(user);
				item.setScheduleItem(
						businessService.getPresentation(presentaitonIds.get(index).getAsLong()).getScheduleItem());
				scheduleItems.add(item);
			}

			calendarServices.replaceScheduleItemsForUser(user, scheduleItems);
			return businessService.getUserScheduleItemsForCurrentEventForUser(user);
		} catch (IOException ex) {
			Logger.getLogger(AndroidLoginController.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException(ex);
		}
	}

	/**
	 * JSON representation of a token's status.
	 */
	public static class TokenStatus {

		public boolean valid;
		public String gplus_id;
		public String message;

		public TokenStatus() {
			valid = false;
			gplus_id = "";
			message = "";
		}

		public void setValid(boolean v) {
			this.valid = v;
		}

		public void setId(String gplus_id) {
			this.gplus_id = gplus_id;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	/**
	 * JSON response to verification request.
	 * <p/>
	 * Example JSON response: { "id_token_status": { "info": "12345", "valid":
	 * True }, "access_token_status": { "Access Token not meant for this app.",
	 * "valid": False } }
	 */
	public static class VerificationResponse {

		public TokenStatus id_token_status;
		public TokenStatus access_token_status;

		private VerificationResponse(TokenStatus _id_token_status, TokenStatus _access_token_status) {
			this.id_token_status = _id_token_status;
			this.access_token_status = _access_token_status;
		}

		public static VerificationResponse newVerificationResponse(TokenStatus id_token_status,
				TokenStatus access_token_status) {
			return new VerificationResponse(id_token_status, access_token_status);
		}
	}

	public static class AndroidAuthentication {

		String idToken;

		public AndroidAuthentication() {
		}

		public String getIdToken() {
			return idToken;
		}

		public void setIdToken(String idToken) {
			this.idToken = idToken;
		}

	}
}
