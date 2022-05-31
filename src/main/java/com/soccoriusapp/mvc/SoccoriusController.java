package com.soccoriusapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class SoccoriusController {
	
	@GetMapping
	public String showHomePage(Model model) {
	
		model.addAttribute("pageTitle", "Dashboard");
		return "index";
	}
	
}
