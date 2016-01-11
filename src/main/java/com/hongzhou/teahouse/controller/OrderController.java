package com.hongzhou.teahouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hongzhou.teahouse.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order/P1234/20")
	public String process(){
	
		orderService.processOrder("P1234", 20);
		return "redirect:/products";
	}

}
