package com.itwill.guest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/*

1. GET  	/guests       		: 방명록리스트  (모든데이타요청)
2. GET  	/guests/{guest_no}  : 방명록상세보기(guest_no 데이타를요청)
3. POST 	/guests  	  		: 방명록쓰기	(데이타추가)
4. PUT  	/guests/{guest_no} 	: 방명록수정    (guest_no 데이타를수정)
5. DELETE 	/guests/{guest_no}	: 방명록삭제	(guest_no 데이타를삭제)
 */

import com.itwill.guest.Guest;
import com.itwill.guest.GuestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.models.PathItem.HttpMethod;

@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class GuestRestController {
	@Autowired
	private GuestService guestService;
	/*
		Map resultMap=new HashMap<>();
		int status=1;
		String msg="";
		List data = new ArrayList();
		*****************************
		*****************************
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data);
		return resultMap;
	 */
	@Operation(summary = "방명록리스트",description = "방명록전체를 조회합니다.")
	@GetMapping(value = "/guests",produces = "application/json;charset=UTF-8")
	public Map guest_list() throws Exception{
		Map resultMap=new HashMap<>();
		int status=1;
		String msg="";
		List data = new ArrayList();
		/*****************************/
		data=guestService.guestList();
		/*****************************/
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data);
		return resultMap;
	}
	@Operation(summary ="방명록 상세보기")
	@Parameter(name="guest_no",description = "방명록의번호")
	@GetMapping(value = "/guests/{guest_no}",
				produces = "application/json;charset=UTF-8")
	public Map guest_detail(@PathVariable(value = "guest_no") int guest_no) throws Exception{
		Map resultMap=new HashMap<>();
		int status=1;
		String msg="";
		List data = new ArrayList();
		/*****************************/
		Guest guest=guestService.guestDetail(guest_no);
		if(guest!=null) {
			data.add(guest);
		}else {
			status=2;
			msg="게시물이존재하지않습니다.";
		}
		/*****************************/
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data);
		return resultMap;
	}
	@Operation(summary ="방명록 쓰기")
	@PostMapping(value = "/guests",produces = "application/json;charset=UTF-8")
	public Map guest_write_action(@RequestBody Guest guest) {
		System.out.println(">>guest:"+guest);
		Map resultMap=new HashMap<>();
		int status=1;
		String msg="";
		List data = new ArrayList();
		/*****************************/
		try {
			int insert_guest_no=guestService.guestWrite(guest);
			Guest newGuest=guestService.guestDetail(insert_guest_no);
			status=1;
			msg="성공";
			data.add(newGuest);
		}catch (Exception e) {
			e.printStackTrace();
			status=2;
			msg="방명록쓰기실패";
		}
		/*****************************/
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data);
		return resultMap;
		
	}
	@Operation(summary = "방명록수정",description = "전송되는아이디에해당하는게시물수정")
	@PutMapping(value = "/guests/{guest_no}",produces = "application/json;charset=UTF-8")
	public Map guest_update_action(@PathVariable("guest_no") int guest_no,
									@RequestBody Guest guest) {
		Map resultMap=new HashMap<>();
		int status=1;
		String msg="";
		List data = new ArrayList();
		/*****************************/
		try {
			guest.setGuestNo(guest_no);
			guestService.guestUpdate(guest);
			status=1;
			msg="수정성공";
			data.add(guest);
		}catch (Exception e) {
			e.printStackTrace();
			status=2;
			msg="수정실패";
		}
		/*****************************/
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data);
		return resultMap;
		
	
	}
	
	
  
	
	
	@Operation(summary ="방명록 삭제")
	@DeleteMapping(value = "/guests/{guest_no}",produces = "application/json;charset=UTF-8")
	public Map guest_remove_action(@PathVariable("guest_no") int guest_no)throws Exception{
		Map resultMap=new HashMap<>();
		int status=1;
		String msg="";
		List data = new ArrayList();
		/*****************************/
		try {
			guestService.guestDelete(guest_no);
			status=1;
			msg="";
		}catch (Exception e) {
			e.printStackTrace();
			status=2;
			msg="방명록삭제실패";
		}
		/*****************************/
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data);
		return resultMap;
	}
	
}











