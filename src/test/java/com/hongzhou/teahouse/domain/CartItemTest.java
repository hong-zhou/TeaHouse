package com.hongzhou.teahouse.domain;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartItemTest {

	private CartItem cartItem;
	
	@Before
	public void setup(){
		cartItem = new CartItem();
	}
	
	@Test
	public void cartItem_total_price_should_be_equal_to_product_unit_price_in_case_of_single_quantity(){
		//Arrange
		Product shangnanTea = new Product("P1234", "ShangNan Tea", new BigDecimal(20));
		cartItem.setProduct(shangnanTea);
		
		//Act
		BigDecimal totalPrice = cartItem.getTotalPrice();
		
		//Assert
		Assert.assertEquals(shangnanTea.getUnitPrice(), totalPrice);
	}
}
