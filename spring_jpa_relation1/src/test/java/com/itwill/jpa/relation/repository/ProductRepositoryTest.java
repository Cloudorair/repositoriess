package com.itwill.jpa.relation.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.SystemPropertyUtils;

import com.itwill.jpa.relation.SpringJpaRelationApplicationTests;
import com.itwill.jpa.relation.entity.Category;
import com.itwill.jpa.relation.entity.Product;
import com.itwill.jpa.relation.entity.ProductDetail;

import jakarta.transaction.Transactional;

class ProductRepositoryTest extends SpringJpaRelationApplicationTests {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	@Disabled
	@DisplayName("1.제품+제품상세 저장")
	@Test
	@Transactional
	@Rollback(false)
	void productWithProductDetailSave() {
		Product product = Product.builder().name("JPA").price(2000).stock(50).build();
		ProductDetail productDetail = ProductDetail.builder().description("JPA아주좋아요!!").build();
		/*
		 * 연관관계설정(OWNER테이블아닌경우)
		 * Product에 ProductDetail set
		 * ProductDetail에 Product set
		 */
		product.setProductDetail(productDetail);
		productDetail.setProduct(product);

		productRepository.save(product);

	}
	//@Disabled
	@DisplayName("2.제품+제품상세 읽기")
	@Test
	@Transactional
	@Rollback(false)
	void productWithProductDetailRead() {
		/*
		 * 연관관계설정(OWNER테이블아닌경우)
		 * Product-->ProductDetail
		 */
		System.out.println("--------------productWithProductDetailRead-------------------");
		Product product = productRepository.findById(1L).get();
		System.out.println(">>>" + product);
		System.out.println(">>>" + product.getProductDetail());

	}

	
	@Disabled
	@DisplayName("3-1.제품+카테고리 저장1")
	@Test
	@Transactional
	@Rollback(false)
	void productWithCategorySave1() {
		Category category1 = Category.builder().code("IT").name("프로그래밍").build();
		Product product1 = Product.builder().name("SPRING").price(3000).stock(50).build();
		Product product2 = Product.builder().name("JAVA").price(1000).stock(23).build();
		Product product3 = Product.builder().name("HTML").price(2400).stock(11).build();
		/*
		 연관관계설정
		 	Product-->Category[OWNER테이블인경우]
		 */
		product1.setCategory(category1);
		product2.setCategory(category1);
		product3.setCategory(category1);
		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
		

	}

	/*******************************************************/
	@Disabled
	@DisplayName("3-2.제품+카테고리 저장2")
	@Test
	@Transactional
	@Rollback(false)
	void productWithCategorySave2() {
	
			Category category1 =categoryRepository.findById(1L).get();
			
			Product product1 = Product.builder().name("소설책1").price(1000).stock(10).build();
			Product product2 = Product.builder().name("소설책2").price(2000).stock(20).build();
			Product product3 = Product.builder().name("소설책3").price(3000).stock(30).build();
			product1.setCategory(category1);
			product2.setCategory(category1);
			product3.setCategory(category1);
			productRepository.save(product1);
			productRepository.save(product2);
			productRepository.save(product3);
		

	}
	/*******************************************************/
	
	/**************** product :category ********************/
	@Test
	@DisplayName("5.제품+카테고리읽기")
	@Transactional
	@Rollback(false)
	void productWithCategoryRead() {
		System.out.println("-------------5.제품+카테고리읽기[FetchType=LAZY]----------------");
		Product product1=productRepository.findById(1L).get();
		System.out.println(">>>>>>>>>>>"+product1);
		System.out.println(">>>>>>>>>>>"+product1.getCategory());
	}
	

}