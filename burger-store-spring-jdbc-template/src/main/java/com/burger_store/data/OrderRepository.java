package com.burger_store.data;

import java.util.List;
import java.util.Map;

import com.burger_store.samples.Order;

public interface OrderRepository {
	void save(Order order);
}
