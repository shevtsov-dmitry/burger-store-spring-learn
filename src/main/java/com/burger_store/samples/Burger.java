package com.burger_store.samples;

import java.util.Date;
import java.util.List;

public class Burger {
    private Long id;
    private String name;
    private Date dateCreated;
    private List<String> ingredients;

    public Burger() {}

	public Burger(String name, Date dateCreated, List<String> ingredients) {
		super();
		this.name = name;
		this.dateCreated = dateCreated;
		this.ingredients = ingredients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
