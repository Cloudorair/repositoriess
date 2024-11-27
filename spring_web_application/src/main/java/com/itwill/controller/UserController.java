package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("/list")
	public String list() {
		return "forward:/WEB-INF/views/user/list.jsp";
	}
	@RequestMapping("/view")
	public String view() {
		return "forward:/WEB-INF/views/user/view.jsp";
	}
}
