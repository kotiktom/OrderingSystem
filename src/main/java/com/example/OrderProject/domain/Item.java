package com.example.OrderProject.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import com.example.OrderProject.domain.Category;
import com.example.OrderProject.domain.ItemPrice;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@TableGenerator(name="IDGenerator", initialValue=100)
public class Item {
	
	@GeneratedValue(strategy = GenerationType.TABLE, generator="IDGenerator")
	@Id long id;
	
	public String itemNumber;
	public String name;
	public String ean;
	public double price;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "categoryid")
	private Category category;
	
	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public void setItemprice(Category category) {
		this.category = category;
	}

	public Item(String itemNumber, String name, String ean, double price, Category category) {
		
		this.itemNumber = itemNumber;
		this.name = name;
		this.ean = ean;
		this.price = price;
		this.category = category;
	}

	public Item() {
		
	}

	
	
}