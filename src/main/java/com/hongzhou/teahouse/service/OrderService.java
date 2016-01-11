package com.hongzhou.teahouse.service;

import com.hongzhou.teahouse.domain.Order;

public interface OrderService {
	
	void processOrder(String productId, int count);
	
	Long saveOrder(Order order);
}
