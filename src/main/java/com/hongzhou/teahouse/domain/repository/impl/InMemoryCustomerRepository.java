package com.hongzhou.teahouse.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hongzhou.teahouse.domain.Customer;
import com.hongzhou.teahouse.domain.repository.CustomerRepository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository{

	private List<Customer> listOfCustomers = new ArrayList<Customer>();
	
	private Customer customer1;
	private Customer customer2;
	private Customer customer3;
	
	public InMemoryCustomerRepository(){
		
		
		customer1 = new Customer("1", "Hong", "1117 7th Street, MN 55414", 50);
		customer2 = new Customer("2", "Yanli", "8th Street, IL 63530", 150);
		customer3 = new Customer("3", "Grace", "9th Ave, TX 77054", 200);
		
		listOfCustomers.add(customer1);
		listOfCustomers.add(customer2);
		listOfCustomers.add(customer3);
	}
	
	
	public List<Customer> getAllCustomers() {
		
		return listOfCustomers;
	}
	
	public Customer getCustomerById(String customerId){
		Customer customerById = null;
		
		for(Customer customer : listOfCustomers){
			if(customer != null && customer.getCustomerId() != null && customer.getCustomerId().equals(customerId)){
				customerById = customer;
				break;
			}
		}
		
		if(customerById == null){
			throw new IllegalArgumentException("No customers found with the customer id: " + customerId);
		}
		
		return customerById;
		
	}

}
