package com.soccoriusapp.mvc.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/reports")
public class ReportController {

	@GetMapping
	public String showConsultasFirstPage(Model model) {
		
		return "reports/reports";
	}
}
