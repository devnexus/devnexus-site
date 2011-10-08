package com.devnexus.ting.core.dao;

import com.devnexus.ting.core.model.User;

public interface UserDao  extends GenericDao < User, Long > {

	User getUserByUsername(String trim);

}
