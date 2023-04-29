package com.soccoriusapp.mvc.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.model.rest.TriagemReceitaPacienteRest;

public interface TriagemReceitaPacienteService {
	
	public Page<TriagemReceitaPacienteRest> geTriagemReceitaPacienteReport(int page, 
			String doenca, String bairro, LocalDate dateStart, LocalDate dateEnd);

}
