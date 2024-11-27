package com.itwill.jpa.service;
import java.util.List;

import com.itwill.jpa.entity.Product;

public interface ProductService {
	Product getProduct(Long number);
	Product saveProduct(Product product);
	Product updateProduct(Product product) throws Exception;
	Product deleteProduct(Long number) throws Exception;
	List<Product> products();
}