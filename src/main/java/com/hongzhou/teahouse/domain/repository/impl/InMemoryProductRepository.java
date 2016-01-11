package com.hongzhou.teahouse.domain.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.hongzhou.teahouse.domain.Product;
import com.hongzhou.teahouse.domain.repository.ProductRepository;
import com.hongzhou.teahouse.exception.ProductNotFoundException;

@Repository
public class InMemoryProductRepository implements ProductRepository{

	private List<Product> listOfProducts = new ArrayList<Product>();
	
	public InMemoryProductRepository(){
		
		Product shangnanTea = new Product ("P1234", "ShangNan Tea", new BigDecimal(20));
		shangnanTea.setDescription("Green tea produced in Shangnan, China with the highest latitute where can grow tea trees.");
		shangnanTea.setCategory("GreenTea");
		shangnanTea.setManufacturer("Shangnan");
		shangnanTea.setUnitsInStock(1000);
		
		Product longJingTea = new Product ("P1235", "Xihu Longjing Tea", new BigDecimal(200));
		longJingTea.setDescription("The famous tea produced in West Lake of China with a history of 1200 years");
		longJingTea.setCategory("GreenTea");
		longJingTea.setManufacturer("Hangzhou");
		longJingTea.setUnitsInStock(1000);
		
		Product puerhTea = new Product ("P1236", "Pu-Erh Tea", new BigDecimal(160));
		puerhTea.setDescription("The famous tea produced in West Lake of China with a history of 1200 years");
		puerhTea.setCategory("DarkTea");
		puerhTea.setManufacturer("Yunnan");
		puerhTea.setUnitsInStock(1000);
		
		listOfProducts.add(shangnanTea);
		listOfProducts.add(longJingTea);
		listOfProducts.add(puerhTea);
		}
	
	public List<Product> getAllProducts(){
		return listOfProducts;
	}

	public Product getProductById(String productId) {
		
		Product productById = null;
		
		for(Product product : listOfProducts){
			if(product != null && product.getProductId() != null && product.getProductId().equals(productId)){
				productById = product;
				break;
			}
		}
		
		if(productById == null){
			throw new ProductNotFoundException(productId);
		}
		
		return productById;
	}
	
	public List<Product> getProductsByCategory(String category){
		
		List<Product> productsByCategory = new ArrayList<Product>();
		
		for(Product product : listOfProducts) {
			if(category.equalsIgnoreCase(product.getCategory())){
				productsByCategory.add(product);
			}
		}
		
		return productsByCategory;	
	}

	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		
		Set<Product> productsByBrand = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();
		
		Set<String> criterias = filterParams.keySet();
		
		if(criterias.contains("brand")){
			for(String brandName : filterParams.get("brand")){
				for(Product product : listOfProducts){
					if(brandName.equalsIgnoreCase(product.getManufacturer())){
						productsByBrand.add(product);
					}
				}
			}
		}
		
		if(criterias.contains("category")){
			for(String category : filterParams.get("category")){
				productsByCategory.addAll(this.getProductsByCategory(category));
			}
		}
		
		productsByCategory.retainAll(productsByBrand);
		
		return productsByCategory;
	}

	public List<Product> getProductsByManufacturer(String manufacturer) {
		List<Product> productsByManufacturer = new ArrayList<Product>();
		
		for (Product product : productsByManufacturer){
			if (manufacturer.equalsIgnoreCase(product.getManufacturer())){
				productsByManufacturer.add(product);
			}
		}
		
		return productsByManufacturer;
	}

	public void addProduct(Product product) {
		listOfProducts.add(product);
	}
}
