package com.burger_store.data;

import com.burger_store.samples.Order;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository{

    private final JdbcTemplate jdbc;

    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        Timestamp placedAt = new Timestamp(order.getPlacedAt().getTime());
        String sql = "INSERT INTO order (placedat, city, street, apartment) VALUES(?,?,?,?)";
        var pscf = new PreparedStatementCreatorFactory(sql,
                Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR );
        pscf.setReturnGeneratedKeys(true);
        var psc = pscf.newPreparedStatementCreator(Arrays.asList(order.getOrderComponents()));
        jdbc.update(psc);
        return order;
    }
}
