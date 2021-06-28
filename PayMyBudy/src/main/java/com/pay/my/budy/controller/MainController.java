package com.pay.my.budy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pay.my.budy.repository.UserRepository;

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

}
