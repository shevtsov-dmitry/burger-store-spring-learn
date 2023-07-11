package com.burger_store.data;

import com.burger_store.samples.Burger;
import com.burger_store.samples.Order;
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
public class JdbcIngredientRepository implements IngredientRepository{

	private JdbcTemplate jdbc;

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
	public void save(Order order) {
		List<String> ingredients = new ArrayList<>();
		for (Burger burger : order.getOrderComponents()) {
			ingredients.addAll(burger.getIngredients());
		}
		PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
				"INSERT INTO ingredients(" +
						"lettuce, bacon, tomato, onion, pickles, cheese, mayonnaise, ketchup)" +
						" VALUES (?,?,?,?,?,?,?,?)", Types.BOOLEAN, Types.BOOLEAN, Types.BOOLEAN,
				Types.BOOLEAN, Types.BOOLEAN ,Types.BOOLEAN , Types.BOOLEAN, Types.BOOLEAN)
				.newPreparedStatementCreator(Arrays.asList(/* TODO */));
		jdbc.update(psc);
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
