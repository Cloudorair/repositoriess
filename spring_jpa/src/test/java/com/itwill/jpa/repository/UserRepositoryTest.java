package com.itwill.jpa.repository;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.itwill.SpringJpaApplicationTests;
import com.itwill.jpa.entity.User;

import jakarta.transaction.Transactional;
@ActiveProfiles("user")
class UserRepositoryTest  
extends SpringJpaApplicationTests{
	@Autowired
	UserRepository userRepository;
	@DisplayName("회원가입")
	@Test
	@Transactional
	@Rollback(value = false)
	void save() {
		User user1=User.builder().userId("user26").password("2626").name("김경호26").email("guard26@gmail.com").build();
		User user2=User.builder().userId("user27").password("2727").name("김경호27").email("guard27@gmail.com").build();
		
		User savedUser1=userRepository.save(user1);
		User savedUser2=userRepository.save(user2);
		System.out.println(savedUser1);
		System.out.println(savedUser2);
	}
	@DisplayName("회원PK로찾기")
	@Test
	void findById() {
		Optional<User>  optionalUser=userRepository.findById("guard1");
		if(optionalUser.isPresent()) {
			System.out.println(optionalUser.get());
		}
	}
	@DisplayName("회원수정")
	@Test
	void update() {
		User findUser1 = userRepository.findById("guard1").get();
		findUser1.setName("제임스댄");
		userRepository.save(findUser1);
		
	}
	@DisplayName("회원삭제")
	@Test
	void delete() {
		userRepository.deleteById("guard1");
		userRepository.delete(userRepository.findById("guard2").get());
	}
	
	
	@DisplayName("사용자정의 select")
	@Test
	void customSelect() {
		System.out.println(">>>findByName:"+userRepository.findByName("김경호20").size());
		System.out.println(">>>searchByName:"+userRepository.searchByName("김경호20").size());
		System.out.println(">>>readByName:"+userRepository.readByName("김경호20").size());
		System.out.println(">>>getByName:"+userRepository.getByName("김경호20").size());
		System.out.println("--------------------------------------------------------------");
		System.out.println(">>>findByEmail:"+userRepository.findByEmail("guard1@gmail.com"));
		System.out.println(">>>findByNameAndEmail:"+userRepository.findByNameAndEmail("김경호1","guard1@gmail.com"));
		System.out.println(">>>findByNameOrEmail:"+userRepository.findByNameOrEmail("김경호1","guard2@gmail.com"));
		System.out.println("--------------------------------------------------------------");
		System.out.println(">>>findByNameOrderByNameDesc:"
				+userRepository.findByNameOrderByNameDesc("김경호20"));
		System.out.println(">>>findFirst3ByNameOrderByUserIdDesc:"
				+userRepository.findFirst3ByNameOrderByUserIdDesc("김경호20"));
		System.out.println(">>>findTop3ByNameOrderByUserIdDesc:"
				+userRepository.findTop3ByNameOrderByUserIdDesc("김경호20"));
		System.out.println("--------------------------------------------------------------");
		System.out.println(">>>findByNameContains:"+userRepository.findByNameContains("김경호1"));
		System.out.println(">>>findByNameStartingWith:"+userRepository.findByNameStartingWith("김경호2"));
		System.out.println(">>>findByNameEndingWith:"+userRepository.findByNameEndingWith("20"));
		System.out.println(">>>findByNameLike:"+userRepository.findByNameLike("%1%"));
		System.out.println(">>>findByNameLike:"+userRepository.findByNameLike("김경호__"));
		System.out.println("--------------------------------------------------------------");
		System.out.println(">>>findByNameSQL:"+userRepository.findByNameSQL("김경호20"));
		System.out.println(">>>findByNameJPQL:"+userRepository.findByNameJPQL("김경호20"));
		
		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------");
	}
}
