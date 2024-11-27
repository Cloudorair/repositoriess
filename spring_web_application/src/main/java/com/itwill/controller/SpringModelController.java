package com.itwill.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
@Controller
public class SpringModelController {
	
	@GetMapping("/model_request")
	public String model_request(HttpServletRequest request) {
		request.setAttribute("req", "리퀘스트속성데이타");
		return "forward:/WEB-INF/views/spring_model.jsp";
	}
	@GetMapping("/model_model")
	public String model_model(Model model) {
		model.addAttribute("model", "모델데이타");
		//request.setAttribute("model", " 모델데이타");
		return "forward:/WEB-INF/views/spring_model.jsp";
	}
	@GetMapping("/model_map")
	public String model_map(Map map) {
		map.put("map", "맵데이타");
		//request.setAttribute("map", " 맵데이타");
		return "forward:/WEB-INF/views/spring_model.jsp";
	}
	@GetMapping("/model_modelmap")
	public String model_modelmap(ModelMap modelMap) {
		modelMap.put("modelmap", "모델맵데이타");
		//request.setAttribute("modelmap", " 모델맵데이타");
		return "forward:/WEB-INF/views/spring_model.jsp";
	}
	@GetMapping("/model_modelandview")
	public ModelAndView modelAndView() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("modelandview", "모델앤드뷰데이타");
		//request.setAttribute("modelandview", " 모델앤드뷰데이타");
		modelAndView.setViewName("forward:/WEB-INF/views/spring_model.jsp");
		return modelAndView;
	}
	
	
	@GetMapping("/model_all")
	public String model_all(HttpServletRequest request,Model model,Map map,ModelMap modelMap) {
		System.out.println(model.getClass().getSimpleName());
		request.setAttribute("req", "리퀘스트속성데이타");
		model.addAttribute("model", "모델데이타");
		map.put("map", "맵데이타");
		modelMap.put("modelmap", "모델맵데이타");
		return "forward:/WEB-INF/views/spring_model.jsp";
	}
	
	
	
	
	
	
	
	
	
	
}
