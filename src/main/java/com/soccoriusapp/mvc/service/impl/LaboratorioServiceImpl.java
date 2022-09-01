package com.soccoriusapp.mvc.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.entity.LaboratorioEntity;
import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.service.LaboratorioService;

@Service
public class LaboratorioServiceImpl implements LaboratorioService{

	@Override
	public Long countLaboratorios() {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public Paciente createLaboratorio(LaboratorioEntity laboratorio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Paciente> searchLaboratorios(int page, int limit, String sortColumn, String sortMode, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
