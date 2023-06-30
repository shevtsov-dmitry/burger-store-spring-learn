package com.burger_store.samples;

import javax.validation.constraints.Size;

public class Ingredient {
    private Long id;
    private String name;
    private IngredientType type;

    public Ingredient() {
    }

    public Ingredient(String name, IngredientType type) {
        this.name = name;
        this.type = type;
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

    public IngredientType getType() {
        return type;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }
}
