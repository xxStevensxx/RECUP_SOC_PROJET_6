package com.pay.my.budy.dao;

import java.util.List;

import com.pay.my.budy.model.User;

public interface UserDao {

	void add(User user);

	void update(User user);

	void delete(User user);

	User getUserById(int id);

	List<?> getAllUser();

}
