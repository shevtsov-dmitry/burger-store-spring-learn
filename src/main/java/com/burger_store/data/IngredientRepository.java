package com.burger_store.data;


import java.util.List;

import com.burger_store.samples.Ingredient;
import com.burger_store.samples.Order;


public interface IngredientRepository {
	public List<String> retrieveIngredientVariantsList();
	void save(Order order);

}
