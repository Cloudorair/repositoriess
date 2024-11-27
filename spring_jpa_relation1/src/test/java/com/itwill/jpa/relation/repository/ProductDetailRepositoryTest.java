package com.itwill.jpa.relation.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.jpa.relation.SpringJpaRelationApplicationTests;
import com.itwill.jpa.relation.entity.Product;
import com.itwill.jpa.relation.entity.ProductDetail;

import jakarta.transaction.Transactional;

class ProductDetailRepositoryTest extends SpringJpaRelationApplicationTests{
	
	@Autowired
	ProductDetailRepository productDetailRepository;
	@Test
	@Transactional
	@Rollback(false)
	void productDetailWithProductSave() {
		Product product=Product.builder().name("JDBC").price(2000).stock(50).build();
		ProductDetail productDetail=ProductDetail.builder().description("JDBC아주좋은책이여요").build();
		/*
		 연관관계설정(OWNER)
		 ProductDetail에 Product set
		*/
		productDetail.setProduct(product);
		productDetailRepository.save(productDetail);
	
	}
	@Test
	@Transactional
	@Rollback(false)
	void productDetailWithProductRead() {
		ProductDetail findProductDetail= productDetailRepository.findById(1L).get();
		System.out.println(">> findProductDetail:"+findProductDetail);
		Product findProduct=findProductDetail.getProduct();
		System.out.println(">> findProduct:"+findProduct);
	}
	
}
