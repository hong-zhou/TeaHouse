package com.hongzhou.teahouse.domain.repository;

import com.hongzhou.teahouse.domain.Order;

public interface OrderRepository {

	Long saveOrder(Order order);
}
