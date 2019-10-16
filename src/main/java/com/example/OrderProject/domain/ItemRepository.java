package com.example.OrderProject.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

	List<Item> findByName(String string);
	
	List<Item> findAll();

	static List<Item> findById() {
		return null;
		}
	
	@Query(
	value = "SELECT name FROM item u WHERE name = ?1", 
	nativeQuery = true)
	String FindByParamName(String name);
	
	@Query(
	value = "SELECT price FROM item u WHERE name = ?1", 
	nativeQuery = true)
	String FindByParamPrice(String name);
	
	@Query(
	value = "SELECT price FROM item u WHERE name = ?1", 
	nativeQuery = true)
	Double FindByParamPrice2(String name);
	
	@Query(
	value = "SELECT item_number FROM item u WHERE name = ?1", 
	nativeQuery = true)
	String FindByParamNumber(String name);

	
	

}
