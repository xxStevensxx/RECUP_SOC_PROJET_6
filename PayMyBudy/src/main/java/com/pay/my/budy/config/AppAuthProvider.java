package com.pay.my.budy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.pay.my.budy.services.MsgManager;

public class AppAuthProvider extends DaoAuthenticationProvider{
	
	@Autowired
	UserDetailsService userDetailsServices;
	
	@Autowired
	MsgManager msgManager;
	
	
	
	@Override
	public Authentication authenticate(Authentication authentication) {
	
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		
		String name = auth.getName();
		String password = auth.getCredentials().toString();
		
		
		UserDetails userName = userDetailsServices.loadUserByUsername(name);
		UserDetails userPswrd = userDetailsServices.loadUserByUsername(password);

		
		
		if (userName == null) {
			
			throw new BadCredentialsException(msgManager.logMessage(6) + auth.getPrincipal());

		}
		
		
		if(userPswrd == null) {
			
			throw new BadCredentialsException(msgManager.logMessage(6) + auth.getPrincipal());

			
		}
		
				return new UsernamePasswordAuthenticationToken(userName, null, auth.getAuthorities());
	}
	
	
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
    
}
