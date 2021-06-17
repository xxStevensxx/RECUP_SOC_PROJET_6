package com.pay.my.budy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping(value = "/")
	public String rootController() {
		
		return "/layouts/index";
		
	}
	
	
	@GetMapping(value = "/home")
	public String homeController() {
		
		return "/layouts/index";
		
	}
	
	
	@GetMapping(value = "/login")
	public String loginController() {
		
		return "/layouts/login";
		
	}

}
