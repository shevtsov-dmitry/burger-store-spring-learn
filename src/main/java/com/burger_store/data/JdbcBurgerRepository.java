package com.burger_store.data;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.burger_store.samples.Burger;
import com.burger_store.samples.Order;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcBurgerRepository implements BurgerRepository {
	private final JdbcTemplate jdbc;
	private Integer latestKey;
	private final Order order = new Order();
	public JdbcBurgerRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public Integer getLatestAutoIncrementedId() {
		Burger burger = new Burger();
		try {
			Integer latestId = jdbc.queryForObject("SELECT MAX(id) FROM burger", Integer.class);
			burger.setId(latestId);
			return latestKey;
		} catch (DataAccessException e) {
			burger.setId(1);
			return 1;
		}
	}

	@Override
	public void save(List<Burger> orderComponents, Integer orderId) {
		for (Burger burger : orderComponents) {
			burger.setDateCreated(new Date());
			var key = new GeneratedKeyHolder();
			var pscf = new PreparedStatementCreatorFactory(
					"INSERT INTO burger(name, datecreated, ingredient_id, order_id) VALUES (?,?,?,?)", Types.VARCHAR,
					Types.TIMESTAMP, Types.INTEGER, Types.INTEGER);
			pscf.setReturnGeneratedKeys(true);
			var psc = pscf.newPreparedStatementCreator(
					List.of(burger.getName(), new Timestamp(burger.getDateCreated().getTime()),
							this.getLatestAutoIncrementedId(), order.getId()));
			jdbc.update(psc, key);
		}

	}
}
