package com.hongzhou.teahouse.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hongzhou.teahouse.domain.Product;

public interface ProductService {

	List<Product> getAllProducts();
	
	List<Product> getProductsByCategory(String category);
	
	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	Product getProductById(String productId);
	
	List<Product> getProductsByManufacturer(String manufacturer);
	
	void addProduct(Product product);
}
