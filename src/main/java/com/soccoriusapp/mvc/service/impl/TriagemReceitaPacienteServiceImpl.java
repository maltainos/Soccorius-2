package com.soccoriusapp.mvc.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.model.rest.TriagemReceitaPacienteRest;
import com.soccoriusapp.mvc.repository.TriagemReceitaPacienteRepository;
import com.soccoriusapp.mvc.service.TriagemReceitaPacienteService;

@Service
public class TriagemReceitaPacienteServiceImpl implements TriagemReceitaPacienteService{
	
	public static final int LIMIT = 10;

	@Autowired
	private TriagemReceitaPacienteRepository reportRepository;

	@Override
	public Page<TriagemReceitaPacienteRest> geTriagemReceitaPacienteReport(int page, String doenca,
			String bairro, LocalDate dateStart, LocalDate dateEnd) {
		
		Pageable pageable = PageRequest.of(page, LIMIT);
		
		Page<TriagemReceitaPacienteRest> returnValue = reportRepository.getTriagemReceitaReport(doenca, bairro, dateStart, dateEnd,pageable);
		
		return returnValue;
	}
	
	
}
