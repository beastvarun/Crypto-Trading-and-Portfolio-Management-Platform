package com.project1.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
 class homeController {
	
	
	@GetMapping
	public String home() {
		
		return "welcome home";
		
	}

}
