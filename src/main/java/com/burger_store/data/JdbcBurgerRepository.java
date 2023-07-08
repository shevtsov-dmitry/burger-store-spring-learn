package com.burger_store.data;

import org.springframework.stereotype.Repository;

import com.burger_store.samples.Burger;

@Repository
public class JdbcBurgerRepository implements BurgerRepository {

	@Override
	public Burger save(Burger burger) {
		
		return null;
	}
}
