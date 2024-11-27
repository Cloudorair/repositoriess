package com.itwill.jpa.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long>{
	
	//int removeByGuestMyName(String guestName);
	int removeByGuestName(String guestName);
	int countByGuestName(String guestName);
	boolean existsByGuestName(String guestName);
	
	List<Guest> findByGuestName(String guestName);
	List<Guest> findByGuestNoBetween(Long no1,Long no2);
	List<Guest> findByGuestDateGreaterThan(Date guestDate);
	List<Guest> findByGuestDateGreaterThanEqual(Date guestDate);
	
	
	
	
}
