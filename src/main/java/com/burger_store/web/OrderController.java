package com.burger_store.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("makeOrder")
public class OrderController {
	
	@GetMapping
	public String showForm() {
		return "";
	}
	

}
