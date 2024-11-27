package com.itwill.jpa.relation.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity
public class Product {
	@Id
	@SequenceGenerator(name = "product_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_seq")
	private Long id;
	private String name;
	private Integer price;
	private Integer stock;
	
	/***PRODUCT:CATEGORY*****
	  N(PRODUCT):1(CATEGORY)
	  OWNER TABLE(o)
	*/
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Category category;
	
	/***PRODUCT:PROVIDER*****
	  N(PRODUCT):1(PROVIDER)
	  OWNER TABLE(o)
	*/
	@ToString.Exclude
	@ManyToOne
	private Provider provider;
	
	
	/***PRODUCT:PRODUCT_DETAIL*****
	  1(PRODUCT):1(PRODUCT_DETAIL)
	*/
	@OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private ProductDetail productDetail;
}