package com.itwill.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.entity.Product;
import com.itwill.jpa.repository.ProductRepository;

import jakarta.transaction.Transactional;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	@Override
	public Product getProduct(Long productId) {
		return productRepository.findByProductId(productId);
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) throws Exception {
		if(productRepository.findById(product.getProductId()).isPresent()) {
			return productRepository.save(product);
		}
		return new Product();
	}

	@Override
	@Transactional
	public Product deleteProduct(Long productId) throws Exception {
		Product deleteProduct=productRepository.findByProductId(productId); 
		productRepository.deleteByProductId(productId);
		return deleteProduct;
	}

	@Override
	public List<Product> products() {
		return productRepository.findAll();
	}
	
}
