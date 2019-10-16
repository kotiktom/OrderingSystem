package com.example.OrderProject.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(name="IDGenerator", initialValue=500)

public class ItemPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="IDGenerator")
	private long priceid;
	
	private String price;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "itemprice")
	private List<Item> items;

	public ItemPrice() {}
	
	public ItemPrice(String price) {
		this.price = price;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public long getPriceid() {
		return priceid;
	}

	public void setpriceid(long priceid) {
		this.priceid = priceid;
	}

	public List<Item> getBooksList() {
		return items;
	}

	public void setBooksList(List<Item> booksList) {
		this.items = booksList;
	}



}
