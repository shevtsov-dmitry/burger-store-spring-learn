package com.burger_store.web;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.burger_store.data.BurgerRepository;
import com.burger_store.data.IngredientRepository;
import com.burger_store.data.JdbcIngredientRepository;
import com.burger_store.samples.Burger;

import jakarta.servlet.http.HttpServletRequest;

@Controller
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
	
    @PostMapping
    public String process(@ModelAttribute("burger") Burger burger, 
    		Errors errors, HttpServletRequest request){ // TODO valid burger inputs
    	String[] selectedIngredients = request.getParameterValues("ingredient");
    	log.info(Arrays.toString(selectedIngredients));
		/*
		 * if (selectedIngredients != null) { for (String ingredient : 
		 * selectedIngredients) { burger.addIngredient(ingredient); } }
		 */
    	log.info("BURGER INGREDINTS UNDER THIS TEXT");
    	log.info(burger.getIngredients().toString()); 
    	// order.add(saved)
    	
        return ""; 
    }
}
