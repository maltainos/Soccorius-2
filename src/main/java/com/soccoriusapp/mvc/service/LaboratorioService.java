package com.soccoriusapp.mvc.service;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.entity.LaboratorioEntity;
import com.soccoriusapp.mvc.entity.Paciente;

public interface LaboratorioService {
	
	Long countLaboratorios();
	LaboratorioEntity createLaboratorio(LaboratorioEntity laboratorio);
	Page<LaboratorioEntity> searchLaboratorios(int page, int limit, String sortColumn, String sortMode, String keyword);
}
