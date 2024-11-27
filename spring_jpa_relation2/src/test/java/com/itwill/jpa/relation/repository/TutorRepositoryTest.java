package com.itwill.jpa.relation.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.SpringJpaApplicationTests;
import com.itwill.jpa.relation.entity.Address;
import com.itwill.jpa.relation.entity.Course;
import com.itwill.jpa.relation.entity.Tutor;

class TutorRepositoryTest extends SpringJpaApplicationTests{
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	TutorRepository tutorRepository;
	
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void tutorWithAddressSave() {
		
		Tutor tutor1=Tutor.builder()
					.name("김강사1")
					.email("kim1@gmail.com")
					.phone("111-1111")
					.dob(LocalDateTime.now().minusWeeks(1))
					.build();
		Tutor tutor2=Tutor.builder()
				.name("김강사2")
				.email("kim2@gmail.com")
				.phone("222-2222")
				.dob(LocalDateTime.now())
				.build();
		
		Address address=Address.builder()
				.street("서울호텔")
				.city("서울")
				.state("서울시")
				.zip("77777")
				.country("대한민국")
				.build();
		
		
		
		
		
	}
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void selectTutorWithAddress() {
	
	}
	
	@Test
	@Transactional
	@Rollback(false)
	void selectTutor() {
		/*
		<< fetchType >>
		  - 특별한이유가없다면 지연 로딩(LAZY)을사용합니다.
		@ManyToOne, @OneToOne 어노테이션들은 기본이 즉시 로딩(EAGER) 이다.
		@OneToMany @ManyToMany 기본이 지연 로딩(LAZY)이다.
		@OneToOne 에서는 OWNER테이블이아닌경우 지연로딩설정이안됨
		*/
		Tutor tutor1=tutorRepository.findById(1L).get();
		
		
	}
	@Test
	@Transactional
	@Rollback(false)
	void selectTutorWithCourses() {
		/*
		<< fetchType >>
		  - 특별한이유가없다면 지연 로딩(LAZY)을사용합니다.
		@ManyToOne, @OneToOne 어노테이션들은 기본이 즉시 로딩(EAGER) 이다.
		@OneToMany @ManyToMany 기본이 지연 로딩(LAZY)이다.
		@OneToOne 에서는 OWNER테이블이아닌경우 지연로딩설정이안됨
		 */
		Tutor tutor1=tutorRepository.findById(1L).get();
		List<Course> courses=tutor1.getCourses();
		for (Course course : courses) {
			System.out.println(course);
		}
		
	}
}















