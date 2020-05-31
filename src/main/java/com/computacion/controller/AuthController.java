package com.computacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping("/")
	public String home() {
		return "/index"
;	}
	
	@GetMapping("/login")
	public String login() {
//		model.addAttribute("user","");
		return "/login";
	}
	
	
	
	
	
}
