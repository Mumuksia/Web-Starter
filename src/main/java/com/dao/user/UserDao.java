package com.dao.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dao.Dao;
import com.dao.model.entity.User;


public interface UserDao extends Dao<User, Long>, UserDetailsService
{

	User findByName(String name);

}
