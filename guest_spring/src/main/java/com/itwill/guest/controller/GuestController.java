package com.itwill.guest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.guest.Guest;
import com.itwill.guest.GuestService;

@Controller
public class GuestController {
	@Autowired
	private GuestService guestService;
	public GuestController() {
		System.out.println("### GuestController()생성자");
	}
	@GetMapping("/guest_main")
	public String guest_main() {
		return "guest_main";
	}
	@GetMapping("/guest_list")
	public String guest_list(Model model) {
		try {
			List<Guest> guestList=guestService.guestList();
			model.addAttribute("guestList", guestList);
			return "guest_list";
		}catch (Exception e) {
			e.printStackTrace();
			return "guest_error";
			
		}
	}
	/*
	 parameter에 guest_no가존재하지않으면
	 */
	@GetMapping(value="/guest_view",params = "!guest_no")
	public String guest_view() {
		return "redirect:guest_main";
	}
	/*
	 parameter에 guest_no가존재하지않으면
	 */
	@GetMapping(value="/guest_view",params = "guest_no")
	public String guest_view(@RequestParam("guest_no") int guest_no,
								Model model) {
		try {
			Guest guest = guestService.guestDetail(guest_no);
			model.addAttribute("guest", guest);
			return "guest_view";
		}catch (Exception e) {
			e.printStackTrace();
			return "guest_view";
		}
	}
	@GetMapping("/guest_write_form")
	public String guest_write_form() {
		return "guest_write_form";
	}
	
	
	/*
	@PostMapping("/guest_write_action")
	public String guest_write_action(@ModelAttribute Guest guest) {
		System.out.println(guest);
		try {
			int guest_no=guestService.guestWrite(guest);
			return "redirect:guest_view?guest_no="+guest_no;
		}catch (Exception e) {
			e.printStackTrace();
			return "guest_error";
		}
	}
	*/
	
	@PostMapping("/guest_write_action")
	public String guest_write_action(	@RequestParam("guest_name") String guestName,
										@RequestParam("guest_email") String guestEmail,
										@RequestParam("guest_homepage") String guestHomepage,
										@RequestParam("guest_title") String guestTile,
										@RequestParam("guest_content") String guestContent){
		Guest guest=new Guest(0, guestName, null, guestEmail, guestHomepage, guestTile, guestContent);
		System.out.println(guest);
		try {
			int guest_no=guestService.guestWrite(guest);
			return "redirect:guest_view?guest_no="+guest_no;
		}catch (Exception e) {
			e.printStackTrace();
			return "guest_error";
		}
	}
	@PostMapping("/guest_modify_form")
	public String guest_modify_form(@RequestParam("guest_no") int guest_no,Model model) {
		try {
			Guest guest=guestService.guestDetail(guest_no);
			model.addAttribute("guest", guest);
			return "guest_modify_form";
		}catch (Exception e) {
			e.printStackTrace();
			return "guest_error";
		}
	}
	@PostMapping("/guest_modify_action")
	public String guest_modify_action(
			@RequestParam("guest_no") int guest_no,
			@RequestParam("guest_name") String guest_name,
			@RequestParam("guest_email") String guest_email,
			@RequestParam("guest_homepage") String guest_homepage,
			@RequestParam("guest_title") String guest_title,
			@RequestParam("guest_content") String guest_content,
			RedirectAttributes redirectAttributes)
	{
		Guest guest=Guest.builder()
					.guestNo(guest_no)
					.guestName(guest_name)
					.guestEmail(guest_email)
					.guestHomepage(guest_homepage)
					.guestTitle(guest_title)
					.guestContent(guest_content)
					.build();
		try {
			guestService.guestUpdate(guest);
			redirectAttributes.addAttribute("guest_no", guest_no);
			return "redirect:guest_view";
		}catch (Exception e) {
			e.printStackTrace();
			return "guest_error";
		}
	}
	@PostMapping("/guest_remove_action")
	public String guest_remove_action(@RequestParam("guest_no") int guest_no) {
		try {
			guestService.guestDelete(guest_no);
			return "redirect:guest_list";
		}catch (Exception e) {
			e.printStackTrace();
			return "guest_error";
		}
	}
	
	@GetMapping(value = {"/guest_write_action","/guest_modify_form","/guest_modify_action","/guest_remove_action"})
	public String guest_get() {
		return "redirect:guest_main";
	}
	
	
	/*
	<<요청 url(command)>>
	/guest_main			 --forward --> guest_main.jsp
	/guest_list			 --forward --> guest_list.jsp
	/guest_view			 --forward --> guest_view.jsp
	/guest_write_form	 --forward --> guest_write_form.jsp
	/guest_write_action  --redirect--> guest_list
	/guest_modify_form	 --forward --> guest_modify_form.jsp
	/guest_modify_action --redirect--> guest_view
	/guest_remove_action --redirect--> guest_list
	 */
	
}
