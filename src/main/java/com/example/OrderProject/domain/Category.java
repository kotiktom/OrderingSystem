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
@TableGenerator(name="IDGenerator", initialValue=400)

public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="IDGenerator")
	private long categoryid;
	
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Item> items;

	public Category() {}
	
	public Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> booksList) {
		this.items = booksList;
	}



}