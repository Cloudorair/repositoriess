package com.itwill.jpa.relation.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Provider {
	@Id
	@SequenceGenerator(name = "provider_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "provider_seq")
	private Long id;
	private String name;
	/***CATEGORY:PRODUCT*****
	  1(PROVIDER):N(PRODUCT)
	  OWNER TABLE(x)
	*/
	@OneToMany(mappedBy = "provider",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@Builder.Default
	private List<Product> products=new ArrayList<>();
}
