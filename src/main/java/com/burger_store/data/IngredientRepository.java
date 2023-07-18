package com.burger_store.data;


import java.util.List;

import com.burger_store.samples.Burger;


public interface IngredientRepository {
	public List<String> retrieveIngredientVariantsList();
	void save(Burger burger, Integer burgerId);

}
