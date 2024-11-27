package com.itwill.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.itwill.jpa.entity.Product;
import com.itwill.jpa.repository.ProductRepository;

@SpringBootApplication
public class SpringJpaWebApplication1 implements CommandLineRunner {
	@Autowired
	ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJpaWebApplication1.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product p=Product.builder().name("자바깡").price(1000).stock(10).build();
		productRepository.save(p);
		Thread.sleep(1000);
		p.setName("변경깡1");
		productRepository.save(p);
		Thread.sleep(1000);
		p.setName("변경깡2");
		productRepository.save(p);
		
		
	}
	

}
