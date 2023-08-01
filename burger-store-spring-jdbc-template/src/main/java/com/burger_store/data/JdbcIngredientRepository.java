package com.burger_store.data;

import com.burger_store.samples.Burger;
import com.burger_store.samples.IngredientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcIngredientRepository {

    private JdbcTemplate jdbc;
    private List<String> ingredientVariants = new ArrayList<>();

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<String> retrieveIngredientVariantsList() {
        if(!ingredientVariants.isEmpty()) ingredientVariants.clear();
        Arrays.stream(IngredientType.values()).forEach(e -> ingredientVariants.add(e.toString().toLowerCase()));
        ingredientVariants.removeIf(e -> e.contains("id"));
        return ingredientVariants;
    }

    @Override
    public void save(Burger burger, Integer burgerId) {
        List<String> ingredients = burger.getIngredients();
        List pscList = fillPscList(ingredients ,burgerId);
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
                "INSERT INTO ingredients(" +
                        "id, burger_id, lettuce, bacon, tomato, onion, pickles, cheese, mayonnaise, ketchup)" +
                        " VALUES (?,?,?,?,?,?,?,?,?,?)", Types.INTEGER, Types.INTEGER, Types.BOOLEAN, Types.BOOLEAN,
                Types.BOOLEAN, Types.BOOLEAN, Types.BOOLEAN, Types.BOOLEAN, Types.BOOLEAN, Types.BOOLEAN)
                .newPreparedStatementCreator(pscList);
        jdbc.update(psc);
    }

    private List fillPscList(List<String> ingredients, Integer burgerId) {
        List rowList = new ArrayList(10);
        for (int i = 0; i < 2; i++) rowList.add(burgerId);
        for (String ingredientVariant : ingredientVariants) {
            if(ingredients.contains(ingredientVariant)) rowList.add(true);
            else rowList.add(false);
        }
        return rowList;
    }


}
