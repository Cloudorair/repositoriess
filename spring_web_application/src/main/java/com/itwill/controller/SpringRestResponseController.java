package com.itwill.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.dto.Guest;

@Controller
public class SpringRestResponseController {
	/******response text*******/
	@GetMapping(value="/response_string",produces = {"text/plain;charset=UTF-8"})
	@ResponseBody
	public String response_string() {
		return "Hello string for javascript ajax                           request[한글]";
	}
	@GetMapping(value="/response_html",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String response_html() {
		return "<h3>Hello string for javascript ajax request[한글]</h3><hr>";
	}
	
	@RequestMapping(value = "/response_json",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Guest response_json() {
		Guest guest=new Guest(1, "KIM", "2024/01/24", "guard@email.com","홈페이지", "타이틀", "내용");
		return guest;
	}
	
	@RequestMapping(value = "/response_json_list",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Guest> response_json_list() {
		Guest guest1=new Guest(1, "KIM", "2024/01/24", "guard1@email.com","홈페이지1", "타이틀1", "내용1");
		Guest guest2=new Guest(2, "GIM", "2024/01/25", "guard2@email.com","홈페이지2", "타이틀2", "내용2");
		Guest guest3=new Guest(3, "FIM", "2024/01/23", "guard3@email.com","홈페이지3", "타이틀3", "내용3");
		List<Guest> guestList=new ArrayList<>();
		guestList.add(guest1);
		guestList.add(guest2);
		guestList.add(guest3);
		return guestList;
	}
	
	@RequestMapping(value = "/response_xml",produces = "text/xml;charset=UTF-8")
	@ResponseBody
	public Guest response_xml() {
		Guest guest=new Guest(1, "KIM", "2024/01/24", "guard@email.com","홈페이지", "타이틀", "내용");
		return guest;
	}
	
}
