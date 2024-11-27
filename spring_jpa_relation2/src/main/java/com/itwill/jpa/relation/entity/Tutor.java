package com.itwill.jpa.relation.entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tutor{
	@Id
	@SequenceGenerator(name = "TUTOR_TUTOR_ID_SEQ",sequenceName = "TUTOR_TUTOR_ID_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "TUTOR_TUTOR_ID_SEQ")
	private Long tutorId;
	private String name;
	private String email;
	private String phone;
	private LocalDateTime dob;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="addr_id")
	private Address address;
	/*
	<< fetchType >>
	  - 특별한이유가없다면 지연 로딩(LAZY)을사용합니다.
	@ManyToOne, @OneToOne 어노테이션들은 기본이 즉시 로딩(EAGER) 이다.
	@OneToMany @ManyToMany 기본이 지연 로딩(LAZY)이다.
	@OneToOne 에서는 OWNER테이블이아닌경우 지연로딩설정이안됨
	*/
	@OneToMany(mappedBy = "tutor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@Builder.Default
	private List<Course> courses=new ArrayList<>();
	
	
	
}
