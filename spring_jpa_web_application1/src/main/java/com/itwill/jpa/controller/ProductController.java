package com.itwill.jpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.entity.Product;
import com.itwill.jpa.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import oracle.jdbc.proxy.annotation.GetCreator;

/*
제품전체리스트	:	GET  	/product         
제품상세       :	GET  	/product/{productId}
제품생성		:	POST 	/product
제품삭제		:	DELETE  /product/{productId}
제품수정		:	PUT 	/product
*/
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Operation(description = "제품리스트")
	@GetMapping
	public ResponseEntity<List<Product>> getProductList(){
		List<Product> products=productService.products();
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}
	@Operation(description = "제품번호로보기")
	@GetMapping("{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId){
		Product product=productService.getProduct(productId);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	@Operation(description = "제품생성")
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		Product savedProduct=productService.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
	}
	@Operation(description = "제품수정")
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws Exception{
		Product updatedProduct=productService.updateProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedProduct);
	}
	@Operation(description = "제품삭제")
	@DeleteMapping("{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable(name="productId") Long productId) throws Exception{
		Product deletedProduct =productService.deleteProduct(productId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(deletedProduct);
	}
	
	
}



