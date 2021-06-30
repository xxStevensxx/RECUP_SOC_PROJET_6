package com.pay.my.budy.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.validation.ObjectError;
import com.pay.my.budy.config.SpringSecurityConfig;
import com.pay.my.budy.model.User;
import com.pay.my.budy.repository.UserRepository;

@Service

public class UserServices {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SpringSecurityConfig security;
	
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
		
		security.cryptePswrd(user);

			return userRepository.save(user);
	}
	
	
	//BONUS VERIF DE ROUTINE DOUBLONS CASSE ETC...
	
	
	public ObjectError check(User user) {	
		
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
	
}
