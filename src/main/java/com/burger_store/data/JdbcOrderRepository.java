package com.burger_store.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.burger_store.samples.Order;

@Repository
public class JdbcOrderRepository implements OrderRepository {

	private final JdbcTemplate jdbc;
	
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
    @Override
	public void save(Order order) {
    	var key = new GeneratedKeyHolder();
		order.setPlacedAt(new Date());
		Timestamp placedAt = new Timestamp(order.getPlacedAt().getTime());
		String sql = "INSERT INTO \"order\" (placedat, city, street, apartment) VALUES(?,?,?,?)";
		var pscf = new PreparedStatementCreatorFactory(sql, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR);
		pscf.setReturnGeneratedKeys(true);
		var psc = pscf.newPreparedStatementCreator(
				List.of(placedAt, order.getCity(), order.getStreet(), order.getApartment()));
		jdbc.update(psc, key);
		var keys = key.getKeyList();
		order.setId((Integer) keys.get(keys.size() - 1).get("id"));
	}
}
