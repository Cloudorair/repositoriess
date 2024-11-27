package com.itwill.jpa.relation.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
public class Category{
	@Id
	@SequenceGenerator(name = "category_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "category_seq")
	private Long id;
	@Column(unique = true,nullable = false)
	private String code;
	private String name;
	
	/***CATEGORY:PRODUCT*****
	  1(CATEGORY):N(PRODUCT)
	  OWNER TABLE(x)
	*/
	@Builder.Default
	@OneToMany(	mappedBy = "category",
				cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
	private List<Product> products=new ArrayList<Product>();
	
}
