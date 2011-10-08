package com.devnexus.ting.core.dao.jpa;

import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.UserDao;
import com.devnexus.ting.core.model.User;

@Repository("userDao")
public class UserDaoJpa extends GenericDaoJpa< User, Long>
                           implements UserDao {

    /** Constructor. */
    private UserDaoJpa() {
        super(User.class);
    }

	@Override
	public User getUserByUsername(String username) {
		return (User) super.entityManager.createQuery("select user from User user where user.username = :username")
		                   .setParameter("username", username)
		                   .getSingleResult();
	}

}
