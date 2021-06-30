package com.pay.my.budy.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.validation.ObjectError;
import com.pay.my.budy.config.SecurityConfiguration;
import com.pay.my.budy.model.User;
import com.pay.my.budy.repository.UserRepository;

@Service

public class UserServices {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SecurityConfiguration security;
	
	@Autowired
	MsgManager message;
	
	
	//CRUD
	public Iterable<User> getUsers() {

		return userRepository.findAll();

	}
	

	public Optional<User> getUserById(Integer id) {

		return userRepository.findById(id);
	}
	

	
	public User addUser(User user) {
		
		user.getEmail().trim().toLowerCase();
		security.cryptePswrd(user.getPassword());

			return userRepository.save(user);
	}
	
	
	public void deleteUsers(User user) {
		
		userRepository.deleteAll();
	}
	
	
	public void deleteUserById(Integer id) {
		
		userRepository.deleteById(id);
	}
	
	
	//BONUS VERIF DE ROUTINE DOUBLONS CASSE ETC...
	
	
	public ObjectError signUp(User user) {	
		
		ObjectError err = null;
		ArrayList<User> listUser = new ArrayList<User>(userRepository.findAll()); 
		

		for (int i = 0; i < listUser.size(); i++) {
							
				//On verifie que nos données ne sont pas null 
 				if (!user.getEmail().isBlank() && !	listUser.get(i).getEmail().isBlank()) {
					
					//On check les doublons
					if (user.getEmail().contains(listUser.get(i).getEmail())) {
						
						 return err = new ObjectError("email", message.logMessage(9));
											
					}
					
				}else {
					
					return err = new ObjectError("global", message.logMessage(4)); 
		
				}

		}
				
		return err;
	
	}
	
	
	public ObjectError signIn(User user) {	
		
		ObjectError err = null;
		ArrayList<User> listUser = new ArrayList<User>(userRepository.findAll()); 
		String email = user.getEmail().trim().toLowerCase();
		

		for (int i = 0; i < listUser.size(); i++) {
							
				//On verifie que nos données ne sont pas null 
				if (!email.isBlank() && !listUser.get(i).getEmail().isBlank()) {
					
					//On check si il existe
					if (!email.contains(listUser.get(i).getEmail())) {
						
						 return err = new ObjectError("email", message.logMessage(8));
											
					}
					
				}else {
					
					return err = new ObjectError("global", message.logMessage(4)); 
		
				}

		}
				
		return err;
	
	}
}
