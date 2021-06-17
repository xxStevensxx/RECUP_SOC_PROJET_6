package com.pay.my.budy.repository;


import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.pay.my.budy.dao.UserDaoImpl;
import com.pay.my.budy.model.User;

@Transactional
@Repository
@Component
public class UserRepositoryImpl implements UserRepository {
	
	public UserRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}


	@Autowired
	UserDaoImpl userDaoImpl;

	

	@Override
	public void create(User user) {

		userDaoImpl.add(user);

	}
	

	@Override
	public User getByName(String name) {
		
		ArrayList<User> userName = new ArrayList<User>();
		userName = userDaoImpl.getAllUser();
		
		User nameUser = new User();
		name = name.toLowerCase().trim();

		for (int i = 0; i < userName.size(); i++) {

			if (name.contains(userName.get(i).getFirstName().toLowerCase().trim())) {
				
				System.out.println(userName.get(i));
				 nameUser = userName.get(i);
				 	return nameUser ;

			}

		}

		return nameUser;
	}
	
	@Override
	public User getByEmail(String email) {
		
		ArrayList<User> userEmail = new ArrayList<User>();
		userEmail = userDaoImpl.getAllUser();
		
		User emailUser = new User();
		email = email.toLowerCase().trim();

		for (int i = 0; i < userEmail.size(); i++) {

			if (email.contains(userEmail.get(i).getEmail().toLowerCase().trim())) {
				
				System.out.println(userEmail.get(i));
				emailUser = userEmail.get(i);
				 	return emailUser ;

			}

		}

		return emailUser;
	}
	
	
	@Override
	public ArrayList<User> getAll() {
		
		return userDaoImpl.getAllUser();
	
	}
	

	@Override
	public void save(User user) {
		userDaoImpl.update(user);
	}
	

	@Override
	public void delete(User user) {
		userDaoImpl.delete(user);

	}

	@Override
	public User getById(int id) {
		
		User idUser = userDaoImpl.getUserById(id);
			
			return  idUser;
			
	}

}
