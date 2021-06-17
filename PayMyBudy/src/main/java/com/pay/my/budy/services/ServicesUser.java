package com.pay.my.budy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.pay.my.budy.config.HibernateFactory;
import com.pay.my.budy.config.Params;
import com.pay.my.budy.config.SpringSecurityConfig;
import com.pay.my.budy.model.Friends;
import com.pay.my.budy.model.User;
import com.pay.my.budy.repository.UserRepositoryImpl;

@Service
public class ServicesUser {

	@Autowired
	MsgManager msgManager;
	
	@Autowired
	SpringSecurityConfig securityConfig;
	
	@Autowired
	UserRepositoryImpl userRepo;
	
	private Session session;
	
	
	ObjectError objError = null;
	String errorInput;
	
	
	public void initSession() {

		session = HibernateFactory.getSessionFactory(Params.HIBERNATECONFIG).openSession();

	}
	
	
	public ObjectError secureCaseInputUser(User user) {

		if (!user.getEmail().isBlank() || !user.getEmail().isEmpty()) {

			user.setEmail(user.getEmail().toLowerCase());
			return objError;

		} else {
			
			ObjectError objError = new ObjectError("globalErr", msgManager.errMsgManger(5));
			
				 return objError;

		}

	}

	public ObjectError emailPatternValidator(String email) {

		String regex = "^(.+?)@(.+?)$";

		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches() == false) {

			ObjectError objError = new ObjectError("globalErr", msgManager.errMsgManger(4));

				return objError;

		}
		
		
			return objError;

	}
	
	
	
	public ObjectError checkDuplicateEmail(User user) {
		
		initSession();
		
		Query<?> query = session.createQuery("select user.email from com.pay.my.budy.model.User user");
		List<?> result = query.getResultList();
		
		for(int i = 0; i < result.size(); i++) {
			
			if (user.getEmail().trim().equals(result.get(i))) {
				
				ObjectError objError = new ObjectError("globalErr", msgManager.errMsgManger(6));
				
				session.close();
					return objError;
				
			}
		}
		
		session.close();
			return objError;
		
	}
	
	
	
	public ObjectError UserConnection(ArrayList<User>listUser, User user) {
		

		
		for (int i = 0; i < listUser.size(); i++) {
			
			if (listUser.get(i).getEmail().equals(user.getEmail())) {
				
				System.err.println("mail ok");
				
				//securityConfig.passwordEncoder().matches(user.getPassword(), listUser.get(i).getPassword());
				if (securityConfig.passwordEncoder().matches(user.getPassword(), listUser.get(i).getPassword())) {
					
					System.err.println("pswrd ok");

					//Create session and connection
					
					
				}else {
					
					ObjectError objError = new ObjectError("globalErr", msgManager.errMsgManger(7));
					
						return objError;
				}

			}

		}
		
		return objError;
				
	}
	
	

	public ArrayList<ObjectError> registerServices(User user) {
		
		ArrayList<ObjectError> resultServices = new ArrayList<>();
		
		resultServices.add(secureCaseInputUser(user));
		resultServices.add(emailPatternValidator(user.getEmail()));
		resultServices.add(checkDuplicateEmail(user));
		
		return resultServices;
		
	}
	
	
	public ArrayList<ObjectError> loginServices(User user) {
		
		ArrayList<ObjectError> resultServices = new ArrayList<>();
		
		resultServices.add(secureCaseInputUser(user));
		resultServices.add(emailPatternValidator(user.getEmail()));
		
			return resultServices;
		
	}
	
	
	public ArrayList<User> isConnected(User user, HttpServletRequest request) {
		
		//si l'utilisateur est authentifié on recupere les infos 
		HttpSession session = request.getSession(false);
		User userInfo = new User();
		ArrayList<User> listConnections = new ArrayList<>();
		
		
		if (session != null) {
			
			String emailSession = session.getAttribute("Email").toString();
			userInfo = userRepo.getByEmail(emailSession);
				
		}
		
		//On recupere toutes les connections et on les ajoute dans une liste qu 'on utilisera pour notre vue
		if (userInfo.getFriends() != null || !userInfo.getFriends().isEmpty()) {
			
				User friends = null;
			
				for (int i = 0; i < userInfo.getFriends().size(); i++) {
					
					for(Friends myFriend : userInfo.getFriends()) {
						
						for(int idFriend : myFriend.getIdFriend()) {
							
							friends = userRepo.getById(idFriend);

						}
						
		
					}
																			

					listConnections.add(friends);
					
						return listConnections;
					
			}
			
		} 
			//si il a pas d'amis on retourne un tableau vide
				return listConnections;
	}
	
	

}

