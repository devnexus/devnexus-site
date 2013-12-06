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

import com.devnexus.ting.core.model.CfpSubmission;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * This class manages logins from the Android client using Google's services.
 * 
 */

@Controller
public class AndroidLoginController {
    
    
    /**
     * This will verify tokens sent from an Authenticated Android device that 
     * the user is who the user says it is.
     * 
     * Additionally it will create an account if one does not exist.
     * 
     */
    @RequestMapping(value="/loginAndroid", method=RequestMethod.POST)
    public String login(String idToken, String accessToken) {

        TokenStatus idStatus = new TokenStatus();
        if (idToken != null) {
          // Check that the ID Token is valid.

          Checker checker = new Checker(new String[]{CLIENT_ID}, CLIENT_ID);
          GoogleIdToken.Payload jwt = checker.check(idToken);

          if (jwt == null) {
            // This is not a valid token.
            idStatus.setValid(false);
            idStatus.setId("");
            idStatus.setMessage("Invalid ID Token.");
          } else {
            idStatus.setValid(true);
            String gplusId = (String)jwt.get("sub");
            idStatus.setId(gplusId);
            idStatus.setMessage("ID Token is valid.");
          }
        } else {
          idStatus.setMessage("ID Token not provided");
        }

        TokenStatus accessStatus = new TokenStatus();
        if (accessToken != null) {
          // Check that the Access Token is valid.
          try {
            GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
            Oauth2 oauth2 = new Oauth2.Builder(
                TRANSPORT, JSON_FACTORY, credential).build();
            Tokeninfo tokenInfo = oauth2.tokeninfo()
                .setAccessToken(accessToken).execute();
            if (tokenInfo.containsKey("error")) {
              // This is not a valid token.
              accessStatus.setValid(false);
              accessStatus.setId("");
              accessStatus.setMessage("Invalid Access Token.");
            } else if (!tokenInfo.getIssuedTo().equals(CLIENT_ID)) {
              // This is not meant for this app. It is VERY important to check
              // the client ID in order to prevent man-in-the-middle attacks.
              accessStatus.setValid(false);
              accessStatus.setId("");
              accessStatus.setMessage("Access Token not meant for this app.");
            } else {
              accessStatus.setValid(true);
              accessStatus.setId(tokenInfo.getUserId());
              accessStatus.setMessage("Access Token is valid.");
            }
          } catch (IOException e) {
            accessStatus.setValid(false);
            accessStatus.setId("");
            accessStatus.setMessage("Invalid Access Token.");
          }
        } else {
          accessStatus.setMessage("Access Token not provided");
        }

        VerificationResponse tokenStatus =
            new VerificationResponse(idStatus, accessStatus);
        return GSON.toJson(tokenStatus);

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
*
* Example JSON response:
* {
* "id_token_status": {
* "info": "12345",
* "valid": True
* },
* "access_token_status": {
* "Access Token not meant for this app.",
* "valid": False
* }
* }
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
}
    
}
