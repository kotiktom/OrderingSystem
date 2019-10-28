package com.example.OrderProject.web;

import java.io.ByteArrayInputStream;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.OrderProject.domain.Category;
import com.example.OrderProject.domain.CategoryRepository;
import com.example.OrderProject.domain.Customer;
import com.example.OrderProject.domain.CustomerOrder;
import com.example.OrderProject.domain.CustomerOrderRepository;
import com.example.OrderProject.domain.CustomerRepository;
import com.example.OrderProject.domain.Item;

import com.example.OrderProject.domain.ItemRepository;
import com.example.OrderProject.domain.ItemServiceWrapper;
import com.example.OrderProject.util.GeneratePdfReport;


@Controller

public class OrderController {
	
	
	@Autowired
	private ItemRepository repository;
	
	@Autowired
	private CategoryRepository crepository; 
	
	 
	 @Autowired
	 ItemRepository itemRepository;
	 
	 @Autowired
	  CustomerOrderRepository orderRepository;
	 
	 @Autowired
	 CustomerRepository cusrepo;

	    @RequestMapping(value = "/pdfreport/{id}", method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_PDF_VALUE)
	    public ResponseEntity<InputStreamResource> citiesReport(@PathVariable("id") Long id, Model model) {
	    	    	
	    	{

	        Long testid = id;
	        
	        ByteArrayInputStream bis = GeneratePdfReport.itemsReport(testid, itemRepository, orderRepository );

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=orderInvoice.pdf");

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
	    }	}
	
	 // ↓↓ LOGIN SECTION ↓↓
	    
	 @RequestMapping(value="/login")
	    public String login() {	
	        return "/login";
	    }
	
	 // ↓↓ API SECTION ↓↓
	 
	 @RequestMapping(value="/api/orders", method= RequestMethod.GET)
	 public @ResponseBody List<CustomerOrder> OrderListRest() {
		return (List<CustomerOrder>) orderRepository.findAll();	
	 }
	 
	 @RequestMapping(value="/api/customers", method= RequestMethod.GET)
	 public @ResponseBody List<Customer> CustomerListRest() {
		return (List<Customer>) cusrepo.findAll();	
	 }
	 
	 @RequestMapping(value="/api/items", method= RequestMethod.GET)
	 public @ResponseBody List<Item> ItemListRest() {
		return (List<Item>) repository.findAll();		
	 }
	 
	 @RequestMapping(value="/api/items/{id}", method= RequestMethod.GET)
	 public @ResponseBody Optional<Item> ItemByIdRest(@PathVariable("id") Long id) {
		return repository.findById(id);
		}
	 
	 @RequestMapping(value="/api/customers/{id}", method= RequestMethod.GET)
	 public @ResponseBody Optional<Customer> CustomerByIdRest(@PathVariable("id") Long id) {
		return cusrepo.findById(id);
		}
	 
	 @RequestMapping(value="/api/orders/{id}", method= RequestMethod.GET)
	 public @ResponseBody Optional<CustomerOrder> OrderByIdRest(@PathVariable("id") Long id) {
		return orderRepository.findById(id);
		}
	 // ↑↑ API SECTION ↑↑
	 
	 
	 
	 //↓↓ REDIRECT SECTION // PAGES SECTION ↓↓
	 
	 
	@RequestMapping(value = "/itemlist", method = RequestMethod.GET)
	public String Itemlist(Model model) {
		model.addAttribute("items", repository.findAll());
		return "/itemlist";
		}
	
	@RequestMapping(value = "/apiinfo", method = RequestMethod.GET)
	public String Apiinfo(Model model) {
		model.addAttribute("items", repository.findAll());
		return "/apiinfo";
		}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String Home2(Model model) {
		model.addAttribute("orders", orderRepository.findAll());	
		return "home";
		}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Home1(Model model) {
		model.addAttribute("orders", orderRepository.findAll());	
		return "home";
		}
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String orders(Model model) {
		model.addAttribute("orders", orderRepository.findAll());		
		model.addAttribute("customers", cusrepo.findAll());	
		return "orders";
		}
	//↑↑ REDIRECT SECTION // PAGES SECTION ↑↑
	
	
	// ↓↓ PAGES FOR ADDING NEW CUSTOMERS/ITEMS/ORDERS ↓↓
	
	@RequestMapping(value = "/addcategory")
	 @PreAuthorize("hasAuthority('ADMIN')")
	public String AddCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
		}

	@RequestMapping(value = "/addcustomer")
	 @PreAuthorize("hasAuthority('ADMIN')")
	public String AddCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "addcustomer";
		}

	
	@RequestMapping(value = "/additem")
	 @PreAuthorize("hasAuthority('ADMIN')")
	public String AddItem(Model model) {
		model.addAttribute("item", new Item());
		model.addAttribute("categories", crepository.findAll());
		return "additem";
		}
	
	@RequestMapping(value = "/neworder")
	 @PreAuthorize("hasAuthority('ADMIN')")
	public String NewOrder(Model model) {
		model.addAttribute("order", new CustomerOrder());
		model.addAttribute("customer", cusrepo.findAll());
		model.addAttribute("items", repository.findAll());
		return "neworder";
		}
	// ↑↑ PAGES FOR ADDING NEW CUSTOMERS/ITEMS/ORDERS ↑↑
	
	
	// ↓↓  SAVING AND DELETING ITEMS/ORDERS/CUSTOMERS ↓↓
	
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String save(Category category) {
		crepository.save(category);
		return "redirect:itemlist";
		}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Item item) {
		repository.save(item);
		return "redirect:itemlist";
		}
	
	@RequestMapping(value = "/savecustomer", method = RequestMethod.POST)
	public String saveCustomer(Customer customer) {
		cusrepo.save(customer);
		return "redirect:orders";
		}
	
	@RequestMapping(value = "/saveorder", method = RequestMethod.POST)
	public String saveOrder(CustomerOrder customerOrder) {
	  ArrayList<Integer> RemoveNulls = customerOrder.getItemAmount();
	 RemoveNulls.removeIf(Objects::isNull);

	 customerOrder.setItemAmount(RemoveNulls);

	 orderRepository.save(customerOrder);
	 return "redirect:orders";
	 }

	
	
	@RequestMapping(value = "/saveitem", method = RequestMethod.POST)
	public String saveItem(Item item) {
		repository.save(item);
		return "redirect:itemlist";
		}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteOrder(@PathVariable("id") Long id, Model model) {
		orderRepository.deleteById(id);
        return "redirect:/orders";
    	}
	
	@RequestMapping(value = "/deleteitem/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteItem(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
        return "redirect:/itemlist";
    	}
	
	@RequestMapping(value = "/deletecustomer/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deletecustomer(@PathVariable("id") Long id, Model model) {
		cusrepo.deleteById(id);
        return "redirect:/orders";
    	}
   
   
    @RequestMapping(value = "/edit/{id}")
    public String editItem(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("item", repository.findById(id));
    	model.addAttribute("categories", crepository.findAll());
    	return "/edititem";
    }
    
    @RequestMapping(value = "/editcustomer/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("customer", cusrepo.findById(id));
    	return "/editcustomer";
    }
    
    @RequestMapping(value = "/editorder/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("order", orderRepository.findById(id));
		model.addAttribute("customer", cusrepo.findAll());
    	model.addAttribute("items", repository.findAll());
    	return "/editorder";
    }
    // ↑↑ SAVING AND DELETING ITEMS/ORDERS/CUSTOMERS ↑↑
}