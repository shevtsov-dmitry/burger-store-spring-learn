package com.burger_store.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.burger_store.samples.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.burger_store.data.BurgerRepository;
import com.burger_store.data.IngredientRepository;
import com.burger_store.data.JdbcIngredientRepository;
import com.burger_store.samples.Burger;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("order")
@RequestMapping("/assembleBurger")
public class IngredientController {
		
	
    private static final Logger log = LoggerFactory.getLogger(IngredientController.class);
    private BurgerRepository designRepository; 
    private final JdbcTemplate jdbc;
    
	public IngredientController(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@GetMapping
    public String showForm(Model model){
    	var repo = new JdbcIngredientRepository(jdbc);  
    	List<String> ingredientVariants = repo.retrieveIngredientVariantsList();
    	model.addAttribute("ingredientVariants",ingredientVariants);
    	return "html/assembleBurger";
    }

	@ModelAttribute(name ="burger")
	public Burger createBurger() {
		return new Burger();
	}

	@ModelAttribute(name ="order")
	public Order createOrder(){
		return new Order();
	}
    @PostMapping
	public String process(Burger burger,
						  Errors errors,
						  @ModelAttribute Order order
						  ){ // TODO valid burger inputs
//		log.info(burger.getName());
//		log.info(burger.getIngredients().toString());
//		order = this.createOrder();
		order.setOrderComponents(new ArrayList<>());
		order.addBurger(burger);
		return "redirect:/makeOrder";
	}
}
