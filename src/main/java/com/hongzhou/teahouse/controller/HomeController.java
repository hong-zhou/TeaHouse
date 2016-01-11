package com.hongzhou.teahouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("greeting", "Welcome to Hong's Tea House!");
		model.addAttribute("tagline", "The world's best tea");
		
		return "welcome";
	}
	
	@RequestMapping("/welcome/greeting")
	public String greeting(){
		return "welcome";
	}
}
