package com.example.OrderProject;

import java.util.ArrayList;


import java.util.List;

import org.hibernate.criterion.Order;
import org.slf4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.OrderProject.domain.Category;
import com.example.OrderProject.domain.CategoryRepository;
import com.example.OrderProject.domain.Customer;
import com.example.OrderProject.domain.CustomerOrder;
import com.example.OrderProject.domain.CustomerOrderRepository;
import com.example.OrderProject.domain.CustomerRepository;
import com.example.OrderProject.domain.Item;
import com.example.OrderProject.domain.ItemPrice;
import com.example.OrderProject.domain.ItemPriceRepository;
import com.example.OrderProject.domain.ItemRepository;
import com.example.OrderProject.domain.Login;
import com.example.OrderProject.domain.LoginRepository;

@SpringBootApplication
public class OrderProjectApplication {
	
	private static final Logger log = LoggerFactory.getLogger(OrderProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OrderProjectApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner studentDemo(CategoryRepository crepository, ItemRepository repository, LoginRepository urepository, CustomerRepository cusreporistory, CustomerOrderRepository OrderRepository, ItemPriceRepository pricerepo) {
		return (args) -> {
			
			Login user1 = new Login("user","$2a$06$iWJkiUp6tTCfxItYgjfp3e5z2SgYd4GKky0JENTlJq97gCVkAkV0C", "USER", "USER");
			Login user2 = new Login("admin","$2a$10$8rP7/WkSlx8GgardAjs6VO9bRixzgi1NW5Ud6/lkfKetBYWUrbCpS", "ADMIN", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			
			crepository.save(new Category("Science"));
			crepository.save(new Category("Classics"));
			crepository.save(new Category("Entertainment")); 
			
			pricerepo.save(new ItemPrice("50"));
			
			
			repository.save(new Item("123123", "Lapio", "123123213", 50.50, crepository.findByName("Science").get(0), pricerepo.findByPrice("50").get(0)));
			repository.save(new Item("234234", "Kengät", "123123423", 100.50 , crepository.findByName("Science").get(0), pricerepo.findByPrice("50").get(0)));
			repository.save(new Item("345345", "Hattu","423423", 25.50 , crepository.findByName("Entertainment").get(0), pricerepo.findByPrice("50").get(0)));
			repository.save(new Item("456456", "Tuoli","543534", 5.50, crepository.findByName("Classics").get(0), pricerepo.findByPrice("50").get(0)));
			repository.save(new Item("567567", "Saappaat","6767867768", 500, crepository.findByName("Classics").get(0), pricerepo.findByPrice("50").get(0)));
			repository.save(new Item("678678", "Hanskat","876876687", 50, crepository.findByName("Classics").get(0), pricerepo.findByPrice("50").get(0)));
			
			ArrayList<String> arraylist = new ArrayList<String>();
			arraylist.add("lapio");
			arraylist.add("tuoli");
			arraylist.add("hattu");
			arraylist.add("lapio");
			
			
			cusreporistory.save(new Customer("Jaakko perala", "osoite", "puh", "email", "as.nro"));
			
	CustomerOrder order = new CustomerOrder("123", arraylist, cusreporistory.findByCustomerName("osoite").get(0));
			
			OrderRepository.save(order);

		
		};
	}
}
