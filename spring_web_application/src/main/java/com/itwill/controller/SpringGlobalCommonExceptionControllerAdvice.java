package com.itwill.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class SpringGlobalCommonExceptionControllerAdvice {
		
		@ExceptionHandler(Exception.class)
		public String handle_exception(Exception e) {
			return "global_error_result";
		}
		@ExceptionHandler(RuntimeException.class)
		public String handle_runtime_exceptin(RuntimeException e) {
			return "global_error_result";
		}
	
}
