package com.anazzubair.techy.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Resource(name="QueryDslUserService")
	private UserService userService;
	
	@RequestMapping(value={"/", "/index.html"})
	public ModelAndView index() {
		
		logger.debug("something logged");
		logger.info("another log");
		User user = userService.getMeAUser();
		return new ModelAndView("index", "user", user);
	}
	
	@ModelAttribute("passenger")
	public String getPassenger(){
		return "Hana";
	}
}