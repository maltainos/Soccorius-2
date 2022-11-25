package com.soccoriusapp.mvc.controller.wsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccoriusapp.mvc.service.impl.TriagemServiceImpl;

@RestController
@RequestMapping(path = "/wsapi/reports")
public class ReportRestController {

	@Autowired
	private TriagemServiceImpl triagemService; 
	
	@PostMapping
	public TriagemRest getReport(@Param("donca") String doenca, @Param("bairo") String bairo,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd) {
		
		triagemService.findReport(doenca, bairo, dateStart, dateEnd);
		
		return new TriagemRest();
	}
}
