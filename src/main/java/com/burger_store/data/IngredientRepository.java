package com.burger_store.data;


import java.util.List;

import com.burger_store.samples.Ingredient;


public interface IngredientRepository {
	public List<String> retrieveIngredientVariantsList();
	Ingredient save(Ingredient ingredient);
}
