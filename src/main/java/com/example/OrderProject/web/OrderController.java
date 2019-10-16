package com.example.OrderProject.web;

import java.io.ByteArrayInputStream;


import java.io.IOException;
import java.util.List;
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
	        return "login";
	    }
	
	 // ↓↓ API SECTION ↓↓
	 
	 @RequestMapping(value="/apiorders", method= RequestMethod.GET)
	 public @ResponseBody List<CustomerOrder> OrderListRest() {
		return (List<CustomerOrder>) orderRepository.findAll();
		
	 }
	 
	 @RequestMapping(value="/items", method= RequestMethod.GET)
	 public @ResponseBody List<Item> bookListRest() {
		return (List<Item>) repository.findAll();
		
	 }
	 
	 @RequestMapping(value="/items/{id}", method= RequestMethod.GET)
	 public @ResponseBody Optional<Item> findBookListRest(@PathVariable("id") Long id) {
		return repository.findById(id);
		
	 }
	 // ↑↑ API SECTION ↑↑
	 
	 
	 
	 //↓↓ REDIRECT SECTION // PAGES SECTION ↓↓
	 
	 
	@RequestMapping(value = "/itemlist", method = RequestMethod.GET)
	public String BookList(Model model) {
		model.addAttribute("items", repository.findAll());
		return "itemlist";
		}

	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String Home(Model model) {
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
		model.addAttribute("prices", crepository.findAll());
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
        return "redirect:../orders";
    	}
	
	@RequestMapping(value = "/deleteitem/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteItem(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
        return "redirect:../itemlist";
    	}
	
	@RequestMapping(value = "/deletecustomer/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deletecustomer(@PathVariable("id") Long id, Model model) {
		cusrepo.deleteById(id);
        return "redirect:../orders";
    	}
   
   
    @RequestMapping(value = "/edit/{id}")
    public String editItem(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("item", repository.findById(id));
    	model.addAttribute("categories", crepository.findAll());
    	System.out.println("jesus" + 2);
    	return "edititem";
    }
    
    @RequestMapping(value = "/editcustomer/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("customer", cusrepo.findById(id));
    	return "editcustomer";
    }
    
    @RequestMapping(value = "/editorder/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("order", orderRepository.findById(id));
		model.addAttribute("customer", cusrepo.findAll());
    	model.addAttribute("items", repository.findAll());
    	return "editorder";
    }
    // ↑↑ SAVING AND DELETING ITEMS/ORDERS/CUSTOMERS ↑↑
}