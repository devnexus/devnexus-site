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
package com.devnexus.ting.web.controller.admin;

import com.devnexus.ting.core.dao.UserDao;
import com.devnexus.ting.core.model.User;
import com.devnexus.ting.web.interceptor.UserInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.userinfo.GoogleUserInfo;
import org.springframework.web.context.request.NativeWebRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Signs the user in by setting the currentUser property on the {@link SecurityContext}.
 * Remembers the sign-in after the current request completes by storing the user's id in a cookie.
 * This is cookie is read in {@link UserInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)} on subsequent requests.
 *
 * @author Keith Donald
 * @see UserInterceptor
 */
public final class SimpleSignInAdapter implements SignInAdapter {

    @Inject
    private UserDao userDao;

    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        Person person = ((Google) connection.getApi()).plusOperations().getGoogleProfile();
        GoogleUserInfo info = ((Google) connection.getApi()).userOperations().getUserInfo();
        User u = new User();
        u.setEmail(info.getEmail());
        u.setFirstName(person.getGivenName());
        u.setLastName(person.getFamilyName());
        u.setUsername(info.getId());
        u.setId((long) info.getId().hashCode());


        if (!userDao.exists(u.getId())) {
            userDao.save(u);
        }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities()));

        return null;
    }

}
