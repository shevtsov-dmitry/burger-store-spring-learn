package com.burger_store.samples;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

		public class Burger {
			private Integer id;
			@NotBlank(message = "you need to name your creature")
			@Size(min=3, max=10, message = "name cannot be longer than 10 characters")
			private String name;
			private Date dateCreated;
			@NotNull
			@Size(min=1, message = "you must to choose at least one ingredient")
			private List<String> ingredients;

    public Burger() {}

	public Burger(String name, Date dateCreated, List<String> ingredients) {
		super();
		this.name = name;
		this.dateCreated = dateCreated;
		this.ingredients = ingredients;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
    
    public void addIngredient(String ingredient) {
    	this.ingredients.add(ingredient);
    }

}
