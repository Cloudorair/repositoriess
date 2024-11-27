package com.itwill.jpa.relation.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductDetail {
	@Id
	@SequenceGenerator(name = "product_detail_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "product_detail_seq")
	private Long id;
	private String description;
	/***PRODUCT_DETAIL:PRODUCT*****
	 * 1(PRODUCT_DETAIL):1(PRODUCT)
	 * OWNER TABLE
	 */
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@ToString.Exclude
	@JoinColumn(nullable = false)
	private Product product;
	
	
}
