package com.burger_store.web;

import com.burger_store.data.BurgerRepository;
import com.burger_store.data.IngredientRepository;
import com.burger_store.data.OrderRepository;
import com.burger_store.samples.Burger;
import com.burger_store.samples.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("order")
@RequestMapping("makeOrder")
public class OrderController {

	private OrderRepository orderRepository;
	private BurgerRepository burgerRepo;
	private IngredientRepository ingredientRepo;
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	public OrderController(OrderRepository orderRepository,
						   BurgerRepository burgerRepo, IngredientRepository ingredientRepo) {
		this.orderRepository = orderRepository;
		this.burgerRepo = burgerRepo;
		this.ingredientRepo = ingredientRepo;
	}

	@GetMapping
	public String showForm(Model model) {
		Order order = (Order) model.getAttribute("order");
		model.addAttribute("orderComponents", order.getOrderComponents());

		return "html/makeOrder";
	}

	@PostMapping
	public String makeOrder(@ModelAttribute("order") Order order, SessionStatus session){
		orderRepository.save(order);
		for (Burger burger: order.getOrderComponents()) {
			burgerRepo.save(burger, order.getId());
			ingredientRepo.save(burger, burger.getId());
		}

		session.setComplete();
		return "redirect:/";
	}


}