package com.itwill.jpa.relation.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.jpa.relation.SpringJpaRelationApplicationTests;
import com.itwill.jpa.relation.entity.Category;
import com.itwill.jpa.relation.entity.Product;

import jakarta.transaction.Transactional;

class CategoryRepositoryTest extends SpringJpaRelationApplicationTests{
	@Autowired
	CategoryRepository categoryRepository;
	
	@DisplayName("1.카테고리+제품들저장")
	@Test
	@Transactional
	@Rollback(false)
	void categoryWithProductsSave() {
		/***************[CascadeType.PERSIST]**************/
		Category category1=Category.builder().code("IT").name("프로그래밍").build();
		Product product1 = Product.builder().name("SPRING").price(3000).stock(50).build();
		Product product2 = Product.builder().name("JAVA").price(1000).stock(23).build();
		Product product3 = Product.builder().name("HTML").price(2400).stock(11).build();
		/*
		 * 연관관계설정[OWNER 테이블이 아닌경우]
		 * Category-->Product
		 * Product-->Category
		 */
		category1.getProducts().add(product1);
		category1.getProducts().add(product2);
		category1.getProducts().add(product3);
		
		product1.setCategory(category1);
		product2.setCategory(category1);
		product3.setCategory(category1);
		categoryRepository.save(category1);
		
		Category category2=Category.builder().code("HOBBY").name("취미").build();
		categoryRepository.save(category2);
		
	}
	@DisplayName("2.카테고리+제품들 읽기")
	@Test
	@Transactional
	@Rollback(false)
	void categoryWithProductsRead() {
		Category category1 = categoryRepository.findById(1L).get();
		System.out.println(">>> "+category1);
		System.out.println(">>> "+category1.getProducts());
	}
	@DisplayName("3.카테고리+제품들  삭제")
	@Test
	@Transactional
	@Rollback(false)
	void categoryWithProductsDelete() {
		System.out.println("----부모엔티티삭제시자식엔티티도같이삭제[CascadeType.REMOVE]------");
		Category category1=categoryRepository.findById(1L).get();
		System.out.println(category1.getProducts());
		categoryRepository.delete(category1);
		
	}
	@DisplayName("4.카테고리만삭제 제품은  FK null")
	@Test
	@Transactional
	@Rollback(false)
	void categoryDelete() {
		System.out.println("----부모엔티티삭제시자식엔티티도같이안삭제[CascadeType.REMOVE]------");
		Category category1=categoryRepository.findById(1L).get();
		//자식 엔티티의 부모를 null로 설정하여 외래 키를 null로 만듦
		for (Product p: category1.getProducts()) {
			p.setCategory(null);
		}
		categoryRepository.delete(category1);
	
	}
	
}





