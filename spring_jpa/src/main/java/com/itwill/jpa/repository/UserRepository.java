package com.itwill.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.jpa.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	List<User> findByName(String name);
	List<User> searchByName(String name);
	List<User> readByName(String name);
	List<User> getByName(String name);
	
	List<User> findByEmail(String email);
	List<User> findByNameAndEmail(String name,String email);
	List<User> findByNameOrEmail(String name,String email);
	
	
	List<User> findByNameOrderByNameDesc(String name);
	List<User> findFirst3ByNameOrderByUserIdDesc(String name);
	List<User> findTop3ByNameOrderByUserIdDesc(String name);
	
	
	
	List<User> findByNameContains(String name);
	List<User> findByNameStartingWith(String name);
	List<User> findByNameEndingWith(String name);
	
	List<User> findByNameLike(String name);
	@Query(value="select * from userinfo where name=?",nativeQuery=true)
	List<User> findByNameSQL(String name);
	
	@Query(value="select u from User u where name=:name")
	List<User> findByNameJPQL(@Param("name") String name);
	/*
	JPQL(Java Persistence Query Language)
		JPQL은 SQL을 추상화하여 특정 데이터베이스 SQL에 의존적이지 않은 객체지향 쿼리 언어입니다.
 		테이블을 대상으로 쿼리를 하는것이 아닌 객체(엔티티)를 대상으로 쿼리를 하기에 객체지향 쿼리 언어라고 불립니다.
		JPQL은 결국 SQL로 변환되어 데이터베이스에 전달됩니다.
 	
		JPQL 문법
 
		예시
			select m from Member as m where m.age > 18

		- 엔티티와 속성은 대소문자를 구분합니다. (Member, age)
		- JPQL 키워드는 대소문자를 구분하지 않습니다. (SELECT, select 모두 가능)
 		- 테이블이 아닌 엔티티의 이름을 사용합니다. (Member)
		- 별칭은 필수입니다. (m) (as는 생략 가능)
		- 엔티티 클래스 이름, 엔티티 필드의 대소문자가 일치해야한다. 
	 */
	
	
	
	
}
