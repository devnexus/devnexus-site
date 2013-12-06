/*
 * Copyright 2002-2011 the original author or authors.
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
package com.devnexus.ting.core.dao.jpa;

import com.devnexus.ting.core.dao.UserDao;
import com.devnexus.ting.core.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository("userDao")
public class UserDaoJpa extends GenericDaoJpa<User, Long>
        implements UserDao {

    /**
     * Constructor.
     */
    private UserDaoJpa() {
        super(User.class);
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            return (User) super.entityManager.createQuery("select user from User user where user.username = :username")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
