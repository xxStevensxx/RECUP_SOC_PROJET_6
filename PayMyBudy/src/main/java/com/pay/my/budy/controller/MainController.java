package com.pay.my.budy.controller;


import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pay.my.budy.model.User;

import com.pay.my.budy.services.UserServices;

@Controller
public class MainController {

	@Autowired
	UserServices userServices;

	// GET

	@RolesAllowed("admin")
	@RequestMapping(value = "/admin")
	public String getAdmin() {
		
		return "Welcome, Admin"; 
	}
	
	
	@GetMapping(value = { "/", "/home" })
	public String rootController() {


		return "/layouts/index";

	}

	@GetMapping(value = "/transfert")
	public String transfertController() {

		return "/layouts/transfert";

	}

	@GetMapping(value = "/profil")
	public String profilController() {

		return "/layouts/profil";

	}

	@GetMapping(value = "/contact")
	public String contactController() {

		return "/layouts/contact";

	}

	@GetMapping(value = "/login")
	public String loginController(@ModelAttribute("FormLogin") User user, BindingResult result) {

		return "/layouts/login";

	}
	
	@GetMapping(value = "/logout")
	public String logoutController() {

		return "/layouts/logout";

	}

	@GetMapping(value = "/signUp")
	public String signupController(@ModelAttribute("FormSignUp") User user, BindingResult result) {

		return "/layouts/signUp";

	}

	// POST
	
	
	@PostMapping(value = "/login")
	public String performLogin(@ModelAttribute("FormLogin")@Valid User user, BindingResult result) {
		
	if (userServices.signIn(user) != null) {
			
			result.addError(userServices.signIn(user));

		}
		
		if (!result.hasErrors()) {
			
				return "/layouts/loginSuccess";

		}
		
				return "/layouts/login";

	}
	
	

	@PostMapping(value = "/signUp")
	public String performSignup(@ModelAttribute("FormSignUp")@Valid User user, BindingResult result) {

		if (userServices.signUp(user) != null) {
			
			result.addError(userServices.signUp(user));

		}
		
		if (!result.hasErrors()) {

			userServices.addUser(user);
			
				return "/layouts/SignUpSuccess";

		}
		
				return "/layouts/signUp";

	}

}
