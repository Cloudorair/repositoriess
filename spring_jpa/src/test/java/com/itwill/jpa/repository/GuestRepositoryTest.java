package com.itwill.jpa.repository;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.itwill.SpringJpaApplicationTests;
import com.itwill.jpa.entity.Guest;

import jakarta.transaction.Transactional;
@ActiveProfiles("guest")
class GuestRepositoryTest extends SpringJpaApplicationTests{
	@Autowired
	GuestRepository guestRepository;
	
	@DisplayName("방명록삭제")
	//@Transactional
	@Test
	void delete() {
		guestRepository.deleteById(11L);
		Guest guest2=Guest.builder().guestNo(12L).build();
		guestRepository.delete(guest2);
		//guestRepository.deleteAll();
	}
	
	@DisplayName("방명록찾기")
	@Test
	void select() {
		System.out.println("-----------findById------------");
		Optional<Guest> guestOptional= guestRepository.findById(1L);
		if(guestOptional.isPresent()) {
			Guest guest=guestOptional.get();
			System.out.println(guest);
		}else {
			System.out.println("guest없다~~~~");
		}
		System.out.println(guestRepository.findById(2L).get());
		System.out.println("-----------findAll------------");
		List<Guest> guestList=guestRepository.findAll();
		System.out.println(guestList);
		System.out.println("-----------count------------");
		System.out.println(guestRepository.count());
		System.out.println("-----------existsById------------");
		System.out.println(guestRepository.existsById(100L));
		
	}
	@Test
	@DisplayName("방명록수정")
	void update() {
		Guest guest1=guestRepository.findById(1L).get();
		System.out.println("guest 1L:"+guest1);
		guest1.setGuestName("제임스딘");
		guestRepository.save(guest1);
		
		/*Guest updateGuest2=Guest.builder()
				.guestNo(2L)
				.guestName("김경호2")
				.guestEmail("kim2@naver.com")
				.guestHomepage("http://www.naver2.com")
				.guestTitle("제목2")
				.guestContent("내용2")			
				.build();*/
		
		Guest updateGuest2=guestRepository.findById(2L).get();
		updateGuest2.setGuestContent("변경콘텐트!!!");
		guestRepository.save(updateGuest2);
	}
	
	@DisplayName("방명록저장")
	@Test
	void save() {
		Guest saveGuest1=Guest.builder()
				.guestName("김경호1")
				.guestEmail("kim1@naver.com")
				.guestHomepage("http://www.naver.com")
				.guestTitle("제목1")
				.guestContent("내용1")			
				.build();
		Guest guest1=guestRepository.save(saveGuest1);
		System.out.println(guest1);
		Guest saveGuest2=Guest.builder()
				.guestName("김경호2")
				.guestEmail("kim2@naver.com")
				.guestHomepage("http://www.naver2.com")
				.guestTitle("제목2")
				.guestContent("내용2")
				.build();
		
		Guest guest2=guestRepository.save(saveGuest2);
		System.out.println(guest2);
	}
	@DisplayName("사용자정의메쏘드")
	@Test
	@Transactional
	@Rollback(value = false)
	void custom_method() throws Exception{
		//System.out.println(">>>"+guestRepository.removeByGuestMyName("guest30"));
		System.out.println(">>>"+guestRepository.removeByGuestName("guest30"));
		System.out.println(">>>"+guestRepository.countByGuestName("guest29"));
		System.out.println(">>>"+guestRepository.existsByGuestName("guest30"));
		
		System.out.println(">>>"+guestRepository.findByGuestName("guest29"));
		System.out.println(">>>"+guestRepository.findByGuestNoBetween(1L,10L));
		System.out.println(">>>"+guestRepository.findByGuestDateGreaterThan(new SimpleDateFormat("yyyy/MM/dd").parse("2024/11/11")));
		System.out.println(">>>"+guestRepository.findByGuestDateGreaterThanEqual(new SimpleDateFormat("yyyy/MM/dd").parse("2024/11/11")));
	}
	
	
}
