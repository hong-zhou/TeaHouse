package com.hongzhou.teahouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhou.teahouse.domain.Customer;
import com.hongzhou.teahouse.domain.repository.CustomerRepository;
import com.hongzhou.teahouse.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomers() {
		
		return customerRepository.getAllCustomers();
	}

}
