package com.itwill.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.user.User;
import com.itwill.user.UserService;
import com.itwill.user.exception.ExistedUserException;
import com.itwill.user.exception.PasswordMismatchException;
import com.itwill.user.exception.UserNotFoundException;

import jakarta.servlet.http.HttpSession;

/*
  /user_main 
  /user_write_form 
  /user_write_action 
  /user_login_form
  /user_login_action 
  /user_logout_action 
  /user_view 
  /user_modify_form
  /user_modify_action 
  /user_remove_action
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/user_main")
	public String user_main() {
		return "user_main";
	}

	@GetMapping("/user_write_form")
	public String user_write_form() {
		return "user_write_form";
	}

	@PostMapping("/user_write_action")
	public String user_write_action(@ModelAttribute User user, RedirectAttributes redirectAttributes)	throws	Exception {
		try {
			userService.create(user);
			return "redirect:user_login_form";
		} catch (ExistedUserException e) {
			redirectAttributes.addFlashAttribute("msg", e.getMessage());
			redirectAttributes.addFlashAttribute("fuser", user);
			return "redirect:user_write_form";
		}

	}

	@GetMapping("/user_login_form")
	public String user_login_form() {
		return "user_login_form";
	}

	@PostMapping("/user_login_action")
	public String user_login_action(@ModelAttribute("fuser") User user, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		/*
		<< public String user_login_action(@ModelAttribute("fuser") User user,... ) >> 
		   
		   1. 	User user=new User();
		   
		   2. 	String userId = request.getParameter("userId");
			  	String password = request.getParameter("password");
		   
		   3. 	user.setUserId(userId);
		      	user.setPassword(password);
		      
		   4-1. [@ModelAttribute User user ]
		        request.setAttribute("user",user);  
		          
		   4-2. [@ModelAttribute("fuser") User user ]
		        request.setAttribute("fuser",user);    
		 */
		try {
			userService.login(user.getUserId(), user.getPassword());
			session.setAttribute("sUserId", user.getUserId());
			return "redirect:user_main";
		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("msg1", e.getMessage());
			redirectAttributes.addFlashAttribute("fuser", user);
			return "redirect:user_login_form";
		} catch (PasswordMismatchException e) {
			redirectAttributes.addFlashAttribute("msg2", e.getMessage());
			redirectAttributes.addFlashAttribute("fuser", user);
			return "redirect:user_login_form";
		}
	}

	@GetMapping("/user_view")
	public String user_view(HttpSession session, Model model) throws Exception {
		/******* login check ******/
		String sUserId = (String) session.getAttribute("sUserId");
		if (sUserId == null) {
			return "redirect:user_main";
		}
		/*********************/
		User loginUser = userService.findUser(sUserId);
		model.addAttribute("loginUser", loginUser);
		return "user_view";
	}

	@GetMapping("/user_modify_form")
	public String user_modify_form(HttpSession session, Model model) throws Exception {
		/******* login check ******/
		String sUserId = (String) session.getAttribute("sUserId");
		if (sUserId == null) {
			return "redirect:user_main";
		}
		/*********************/
		User loginUser = userService.findUser(sUserId);
		model.addAttribute("loginUser", loginUser);
		/*********************/
		return "user_modify_form";
	}

	@PostMapping("/user_modify_action")
	public String user_modify_action(@ModelAttribute User user, HttpSession session) throws Exception {
		/******* login check ******/
		String sUserId = (String) session.getAttribute("sUserId");
		if (sUserId == null) {
			return "redirect:user_main";
		}
		/*********************/
		user.setUserId(sUserId);
		userService.update(user);
		return "redirect:user_view";
	}

	@PostMapping("/user_remove_action")
	public String user_remove_action(HttpSession session) throws Exception {
		/******* login check ******/
		String sUserId = (String) session.getAttribute("sUserId");
		if (sUserId == null) {
			return "redirect:user_main";
		}
		/*********************/
		userService.remove(sUserId);
		session.invalidate();
		return "redirect:user_main";
	}

	@GetMapping("/user_logout_action")
	public String user_logout_action(HttpSession session) {
		/******* login check ******/
		String sUserId = (String) session.getAttribute("sUserId");
		if (sUserId == null) {
			return "redirect:user_main";
		}
		/*********************/
		session.invalidate();
		return "redirect:user_main";
	}

	/*
	@ExceptionHandler(ExistedUserException.class)
	public String existed_user_exception_handler(ExistedUserException e,RedirectAttributes redirectAttributes) {
		System.out.println(redirectAttributes);
		
		redirectAttributes.addFlashAttribute("msg",e.getMessage());
		redirectAttributes.addFlashAttribute("fuser",e.getFuser());
		return "redirect:user_write_form";
	}
	*/
	@ExceptionHandler(Exception.class)
	public String user_exception_handler(Exception e) {
		e.printStackTrace();
		return "user_error";
	}

}
