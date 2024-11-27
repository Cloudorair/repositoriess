package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;


/*
 * Controller객체생성
 */
@Controller
public class HelloAnnotationController2 {
	public HelloAnnotationController2() {
		System.out.println("### HelloAnnotationController2()");
	}
	/*
	 * HandlerMapping객체에 등록
	 */
	@GetMapping("/hello2")
	public String hello2(HttpServletRequest request) {
		System.out.println("### HelloAnnotationController1.hello2() 메쏘드호출");
		request.setAttribute("msg", "HelloAnnotationController2 안녕");
		return "hello2";
	}
	
}
