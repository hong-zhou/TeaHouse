package com.hongzhou.teahouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hongzhou.teahouse.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/customers")
	public String processCustomer(Model model){
		
		model.addAttribute("customers", customerService.getAllCustomers());
		
		return "customers";
		
	}

}
