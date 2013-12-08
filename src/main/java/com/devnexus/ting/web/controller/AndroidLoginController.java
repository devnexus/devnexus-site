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

import com.devnexus.ting.common.Apphome;
import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.model.AuthorityType;
import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.model.UserAuthority;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.core.service.UserService;
import com.devnexus.ting.core.service.exception.DuplicateUserException;
import com.devnexus.ting.web.controller.googleauth.Checker;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * This class manages logins from the Android client using Google's services.
 */
@Controller
public class AndroidLoginController {

    private static final String CLIENT_ID;
    private static final String CLIENT_SECRET;
    private static final Gson GSON = new GsonBuilder().create();

    @Autowired
    UserService userService;
    
    @Autowired
    BusinessService businessService;
    
    static {
        Apphome appHome = SystemInformationUtils.retrieveBasicSystemInformation();
        Properties props = SystemInformationUtils.getConfigProperties(appHome.getAppHomePath());

        CLIENT_ID = props.getProperty("TING_CLIENT_ID");
        CLIENT_SECRET = props.getProperty("TING_CLIENT_SECRET");
    }

    private static final String SCOPES = "https://www.googleapis.com/auth/plus.login "
            + "https://www.googleapis.com/auth/userinfo.email "
            + "https://www.googleapis.com/auth/userinfo.profile";

    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private static final JacksonFactory FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * This will verify tokens sent from an Authenticated Android device that
     * the user is who the user says it is.
     * <p/>
     * Additionally it will create an account if one does not exist.
     */
    @RequestMapping(value = "/loginAndroid", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {

        JsonObjectParser json = FACTORY.createJsonObjectParser();

        try {

            AndroidAuthentication auth = GSON.fromJson(request.getReader(), AndroidAuthentication.class);
            String accessToken = auth.accessToken;

            GoogleIdToken.Payload payload = new Checker(new String[]{CLIENT_ID}, CLIENT_ID).check(accessToken);
            
            User user;
            try {
                user = (User) userService.loadUserByUsername(payload.getSubject());
            } catch (UsernameNotFoundException e) {
                user = new User();
                user.setEmail(payload.getEmail());
                user.setUsername(payload.getSubject());
                user.setUserAuthorities(new HashSet<UserAuthority>(1));
                user.getUserAuthorities().add(new UserAuthority(user, AuthorityType.APP_USER));
                user.setFirstName("");
                user.setLastName("");
                byte[] password = new byte[16];
                new SecureRandom().nextBytes(password);
                user.setPassword(Arrays.toString(password));
        
                try {
                    userService.addUser(user);
                    userService.initializeUserforEvent(user, businessService.getCurrentEvent().getEventKey());
                } catch (DuplicateUserException ex) {
                    Logger.getLogger(AndroidLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                }
                
                user = (User) userService.loadUserByUsername(user.getUsername());
                
            }
            
           SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
            
            return "{}";

        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
            
            throw new RuntimeException(e);
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
            return new VerificationResponse(id_token_status,
                    access_token_status);
        }
    }

    public static class AndroidAuthentication {

        String gPlusId, accessToken;

        public AndroidAuthentication() {
        }

        public String getgPlusId() {
            return gPlusId;
        }

        public void setgPlusId(String gPlusId) {
            this.gPlusId = gPlusId;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

    }
}
