package com.hongzhou.teahouse.service;

import com.hongzhou.teahouse.domain.Cart;

public interface CartService {

	Cart create(Cart cart);
	
	Cart read(String cartId);
	
	void update(String cartId, Cart cart);
	
	void delete(String cartId);
	
	Cart validate(String cartid);
}
