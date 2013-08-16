package com.anazzubair.techy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anazzubair.techy.business.model.User;
import com.anazzubair.techy.business.service.UserService;

@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/some.html")
	public ModelAndView indexPage() {
		
		User user = userService.getMeAUser();
		return new ModelAndView("index", "user", user);
	}
	
	@ModelAttribute("passenger")
	public String getPassenger(){
		return "Hana";
	}
}