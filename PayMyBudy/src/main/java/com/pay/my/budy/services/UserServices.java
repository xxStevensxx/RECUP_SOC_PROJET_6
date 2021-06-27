package com.pay.my.budy.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pay.my.budy.model.User;
import com.pay.my.budy.repository.UserRepository;

@Service
public class UserServices implements UserDetailsService {
	
	@Autowired
	MsgManager msgManager;
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServices(UserRepository userRepository) {
		this.userRepository = userRepository;
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Objects.requireNonNull(username);
		User user = userRepository.findUserWithName(username)
					.orElseThrow(() -> new UsernameNotFoundException(msgManager.errMsgManger(6)));
					
			return (UserDetails) user;
	}

}
