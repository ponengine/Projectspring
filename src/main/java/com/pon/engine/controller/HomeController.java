package com.pon.engine.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

/**
 * Handles requests for the application home page.
 */
@Controller("/customer")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping("/")
	public String welcome(Model model) {
	model.addAttribute("greeting", "Welcome to Web Store!");
	model.addAttribute("tagline", "The one and only amazing web store");
	return "forward:/welcome/greeting";
	}
	@RequestMapping("/home")
	public ModelAndView greeting(Map<String, Object> model) {
	model.put("greeting", "Welcome to Web Store!");
	model.put("tagline", "The one and only amazing web store");
	View view = new InternalResourceView("/WEB-INF/views/welcome.jsp");
	return new ModelAndView(view, model);
	}
	@RequestMapping("/welcome/greeting")
	public String greeting() {
	return "welcome";
	}
}
