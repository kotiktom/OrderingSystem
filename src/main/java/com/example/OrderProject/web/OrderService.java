package com.example.OrderProject.web;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderProject.domain.Customer;
import com.example.OrderProject.domain.CustomerOrder;
import com.example.OrderProject.domain.CustomerOrderRepository;
import com.example.OrderProject.domain.CustomerRepository;
import com.example.OrderProject.domain.Item;
import com.example.OrderProject.domain.ItemRepository;
import com.example.OrderProject.domain.ItemServiceWrapper;

@Service
public class OrderService implements ItemServiceWrapper {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private CustomerRepository crepository;
    
    @Autowired
    private CustomerOrderRepository orderRepository;
    
   
    public List<Item> findAll() {

        return (List<Item>) repository.findAll();
    }

	
	public List<Customer> findAll1() {
		return (List<Customer>) crepository.findAll();
	}


	public List<CustomerOrder> findAll2() {
		return (List<CustomerOrder>) orderRepository.findAll();
	}


	@Override
	public List<Item> findById(Long id) {
		
		return (List<Item>) ItemRepository.findById();
	}


}
