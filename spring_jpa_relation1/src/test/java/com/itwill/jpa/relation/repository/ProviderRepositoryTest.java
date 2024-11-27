package com.itwill.jpa.relation.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.jpa.relation.SpringJpaRelationApplicationTests;
import com.itwill.jpa.relation.entity.Product;
import com.itwill.jpa.relation.entity.Provider;

import jakarta.transaction.Transactional;

class ProviderRepositoryTest extends SpringJpaRelationApplicationTests {
	@Autowired
	ProviderRepository providerRepository;

	@DisplayName("2-1.공급자+제품들저장")
	@Test
	@Transactional
	@Rollback(false)
	void providerWithProductsSave1() {
		Provider provider =Provider.builder().name("한빛미디어").build();
		Product product1 = Product.builder().name("자바바").price(3000).stock(50).build();
		Product product2 = Product.builder().name("자바라").price(1000).stock(23).build();
		Product product3 = Product.builder().name("자바는").price(2400).stock(11).build();
		/*
		 * 연관설정 
		 * 	Provider --> Product
		 * 	Product  --> Provider
		 * OWNER TABLE(X)
		 */
		provider.getProducts().add(product1);
		provider.getProducts().add(product2);
		provider.getProducts().add(product3);
		product1.setProvider(provider);
		product2.setProvider(provider);
		product3.setProvider(provider);
		
		providerRepository.save(provider);
		
	}
	@DisplayName("2-2.공급자+제품들저장")
	@Test
	@Transactional
	@Rollback(false)
	void providerWithProductsSave2() {
		Provider provider =providerRepository.findById(1L).get();
		Product product1 = Product.builder().name("삼송책1").price(3000).stock(50).build();
		Product product2 = Product.builder().name("삼송책2").price(1000).stock(23).build();
		Product product3 = Product.builder().name("삼송책3").price(2400).stock(11).build();
		/*
		 * 연관설정 
		 * 	Provider --> Product
		 * 	Product  --> Provider
		 * OWNER TABLE(X)
		 */
		provider.getProducts().add(product1);
		provider.getProducts().add(product2);
		provider.getProducts().add(product3);
		product1.setProvider(provider);
		product2.setProvider(provider);
		product3.setProvider(provider);
		
		providerRepository.save(provider);
		
	}
	@DisplayName("3.공급자+제품들읽기")
	@Test
	@Transactional
	@Rollback(false)
	void providerWithProductsRead() {
		Provider provider=providerRepository.findById(1L).get();
		System.out.println(">>> provider: "+provider);
		System.out.println(">>> provider.getProducts(): "+provider.getProducts());
	}
	
	@DisplayName("4.공급자+제품들삭제")
	@Test
	@Transactional
	@Rollback(false)
	void providerWithProductsDelete() {
		System.out.println("----부모엔티티삭제시자식엔티티도같이삭제[CascadeType.REMOVE]------");
		Provider provider =providerRepository.findById(1L).get();
		System.out.println(provider);
		System.out.println(provider.getProducts());
		providerRepository.delete(provider);
	}
	@DisplayName("5.공급자삭제 제품FK null")
	@Test
	@Transactional
	@Rollback(false)
	void providerDelete() {
		System.out.println("----부모엔티티삭제시자식엔티티도같이안삭제[CascadeType.REMOVE]------");
		Provider provider =providerRepository.findById(1L).get();
		for (Product p : provider.getProducts()) {
			p.setProvider(null);
		}
		providerRepository.delete(provider);
	}
}




