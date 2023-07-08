package com.burger_store.data;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.burger_store.samples.Ingredient;

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
	public Ingredient save(Ingredient ingredient) {
//		jdbc.update()
		return null;
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
