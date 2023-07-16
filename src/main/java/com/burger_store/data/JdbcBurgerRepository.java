package com.burger_store.data;

import com.burger_store.samples.Burger;
import com.burger_store.samples.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

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
    public void save(Burger burger, Integer orderId) {
        var key = new GeneratedKeyHolder();
        burger.setDateCreated(new Date());
        Timestamp createdAt = new Timestamp(burger.getDateCreated().getTime());
        var pscf = new PreparedStatementCreatorFactory(
                "INSERT INTO burger(name, datecreated, order_id) VALUES (?,?,?)",
                Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER);
        pscf.setReturnGeneratedKeys(true);
        var psc = pscf.newPreparedStatementCreator(
                List.of(burger.getName(), createdAt, orderId));

        jdbc.update(psc, key);

        var keys = key.getKeyList();
        burger.setId((Integer) keys.get(keys.size() - 1).get("id"));
    }
}
