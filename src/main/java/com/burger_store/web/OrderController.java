package com.burger_store.web;

import com.burger_store.samples.Burger;
import com.burger_store.samples.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("order")
@RequestMapping("makeOrder")
public class OrderController {

	private final JdbcTemplate jdbc;
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	@ModelAttribute(name="order")
	public Order createOrder(){
		return new Order();
	}

	public OrderController(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@GetMapping
	public String showForm(Model model) {
		Order order = (Order) model.getAttribute("order");

		for (Burger orderComponent : order.getOrderComponents()) {
			log.info(orderComponent.getIngredients().toString());
		}
		model.addAttribute("orderComponents", order.getOrderComponents());


		return "html/makeOrder";
	}

	@PostMapping
	public String makeOrder(){

		return "";
	}


}
;