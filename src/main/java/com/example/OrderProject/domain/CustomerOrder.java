package com.example.OrderProject.domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@TableGenerator(name="IDGenerator", initialValue=300)
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="IDGenerator")
	public long OrderId;
	
	private String orderNumber;

	private ArrayList<String> orderItems;
	private ArrayList<Integer> itemAmount;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "customerid")
	private Customer customer;
	
	

	public ArrayList<Integer> getItemAmount() {
		return itemAmount;
	}
	public void setItemAmount(ArrayList<Integer> itemAmount) {
		this.itemAmount = itemAmount;
	}
	public long getOrderId() {
		return OrderId;
	}
	public void setOrderId(long orderId) {
		OrderId = orderId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public ArrayList getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(ArrayList<String> orderItems) {
		this.orderItems = orderItems;
	}
	public CustomerOrder(String orderNumber, ArrayList<String> orderItems ,ArrayList<Integer> itemAmount , Customer customer) {
		
		this.orderNumber = orderNumber;
		this.orderItems = orderItems;
		this.itemAmount = itemAmount;
		this.customer = customer;
	}
	public CustomerOrder() {
		
	}

}
