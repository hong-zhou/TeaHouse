package com.hongzhou.teahouse.domain.repository;

import com.hongzhou.teahouse.domain.Cart;

public interface CartRepository {

	Cart create(Cart cart);
	
	Cart read(String cartId);
	
	void update(String cartId, Cart cart);
	
	void delete(String cartId);
}
