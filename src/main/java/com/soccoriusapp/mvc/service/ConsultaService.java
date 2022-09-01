package com.soccoriusapp.mvc.service;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.entity.ConsultaEntity;
import com.soccoriusapp.mvc.entity.Paciente;

public interface ConsultaService {
	
	Long countConsultas();
	Paciente createConsulta(ConsultaEntity consulta);
	Page<Paciente> searchConsultas(int page, int limit, String sortColumn, String sortMode, String keyword);

}
