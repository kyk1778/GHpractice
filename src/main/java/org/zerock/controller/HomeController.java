package org.zerock.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
//	@GetMapping("/home")
//	public void home(Model model) {
//		// JSP/Servlet 구조의 request.setAttribute("serverTime",
//		//										new Date());과 동일
//		model.addAttribute("serverTime", new Date());
//	}	// view page 이름 : url과 동일 /home.jsp
	
	@GetMapping("/home")
	public String home(Model model) {
		// JSP/Servlet 구조의 request.setAttribute("serverTime",
		//										new Date());과 동일
		model.addAttribute("serverTime", new Date());
		return "home";
	}
}
