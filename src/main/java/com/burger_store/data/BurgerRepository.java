package com.burger_store.data;

import com.burger_store.samples.Burger;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public interface BurgerRepository {
     void save(List<Burger> orderComponents, Integer orderId);
}
