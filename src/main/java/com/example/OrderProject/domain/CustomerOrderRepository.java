package com.example.OrderProject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long>{
	List<CustomerOrder> findAll();

	
}
