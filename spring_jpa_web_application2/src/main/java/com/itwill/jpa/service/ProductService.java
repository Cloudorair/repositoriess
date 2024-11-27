package com.itwill.jpa.service;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dto.ProductDto;
import com.itwill.jpa.entity.Product;
@Transactional
public interface ProductService {
	@Transactional
	ProductDto getProduct(Long number);
	ProductDto saveProduct(ProductDto product);
	ProductDto updateProduct(ProductDto product) throws Exception;
	void deleteProduct(Long number) throws Exception;
	List<ProductDto> products();
}