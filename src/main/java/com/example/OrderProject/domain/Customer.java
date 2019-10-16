package com.example.OrderProject.domain;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@TableGenerator(name="IDGenerator", initialValue=200)
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="IDGenerator")
	public long customerid;
	
	public String customerName;
	public String customerAddress;
	public String customerNumber;
	public String customerPhone;
	public String customerEmail;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<CustomerOrder> orders;
	
	
	
	public long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}
	public List<CustomerOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<CustomerOrder> orders) {
		this.orders = orders;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Customer(String customerNumber, String customerName, String customerAddress, String customerEmail,
			String customerPhone) {

		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
	}
	public Customer() {
		
	}
	@Override
	public String toString() {
		return "Customer [customerNumber=" + customerNumber + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", customerEmail=" + customerEmail + ", customerPhone=" + customerPhone + "]";
	}
	
	
}
