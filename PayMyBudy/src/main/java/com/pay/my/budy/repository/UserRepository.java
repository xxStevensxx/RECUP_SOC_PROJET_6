package com.pay.my.budy.repository;

import java.util.ArrayList;

import com.pay.my.budy.model.User;

public interface UserRepository {

	void create(User user);

	User getById(int id);

	User getByName(String name);
	
	User getByEmail(String email);
	
	ArrayList<User> getAll();
	
	void save(User user);

	void delete(User user);

}
