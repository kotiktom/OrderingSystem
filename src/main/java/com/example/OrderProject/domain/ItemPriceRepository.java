package com.example.OrderProject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ItemPriceRepository extends CrudRepository <ItemPrice, Long> {

	List<ItemPrice> findByPrice(String string);
	
	

}
