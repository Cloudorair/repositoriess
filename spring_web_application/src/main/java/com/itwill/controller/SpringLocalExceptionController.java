package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwill.exception.BusinessException1;
import com.itwill.exception.BusinessException2;

@Controller
public class SpringLocalExceptionController {
		@GetMapping("/business1")
		public String business1() throws BusinessException1 {
			boolean b=true;
			if(b) {
				throw new BusinessException1("업무예외1");
			}
			return "business_result1";
		}
		
		@GetMapping("/business2")
		public String business2() throws BusinessException2 {
			boolean b=true;
			if(b) {
				throw new BusinessException2("업무예외2");
			}
			return "business_result2";
		}
		@GetMapping("/business3")
		public String business3() {
			String name=null;
			int length=name.length();
			return "business_result2";
		}
		
		
		/******예외처리********/
		/*
		@ExceptionHandler(Exception.class)
		public String handle_business_exception3(Exception e,Model model) {
			model.addAttribute("error_msg", e.getMessage());
			return "business_error_result";
		}*/
		
		@ExceptionHandler(BusinessException1.class)
		public String handle_business_exception1(BusinessException1 e,Model model) {
			model.addAttribute("error_msg", e.getMessage());
			return "business_error_result";
		}
		@ExceptionHandler(BusinessException2.class)
		public String handle_business_exception2(BusinessException2 e,Model model) {
			model.addAttribute("error_msg", e.getMessage());
			return "business_error_result";
		}
	
		
}
