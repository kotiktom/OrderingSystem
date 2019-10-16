package com.example.OrderProject.domain;

import java.util.List;

public interface ItemServiceWrapper {

	  List<Item> findAll();
	  
	  List<Customer> findAll1();
	  
	  List<CustomerOrder> findAll2();

	List<Item> findById(Long id);

	} 

