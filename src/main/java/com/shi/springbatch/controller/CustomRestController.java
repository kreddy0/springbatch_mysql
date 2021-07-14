package com.shi.springbatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shi.springbatch.mapper.CustomMapper;
import com.shi.springbatch.model.Address;
import com.shi.springbatch.model.Customer;

@RestController
@RequestMapping("/api")
public class CustomRestController {
	
	@Autowired
	CustomMapper cmap;
	
	@PostMapping("/addresses")
	public Address createAddress( @RequestBody Address address){
		 cmap.addAddress(address);
		 return address;
	}
	
	@PostMapping("/customers")
	public Customer createCustomer(@RequestBody Customer customer ){
	    cmap.addCustomer(customer);
	    return customer;
		
	}
}
