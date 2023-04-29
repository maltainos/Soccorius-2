package com.soccoriusapp.mvc.controller.wsapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccoriusapp.mvc.model.rest.TriagemReceitaPacienteRest;
import com.soccoriusapp.mvc.service.impl.TriagemReceitaPacienteServiceImpl;

@RestController
@RequestMapping(path = "/wsapi/reports")
public class ReportRestController {

	@Autowired
	private TriagemReceitaPacienteServiceImpl reportService; 
	
	@PostMapping
	public Page<TriagemReceitaPacienteRest> getFistPageReport(@Param("doenca") String doenca, @Param("bairro") String bairro,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd) {
		
		return showReportPerPage(1, doenca, bairro, dateStart, dateEnd);
	}
	
	@GetMapping("/{page}")
	public Page<TriagemReceitaPacienteRest> showReportPerPage(@PathVariable(name = "page") int page, 
			@Param("doenca") String doenca, @Param("bairro") String bairro,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd) {
		
		LocalDate formatDateStart = LocalDate.parse(dateStart, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate formatDateEnd = LocalDate.parse(dateEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		Page<TriagemReceitaPacienteRest> returnValue =  reportService.geTriagemReceitaPacienteReport(page, doenca, bairro, formatDateStart, formatDateEnd);
		
		return returnValue;
	}
}
