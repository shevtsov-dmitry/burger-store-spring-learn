package com.burger_store.data;

import com.burger_store.samples.Burger;
import com.burger_store.samples.Ingredient;
import com.burger_store.samples.Order;

import ch.qos.logback.core.subst.Token.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;
import org.thymeleaf.spring6.expression.Fields;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{

	private JdbcTemplate jdbc;
	private final Burger burger = new Burger();
	private final Order order = new Order();
	private final Ingredient ingredient = new Ingredient();

	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public List<String> retrieveIngredientVariantsList() {
		String sqlStatement = """
			SELECT column_name \r
			FROM information_schema.columns \r
			WHERE table_name = 'ingredients';\r
			""";
		List<String> ingredientVariants = jdbc.queryForList(sqlStatement, String.class);
		ingredientVariants.removeIf(e-> e.contains("id"));
		return ingredientVariants;
	}

	@Override
	public void save() {
		List<String> ingredients = new ArrayList<>();
		for (Burger burgerElement : order.getOrderComponents()) {
			ingredients.addAll(burgerElement.getIngredients());
		}
		List<Boolean> ingredientsPresent = new ArrayList<>();
		this.retrieveBooleanListOfChosenIngredients(ingredients);
		PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
				"INSERT INTO ingredients(" +
						"id, burger_id, lettuce, bacon, tomato, onion, pickles, cheese, mayonnaise, ketchup)" +
						" VALUES (?,?,?,?,?,?,?,?,?)",Types.INTEGER, Types.INTEGER, Types.BOOLEAN, Types.BOOLEAN, Types.BOOLEAN,
				Types.BOOLEAN, Types.BOOLEAN ,Types.BOOLEAN , Types.BOOLEAN, Types.BOOLEAN)
				.newPreparedStatementCreator(Arrays.asList(burger.getId(), burger.getId(), /*TODO insert list here */));
		jdbc.update(psc);
		ingredients.clear();
		ingredientsPresent.clear();
		
	}

	/*
	 * PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
	 * "insert into Taco (name, createdAt) values (?, ?)", Types.VARCHAR,
	 * Types.TIMESTAMP ).newPreparedStatementCreator( Arrays.asList( taco.getName(),
	 * new Timestamp(taco.getCreatedAt().getTime())));
	 * 
	 * jdbc.update(psc);
	 */
}
