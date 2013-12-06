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
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class manages logins from the Android client using Google's services.
 */
public class AndroidLoginController {

    private static final String CLIENT_ID;
    private static final String CLIENT_SECRET;
    private static final Gson GSON = new GsonBuilder().create();
    
    static {
        Apphome appHome = SystemInformationUtils.retrieveBasicSystemInformation();
        Properties props = SystemInformationUtils.getConfigProperties(appHome.getAppHomePath());
        
        
        CLIENT_ID = props.getProperty("TING_CLIENT_ID");
        CLIENT_SECRET = props.getProperty("TING_CLIENT_SECRET");
    }

    private static final String SCOPES = "https://www.googleapis.com/auth/plus.login "
            + "https://www.googleapis.com/auth/userinfo.email "
            + "https://www.googleapis.com/auth/userinfo.profile";

    private static final ApacheHttpTransport TRANSPORT = new ApacheHttpTransport();
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
            
            AndroidAuthentication auth = json.parseAndClose(request.getReader(), AndroidAuthentication.class);
            String gPlusId = auth.gPlusId;
            String accessToken = auth.accessToken;
            
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    TRANSPORT,
                    FACTORY,
                    CLIENT_ID,
                    CLIENT_SECRET,
                    accessToken,
                    (String) null).execute();

            // Create a credential representation of the token data.
            GoogleCredential credential = new GoogleCredential.Builder()
                    .setJsonFactory(FACTORY)
                    .setTransport(TRANSPORT)
                    .setClientSecrets(CLIENT_ID, CLIENT_SECRET).build()
                    .setFromTokenResponse(tokenResponse);

            // Check that the token is valid.
            Oauth2 oauth2 = new Oauth2.Builder(
                    TRANSPORT, FACTORY, credential).build();
            Tokeninfo tokenInfo = oauth2.tokeninfo()
                    .setAccessToken(credential.getAccessToken()).execute();
            // If there was an error in the token info, abort.
            if (tokenInfo.containsKey("error")) {
                
                return GSON.toJson(tokenInfo.get("error").toString());
            }
            // Make sure the token we got is for the intended user.
            if (!tokenInfo.getUserId().equals(gPlusId)) {
                response.setStatus(401);
                return GSON.toJson("Token's user ID doesn't match given user ID.");
            }
            // Make sure the token we got is for our app.
            if (!tokenInfo.getIssuedTo().equals(CLIENT_ID)) {
                response.setStatus(401);
                return GSON.toJson("Token's client ID does not match app's.");
            }
            // Store the token in the session for later use.
            request.getSession().setAttribute("token", tokenResponse.toString());
            return GSON.toJson(tokenResponse.toString());

        } catch (IOException e) {
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
