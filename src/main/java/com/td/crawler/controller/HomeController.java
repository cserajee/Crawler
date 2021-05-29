package com.td.crawler.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.td.crawler.model.AppInfo;
import com.td.crawler.service.GoogleStoreService;

@Controller 
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	GoogleStoreService googleStoreService;
	 
	@GetMapping("/")
	public String home(Model model) { 
		model.addAttribute("name", "welcome");
		return "index";
	}
	
	@GetMapping("/app")
	public ModelAndView app(@RequestParam String id) { 
		AppInfo appInfo = googleStoreService.getAppData(id);
		ModelAndView modelAndView = new ModelAndView("app");
		modelAndView.addObject("app", appInfo);
		return modelAndView;
	} 
	
}
