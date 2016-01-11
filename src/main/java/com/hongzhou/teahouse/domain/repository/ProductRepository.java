package com.hongzhou.teahouse.domain.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hongzhou.teahouse.domain.Product;

public interface ProductRepository {

	List<Product> getAllProducts();
	
	Product getProductById(String productId);
	
	List<Product> getProductsByCategory(String category);
	
	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	List<Product> getProductsByManufacturer(String manufacturer);
	
	void addProduct(Product product);
}
