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





}
