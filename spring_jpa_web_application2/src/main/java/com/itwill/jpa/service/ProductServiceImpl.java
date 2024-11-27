package com.itwill.jpa.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.ProductDto;
import com.itwill.jpa.entity.Product;
import com.itwill.jpa.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	@Override
	public ProductDto getProduct(Long productId) {
		return ProductDto.toDto(productRepository.findById(productId).get());
	}
	@Override
	public ProductDto saveProduct(ProductDto productDto) {
		return ProductDto.toDto(productRepository.save(Product.toEntity(productDto)));
	}
	@Override
	public ProductDto updateProduct(ProductDto productDto) throws Exception {
		return ProductDto.toDto(productRepository.save(Product.toEntity(productDto)));
	}
	@Override
	public void deleteProduct(Long productId) throws Exception {
		productRepository.deleteById(productId);
	}
	@Override
	public List<ProductDto> products() {
		List<Product> productEntityList = productRepository.findAll();
		List<ProductDto> productDtoList=new ArrayList<>();
		for (Product productEntity : productEntityList) {
			productDtoList.add(ProductDto.toDto(productEntity));
		}
		return productDtoList;
	}
}









