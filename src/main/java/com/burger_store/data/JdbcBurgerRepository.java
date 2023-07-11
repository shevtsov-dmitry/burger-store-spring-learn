package com.burger_store.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.burger_store.samples.Burger;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcBurgerRepository implements BurgerRepository {
	private final JdbcTemplate jdbc;

	public JdbcBurgerRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public void save(List<Burger> orderComponents, KeyHolder keyHolder) {
		for (Burger burger : orderComponents) {
			burger.setDateCreated(new Date());
			PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
					"INSERT INTO burger(name, datecreated) VALUES (?,?)",
					Types.VARCHAR, Types.TIMESTAMP);
			pscf.setReturnGeneratedKeys(true);
			PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
					List.of(burger.getName(), new Timestamp(burger.getDateCreated().getTime()))
			);
			jdbc.update(psc, keyHolder);
		}

	}
}
