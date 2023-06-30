package com.burger_store.samples;

import java.util.Date;
import java.util.List;

public class Burger {
    private Long id;
    private Date dateCreated;
    private String name;
    private List<Ingredient> ingredients;

    public Burger() {
    }

    public Burger(Date dateCreated, String name, List<Ingredient> ingredients) {
        this.dateCreated = dateCreated;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
