package com.burger_store.web;

import com.burger_store.data.BurgerRepository;
import com.burger_store.data.JdbcIngredientRepository;
import com.burger_store.samples.Burger;
import com.burger_store.samples.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("order")
@RequestMapping("/assembleBurger")
public class BurgerController {
		
	
    private static final Logger log = LoggerFactory.getLogger(BurgerController.class);
    private BurgerRepository burgerRepo;
    private final JdbcTemplate jdbc;
	private final Order order = new Order();
	private final List<Burger> burgersList = order.setOrderComponents(new ArrayList<>());
    
	public BurgerController(JdbcTemplate jdbc) {
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
		return this.order;
	}
    @PostMapping
	public String process(Burger burger,
						  Errors errors,
						  @ModelAttribute Order order
						  ){ // TODO valid burger inputs

		order.addBurger(burger);
		return "redirect:/makeOrder";
	}
}
