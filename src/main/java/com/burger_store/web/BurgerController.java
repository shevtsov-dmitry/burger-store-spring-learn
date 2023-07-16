package com.burger_store.web;

import com.burger_store.data.IngredientRepository;
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
	private IngredientRepository ingredientRepo;
	private final Order order = new Order();
	private final List<Burger> burgersList = order.setOrderComponents(new ArrayList<>());
    
	public BurgerController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	@GetMapping
    public String showForm(Model model, Burger burger){
		burger.setIngredients(ingredientRepo.retrieveIngredientVariantsList());
    	model.addAttribute("ingredientVariants", burger.getIngredients());
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
