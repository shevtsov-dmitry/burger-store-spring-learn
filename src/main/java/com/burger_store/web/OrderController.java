package com.burger_store.web;

import com.burger_store.data.BurgerRepository;
import com.burger_store.data.OrderRepository;
import com.burger_store.samples.Burger;
import com.burger_store.samples.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("order")
@RequestMapping("makeOrder")
public class OrderController {

	private final JdbcTemplate jdbc;
	private OrderRepository orderRepository;
	private BurgerRepository burgerRepo;
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	public OrderController(JdbcTemplate jdbc, OrderRepository orderRepository, BurgerRepository burgerRepo) {
		this.jdbc = jdbc;
		this.orderRepository = orderRepository;
		this.burgerRepo = burgerRepo;
	}

//	@ModelAttribute(name="order")
//	public Order createOrder(){
//		return new Order();
//	}

	@GetMapping
	public String showForm(Model model) {
		Order order = (Order) model.getAttribute("order");
		model.addAttribute("orderComponents", order.getOrderComponents());

		return "html/makeOrder";
	}

	@PostMapping
	public String makeOrder(@ModelAttribute Order order,
							@ModelAttribute Burger burger,
							SessionStatus session){
//		log.info(order.getCity());
//		log.info(order.getStreet());
//		log.info(order.getApartment());
		orderRepository.save(order);
		burgerRepo.save(order.getOrderComponents(), order.getId());
		log.info(order.getId().toString());
		session.setComplete();
		return "redirect:/";
	}


}