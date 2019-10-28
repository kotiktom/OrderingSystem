package com.example.OrderProject;

import org.junit.Test;

import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.OrderProject.web.OrderController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderProjectApplicationTests {

	@Autowired
	private OrderController controller;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		
	}
	}
