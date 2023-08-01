package com.burger_store.data;


import java.util.List;

import com.burger_store.samples.Burger;
import com.burger_store.samples.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository{
	public List<String> retrieveIngredientVariantsList();
	void save(Burger burger, Integer burgerId);

}

