package com.hongzhou.teahouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhou.teahouse.domain.Order;
import com.hongzhou.teahouse.domain.Product;
import com.hongzhou.teahouse.domain.repository.OrderRepository;
import com.hongzhou.teahouse.domain.repository.ProductRepository;
import com.hongzhou.teahouse.service.CartService;
import com.hongzhou.teahouse.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartService cartService;
	
	public void processOrder(String productId, int quantity) {
		Product productById = productRepository.getProductById(productId);
		
		if(productById.getUnitsInStock() < quantity){
			throw new IllegalArgumentException("Out of Stock. Available" +
					" Units in stock" + productById.getUnitsInStock());
		}
		
		productById.setUnitsInStock(productById.getUnitsInStock() - quantity);
	}

	public Long saveOrder(Order order) {
		Long orderId = orderRepository.saveOrder(order);
		cartService.delete(order.getCart().getCartId());
		return orderId;
	}

}
