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
package com.devnexus.ting.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.core.dao.UserDao;
import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.service.UserService;

/**
 * Provides user specific services.
 *
 * @author Gunnar Hillert
 *
 * @since 1.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    /**
     *   Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

//    //private @Autowired NotificationService notificationService;
//
//    //private @Autowired ServerSettings serverSettings;
//
//    private @Resource(name="server.messageSource") MessageSource messageSource;
//
//    /**
//     * User Dao.
//     */
      private @Autowired UserDao userDao;
//
//    private @Autowired StringDigester stringDigester;
//
//    //~~~~~Business Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//    /** {@inheritDoc} */
//    public User addUser(User user) throws DuplicateUserException {
////        final User savedUser = this.addUser(user, Boolean.FALSE);
////        return savedUser;
//        return null;
//    }
//
//    /** {@inheritDoc} */
//    public User addUser(User user, Boolean verificationRequired) throws DuplicateUserException{
//
//        if (user == null) {
//            throw new IllegalArgumentException("User must not be null.");
//        }
//        if (verificationRequired == null) {
//            throw new IllegalArgumentException("verificationRequired must not be null.");
//        }
//
////        final Date registerDate = new Date();
////        user.setRegistrationDate(registerDate);
////        user.setUpdateDate(registerDate);
////        user.setEnabled(Boolean.FALSE);
////        user.setVerificationKey(this.generateUuid());
////
////        if (user.getUsername() == null) {
////            user.setUsername(user.getEmail());
////        }
////
////        final User duplicateUser = userDao.getUser(user.getUsername());
////
////        if (duplicateUser!= null) {
////            throw new DuplicateUserException("User " + duplicateUser.getUsername()
////                                           + "(Id="  + duplicateUser.getId()
////                                           + ") already exists!");
////        }
////
////        Role role = roleDao.getRole(Constants.Roles.MANAGER.name());
////
////        if (role == null) {
////            throw new IllegalStateException("Role was not found but is required.");
////        }
////
////        final Set<UserToRole> userToRoles = user.getUserToRoles();
////
////        UserToRole utr = new UserToRole();
////        utr.setRole(role);
////        utr.setUser(user);
////
////        userToRoles.add(utr);
////
////        final User savedUser = this.saveUser(user);
////
////        if (verificationRequired) {
////            final Map<String, Object> context = CollectionUtils.getHashMap();
////            context.put("user", savedUser);
////            context.put("registrationCode", savedUser.getVerificationKey());
////            context.put("accountValidationUrl", serverSettings.getServerAddress() + ServerActions.ACCOUNT_VERIFICATION.getPath());
////            notificationService.sendEmail(savedUser.getEmail(), messageSource.getMessage("class.UserServiceImpl.addUser.account.validation.subject", null, LocaleContextHolder.getLocale()), context, "account-validation");
////        }
//       // return savedUser;
//        return null;
//
//    }
//
//    /** {@inheritDoc} */
//    public User saveUser(User user) {
//       // return userDao.save(user);
//       return null;
//    }
//
//    /** {@inheritDoc} */
//    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
//    public User getUser(String username) {
//        return userDao.getUser(username);
//    }
//
//    /** {@inheritDoc} */
//    public void updateUser(User user) {
//        Date updateDate = new Date();
//        user.setUpdateDate(updateDate);
//        userDao.save(user);
//    }
//
//    /** {@inheritDoc} */
//    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
//    public List<User> getAllUsers() {
//
//        return userDao.getAllUsers();
//    }
//
//    /** {@inheritDoc} */
//    public void deleteUser(User user){
//        userDao.remove(user.getId());
//    }
//
//    /** {@inheritDoc} */
//    public void resetPassword(final User user) {
//
//        final String password = PasswordGenerator.generatePassword();
//
//        user.setPassword(this.stringDigester.digest(password));
//
//        this.updateUser(user);
//
//        final Map<String, Object> context = CollectionUtils.getHashMap();
//        context.put("user", user);
//        context.put("password", password);
//
//        this.notificationService.sendEmail(user.getEmail(),
//                                           messageSource.getMessage("class.UserServiceImpl.resetPassword.email.subject",
//                                           null,
//                                           LocaleContextHolder.getLocale()),
//                                           context,
//                                           "get-password");
//
//        LOGGER.info("resetPassword - Email sent to: " + user.getEmail() + "; id: " + user.getId());
//    }

    /** {@inheritDoc} */
    @Override
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException, DataAccessException {

        final User user = userDao.getUserByUsername(userName.trim());

        if (user==null){
            LOGGER.warn("loadUserByUsername() - No user with id " + userName + " found.");
            throw new UsernameNotFoundException("loadUserByUsername() - No user with id " + userName + " found.");
        }

        LOGGER.info("User {} ({}) loaded.", new Object[] { user.getUsername(), user.getEmail()});

        return user;
    }

//    /** {@inheritDoc} */
//    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
//    public User getUser(Long userId) {
//        return userDao.get(userId);
//    }
//
//    /** {@inheritDoc} */
//    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
//    public List<User> getUsers(Integer pageSize, Integer pageNumber, Map<String, String> sortOrders, Map<String, String> userFilters) {
//        return userDao.getUsers(pageSize, pageNumber, sortOrders, userFilters);
//    }
//
//    /** {@inheritDoc} */
//    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
//    public Long getUsersCount() {
//        return userDao.getUsersCount();
//    }
//
//    public String generateUuid() {
//        return UUID.randomUUID().toString();
//    }
//
//    /** {@inheritDoc} */
//    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
//    public User getUserByVerificationKey(final String key) {
//        return userDao.getUserByVerificationKey(key);
//    }
}
