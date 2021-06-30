package com.pay.my.budy.controller;

<<<<<<< Updated upstream
import org.springframework.beans.factory.annotation.Autowired;
=======
<<<<<<< Updated upstream
=======
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> Stashed changes
>>>>>>> Stashed changes
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pay.my.budy.model.User;

<<<<<<< Updated upstream
import com.pay.my.budy.repository.UserRepository;

=======
<<<<<<< Updated upstream
>>>>>>> Stashed changes
@Controller
public class MainController {

	@Autowired
	UserRepository userRepository;
	
	

	@GetMapping(value = "/")
	public String rootController() {

		return "/layouts/index";

	}

	@GetMapping(value = "/home")
	public String homeController() {
<<<<<<< Updated upstream

=======
		
=======
import com.pay.my.budy.services.UserServices;

@Controller
public class MainController {

	@Autowired
	UserServices userServices;

	// GET

	@GetMapping(value = { "/", "/home" })
	public String rootController() {

>>>>>>> Stashed changes
>>>>>>> Stashed changes
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

	@GetMapping(value = "/signUp")
	public String signupController(@ModelAttribute("FormSignUp") User user, BindingResult result) {

		return "/layouts/signUp";

	}

	// POST

	@PostMapping(value = "/signUp")
	public String postSignupForm(@ModelAttribute("FormSignUp")@Valid User user, BindingResult result) {

		if (userServices.check(user) != null) {
			
			result.addError(userServices.check(user));

		}
		
		if (!result.hasErrors()) {

			userServices.addUser(user);
			
				return "/layouts/loginSuccess";

		}
		
				return "/layouts/signUp";

	}

}
