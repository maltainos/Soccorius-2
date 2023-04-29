package com.soccoriusapp.mvc.controller.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soccoriusapp.mvc.model.rest.TriagemReceitaPacienteRest;
import com.soccoriusapp.mvc.service.impl.TriagemReceitaPacienteServiceImpl;

@Controller
@RequestMapping(path = "/reports")
public class ReportController {
	
	@Autowired
	private TriagemReceitaPacienteServiceImpl reportService;

	@GetMapping
	public String showReportFirstPage(Model model) {
		return "reports/reports";
	}
	
	@GetMapping("/{page}")
	public String showReportPerPage(Model model, @PathVariable(name = "page") int page, 
			@RequestParam(name = "limit", defaultValue="limit") int limit, 
			@RequestParam(name = "doenca", defaultValue = "") String doenca, 
			@RequestParam(name = "bairro", defaultValue = "") String bairro,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd) {
		
		LocalDate formatDateStart = LocalDate.parse(dateStart, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate formatDateEnd = LocalDate.parse(dateEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		
		Page<TriagemReceitaPacienteRest> reports =  reportService.geTriagemReceitaPacienteReport(
				page, doenca, bairro, formatDateStart, formatDateEnd);
		
		
		model.addAttribute("reports", reports);
		
		return "reports/reports";
	}
}
