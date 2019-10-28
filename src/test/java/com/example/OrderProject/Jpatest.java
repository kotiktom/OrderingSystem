package com.example.OrderProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.OrderProject.domain.Category;
import com.example.OrderProject.domain.Item;
import com.example.OrderProject.domain.ItemRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class Jpatest {

    @Autowired
    private ItemRepository repository;
    
  
    @Test
    public void findItem() {
    	/* Simply search for a book which is created on application startup */
        List<Item> items = repository.findByName("Tuoli");
        
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getName()).isEqualTo("tuoli");
    }
   
@Test
public void createNewItem() {
	/* Create a new book and check that it is found within the repository */
	Item item = new Item("Mickey", "Mouse", "mm@mouse.com", 123456, new Category("testcategory"));
	repository.save(item);
	assertThat(item.getName()).isNotNull();
}  

@Test
public void deleteItem() {
	/* Fetch by author and make sure there is a book with Johnson as an author */
	List<Item> booksOne = repository.findByName("Tuoli");
	assertThat(booksOne).hasSize(1);
	
	/* Remove the book which has Johnson as author and check the repository again for the book. */
	repository.deleteByName("Tuoli");
    List<Item> booksTwo = repository.findByName("Tuoli");
    assertThat(booksTwo).hasSize(0);
  }  
}